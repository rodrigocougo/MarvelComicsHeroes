package marvelcomicsheroes.com;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import aplicacao.classes.ConnectionLiveData;
import aplicacao.classes.ConnectionModel;
import marvelcomicsheroes.com.adapters.adpListaPrincipal;
import marvelcomicsheroes.com.api.apiMarvelComicsHerois;
import marvelcomicsheroes.com.classes.MarvelHeroes;

/**
 * "Dados fornecidos pela Marvel. © 2014 Marvel"
 */

public class MainListaPrincipal extends AppCompatActivity {

    /**
     * Está activity as seguintes tarefas:
     * Carrega uma lista infinita com os 10 primeiros HEROIS
     * Cria o controle LiveData de Conexão com a Internet
     * Carrega os direitos autorais da Marvel
     */

    /**
     * Conexão LiveData para controle da internet, estou usando apenas a conexão
     * ativa ( > 0 ) ou inativa ( == 0 ), para controle através da class Live.
     */
    public static int VSconexao_internet = -1;

    private apiMarvelComicsHerois mapiMarvelComicsHerois;
    private MarvelHeroes mDataSetMarvelHeroes = new MarvelHeroes();
    public adpListaPrincipal madpListaPrincipal;

    /* Controles de Infinite Scroll {limit de 5} */
    private boolean isScrolling = false;
    private boolean isLastPage = false;
    private int totalItems;
    private int currentItems;
    private int scrollOutItems;

    Fragment conexaoLiveData;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Seleção do layouot principal */
        setContentView(R.layout.activity_item_lista);

        /* Inicializa a conexão livedata de controle da rede (com ou sem internet) */
        ConexaoLiveData();

        /* ProgressBar */
        progressBar = findViewById(R.id.pgbItemLista);

        /* Controle da AppBarLayout/ToolBar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        TextView titulo = findViewById(R.id.txvActionBarTituloConversa);
        TextView subTitulo = findViewById(R.id.txvActionBarSubTituloConversa);
        titulo.setText("Marvel Comics Herois");
        subTitulo.setText("Data provided by Marvel. © 2019 MARVEL");

        /* Recyclerview Principal da lista de Herois */
        madpListaPrincipal = new adpListaPrincipal(this);
        RecyclerView recyclerView = findViewById(R.id.rcvItemListaPrincipal);
        final LinearLayoutManager llmCat = new LinearLayoutManager( this );
        llmCat.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(llmCat);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madpListaPrincipal);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = llmCat.getChildCount();
                totalItems= llmCat.getItemCount();
                scrollOutItems = llmCat.findFirstVisibleItemPosition();

                if(!isLastPage && (currentItems + scrollOutItems == totalItems)){
                    isScrolling = false;
                    progressBar.setVisibility(View.VISIBLE);
                    mapiMarvelComicsHerois.setProgressBar(progressBar);
                    mapiMarvelComicsHerois.DownloadHeroes(-1,10, madpListaPrincipal);
                }
            }
            //            protected void loadMoreItens(){
//                isLoading = true;
//                currentPage += 1;
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadNextPage();
//                    }
//                }, 1000);
//            }
//            @Override
//            public int getTotalPageCount(){
//                return TOTAL_PAGES;
//            }
//            @Override
//            public boolean isLastPage(){
//                return isLastPage;
//            }
//            @Override
//            public boolean isLoading(){
//                return isLoading;
//            }
        });

        /* Inicializa a API da Marvel para download da LISTA DE HEROIS */
        mapiMarvelComicsHerois = new apiMarvelComicsHerois(getApplication());
        loadFirstPage();
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
    private void loadFirstPage(){
        progressBar.setVisibility(View.VISIBLE);
        mapiMarvelComicsHerois.DownloadHeroes(0,10, madpListaPrincipal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_lista, menu);
        return true;
    }

    /* Instancia o LiveData que controla a CONEXÃO COM A INTERNET*/
    public void ConexaoLiveData(){
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                if (connection.getIsConnected()) {
                    switch (connection.getType()) {
                        case 0: //WifiData
                            if (conexaoLiveData != null) getSupportFragmentManager().popBackStack();
                            Toast.makeText(getApplicationContext(), String.format("conexão Wifi"),         Toast.LENGTH_SHORT).show();
                            VSconexao_internet = 1;
                            if(madpListaPrincipal.getHeroisQT() == false) loadFirstPage();
                            break;
                        case 1: //MobileData
                            if (conexaoLiveData != null) getSupportFragmentManager().popBackStack();
                            Toast.makeText(getApplicationContext(), String.format("conexão Mobile"), Toast.LENGTH_SHORT).show();
                            VSconexao_internet = 2;
                            if(madpListaPrincipal.getHeroisQT() == false) loadFirstPage();
                            break;
                        default:
                            if (conexaoLiveData != null) getSupportFragmentManager().popBackStack();
                            VSconexao_internet = 0;
                            break;
                    }
                } else {
                    if(VSconexao_internet > 0 || VSconexao_internet == -1) {
                        VSconexao_internet = 0;
//                        conexaoLiveData = new Intent(getApplicationContext(), MensagemConexao.class);
//                        conexaoLiveData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(conexaoLiveData);
                        conexaoLiveData = new frgMensagemConexao();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.frmMensagemConexao, conexaoLiveData)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });
    }

}
