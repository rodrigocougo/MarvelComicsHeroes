package marvelcomicsheroes.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import aplicacao.MarvelComicHeroes;
import marvelcomicsheroes.com.api.apiMarvelComicsHerois;
import marvelcomicsheroes.com.classes.DummyContent;
import marvelcomicsheroes.com.classes.MarvelHeroes;

import static marvelcomicsheroes.com.frgListaItemDetalhe.ARG_ITEM_ID;

/**
 * A ListItem é a centralização das chamadas no servidor da api
 * utilizando a classe apiMarvelComicsHerois como integrador com
 * Retrofit.
 */
public class ListaItem extends AppCompatActivity {

    public MarvelHeroes mDataSetCharacteres;
    public MarvelHeroes mDataSetComics;
    public MarvelHeroes mDataSetSeries;
    public MarvelHeroes mDataSetStories;
    public MarvelHeroes mDataSetEvents;

    private int Vposition = -1;
    private frgListaItemDetalhe fragment;
    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        /**
         * Neste ponto
         */

        /* Controle do Toolbar/AppBarLayout com Expansable Cabeçalho */
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent.hasExtra("marve_herois")) {
            Vposition = intent.getIntExtra("position", 0);
            this.mDataSetCharacteres = (MarvelHeroes) intent.getSerializableExtra("marve_herois");

            getSupportActionBar().setTitle("");

            SimpleDraweeView sdvItemCapa = findViewById(R.id.sdvItemCapa);
            SimpleDraweeView sdvItemAvatar = findViewById(R.id.sdvItemAvatar);

            String extension = mDataSetCharacteres.data.results.get(Vposition).thumbnail.extension;
            String path = mDataSetCharacteres.data.results.get(Vposition).thumbnail.path;
            Uri uriCapa = Uri.parse(path + "/landscape_xlarge." + extension);
            Uri uriAvatar = Uri.parse(path + "/standard_large." + extension);

            sdvItemCapa.setImageURI(uriCapa);
            sdvItemAvatar.setImageURI(uriAvatar);
        }


        /* ProgressBar de carregamento dos ITENS do CHARACTERES */
        progressBar = findViewById(R.id.pgbItemCarregando);


        /* Nesta chamada para o Fragment {frgListaItemDetalhe} são enviados
         * a posição do HEROI selecionado e a lista atualizada até o momento.
         * A List pertence a chamada de GET /v1/public/characters/id da
         * api Marvel Comics, apenas com os dados iniciais do Heroi,
         * os dados dos ITENS do HEROI serão baixados após a inicialização da
         * tela, com a chamada na apiMarvelComicsHerois */

        Bundle arguments = new Bundle();
        arguments.putSerializable("marve_herois", mDataSetCharacteres);
        arguments.putInt("position", Vposition);

        fragment = new frgListaItemDetalhe(this);
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();

        /* Função que instancia a api da Marvel, faz o download das COMICS/SERIES/STORIES/EVENTS do CHARACTER selecionad,
         * e seta a Lista atual para transportar aos adapters sequentes */
        DownloadComics(Vposition);
        DownloadSeries(Vposition);
        DownloadStories(Vposition);
        DownloadEvents(Vposition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Procedimento de download dos ITENS do CHARACTERES:
     * 1 - Inicializa a apiMarvelComicsHerois
     * 2 - Faz a chamada no procedimento {DownloadHeroiComics()} responsavel por
     * trazer todos os COMICS do HEROI selecionado.
     * 3 - No retorno da chamada Retrofit na api, é executado uma chamada no procedimento
     * {setmDataSetComics()} desta Activity, repassando uma lista de COMICS para o Fragment
     * {frgListaItemDetalhe} deta tela, levando até os dois prócimos Adapters (adpListaDetalhe, adpHorizontalLista)
     * 4 - Observe a documentação no diagrama em caso de duvidas*/

    /* ################################################################################################################################## */
    /* setCOMICS */
    @SuppressLint("NewApi")
    public void setmDataSetComics(MarvelHeroes VFmDataSetComics, int VFposition) {
        progressBar.setVisibility(View.GONE);
        fragment.setmDataSetComics(VFmDataSetComics);
    }
    /* ################################################################################################################################## */
    /* setSERIES */
    @SuppressLint("NewApi")
    public void setmDataSetSeries(MarvelHeroes VFmDataSetSeries, int VFposition) {
        progressBar.setVisibility(View.GONE);
        fragment.setmDataSetSeries(VFmDataSetSeries);
    }
    /* ################################################################################################################################## */
    /* setSTORIES */
    @SuppressLint("NewApi")
    public void setmDataSetStories(MarvelHeroes VFmDataSetStories, int VFposition) {
        progressBar.setVisibility(View.GONE);
        fragment.setmDataSetStories(VFmDataSetStories);
    }
    /* ################################################################################################################################## */
    /* setEVENTS */
    @SuppressLint("NewApi")
    public void setmDataSetEvents(MarvelHeroes VFmDataSetEvents, int VFposition) {
        progressBar.setVisibility(View.GONE);
        fragment.setmDataSetEvents(VFmDataSetEvents);
    }
    /* ################################################################################################################################## */
    /* ################################################################################################################################## */
    /* COMICS Download */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NewApi")
    public void DownloadComics(int Vposition){
        progressBar.setVisibility(View.VISIBLE);
        apiMarvelComicsHerois mapiMarvelComicsHerois = new apiMarvelComicsHerois(getApplication());
        mapiMarvelComicsHerois.DownloadHeroiComics(Math.toIntExact(mDataSetCharacteres.data.results.get(Vposition).id), this, Vposition);
    }
    /* ################################################################################################################################## */
    /* SERIES Download */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NewApi")
    public void DownloadSeries(int Vposition){
        progressBar.setVisibility(View.VISIBLE);
        apiMarvelComicsHerois mapiMarvelComicsHerois = new apiMarvelComicsHerois(getApplication());
        mapiMarvelComicsHerois.DownloadHeroiSeries(Math.toIntExact(mDataSetCharacteres.data.results.get(Vposition).id), this, Vposition);
    }
    /* ################################################################################################################################## */
    /* STORIES Download */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NewApi")
    public void DownloadStories(int Vposition){
        progressBar.setVisibility(View.VISIBLE);
        apiMarvelComicsHerois mapiMarvelComicsHerois = new apiMarvelComicsHerois(getApplication());
        mapiMarvelComicsHerois.DownloadHeroiStories(Math.toIntExact(mDataSetCharacteres.data.results.get(Vposition).id), this, Vposition);
    }
    /* ################################################################################################################################## */
    /* EVENTS Download */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NewApi")
    public void DownloadEvents(int Vposition){
        progressBar.setVisibility(View.VISIBLE);
        apiMarvelComicsHerois mapiMarvelComicsHerois = new apiMarvelComicsHerois(getApplication());
        mapiMarvelComicsHerois.DownloadHeroiEvents(Math.toIntExact(mDataSetCharacteres.data.results.get(Vposition).id), this, Vposition);
    }
    /* ################################################################################################################################## */
}
