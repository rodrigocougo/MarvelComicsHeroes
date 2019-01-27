package marvelcomicsheroes.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import marvelcomicsheroes.com.adapters.adpHorizontalLista;
import marvelcomicsheroes.com.adapters.adpListaDetalhe;
import marvelcomicsheroes.com.classes.DummyContent;
import marvelcomicsheroes.com.classes.MarvelHeroes;

/**
 * A fragment é a representação dos ITENS do Charactere (HEROI).
 * Este fragmente pertence a {@link ListaItem}
 * Este Fragment faz chamadas direta e indireta ao {@link adpListaDetalhe}
 * e através deste ao {@link adpHorizontalLista}.
 */

@SuppressLint("ValidFragment")
public class frgListaItemDetalhe extends Fragment {

    public List<Object> listaObjetos = new ArrayList<>();
    public MarvelHeroes mDataSetCharacteres;
    public MarvelHeroes mDataSetComics;
    public MarvelHeroes mDataSetSeries;
    public MarvelHeroes mDataSetStories;
    public MarvelHeroes mDataSetEvents;
    private int Vposition = -1;

    private RecyclerView rcvItemDetalheLista;
    private adpListaDetalhe madpListaDetalhe;
    private Context context;

    /**
     *
     */
    public static final String ARG_ITEM_ID = "item_id";

    public frgListaItemDetalhe(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detalhe, container, false);

        if(getArguments() != null) {
            mDataSetCharacteres = (MarvelHeroes) getArguments().getSerializable("marve_herois");
            Vposition = getArguments().getInt("position");
        }

        /* RecyclerView */
        rcvItemDetalheLista = rootView.findViewById(R.id.rcvItemDetalheLista);
        rcvItemDetalheLista.setHasFixedSize(true);
        LinearLayoutManager llmCat = new LinearLayoutManager( getContext() );
        llmCat.setStackFromEnd(false);
        llmCat.setReverseLayout(false);
        llmCat.setSmoothScrollbarEnabled(true);
        rcvItemDetalheLista.setLayoutManager(llmCat);
        madpListaDetalhe = new adpListaDetalhe(context, CarrecaDadosMarvelHeroi(), null);
        rcvItemDetalheLista.setAdapter(madpListaDetalhe);
        return rootView;
    }


    /* CHARACTERES */
    public List<Object> CarrecaDadosMarvelHeroi(){
        listaObjetos.clear();

        /* Carrega os dados principais do Heroi, Para carregar o primeiro layout */
        listaObjetos.add(mDataSetCharacteres.data.results.get(Vposition).name+"::"+mDataSetCharacteres.data.results.get(Vposition).description+"::"+mDataSetCharacteres.data.results.get(Vposition).modified);

        listaObjetos.add(mDataSetCharacteres.data.results.get(Vposition).comics);

        listaObjetos.add(mDataSetCharacteres.data.results.get(Vposition).series);

        listaObjetos.add(mDataSetCharacteres.data.results.get(Vposition).stories);

        listaObjetos.add(mDataSetCharacteres.data.results.get(Vposition).events);

        return listaObjetos;
    }
    /* ################################################################################################################################## */
    /* COMICS */
    public void setmDataSetComics(MarvelHeroes mDataSetComics) {
        this.mDataSetComics = mDataSetComics;
//        madpListaDetalhe = new adpListaDetalhe(context, CarrecaDadosMarvelHeroi(), mDataSetComics);
//        rcvItemDetalheLista.setAdapter(madpListaDetalhe);
        madpListaDetalhe.setmDataSetComics(mDataSetComics);
        madpListaDetalhe.notifyDataSetChanged();
    }
    /* ################################################################################################################################## */
    /* SERIES */
    public void setmDataSetSeries(MarvelHeroes mDataSetSeries) {
        this.mDataSetSeries = mDataSetSeries;
//        madpListaDetalhe = new adpListaDetalhe(context, CarrecaDadosMarvelHeroi(), mDataSetSeries);
//        rcvItemDetalheLista.setAdapter(madpListaDetalhe);
        madpListaDetalhe.setmDataSetSeries(mDataSetSeries);
        madpListaDetalhe.notifyDataSetChanged();
    }
    /* ################################################################################################################################## */
    /* STORIES */
    public void setmDataSetStories(MarvelHeroes mDataSetStories) {
        this.mDataSetStories = mDataSetStories;
//        madpListaDetalhe = new adpListaDetalhe(context, CarrecaDadosMarvelHeroi(), mDataSetStories);
//        rcvItemDetalheLista.setAdapter(madpListaDetalhe);
        madpListaDetalhe.setmDataSetStories(mDataSetStories);
        madpListaDetalhe.notifyDataSetChanged();
    }
    /* ################################################################################################################################## */
    /* EVENTS */
    public void setmDataSetEvents(MarvelHeroes mDataSetEvents) {
        this.mDataSetEvents = mDataSetEvents;
//        madpListaDetalhe = new adpListaDetalhe(context, CarrecaDadosMarvelHeroi(), mDataSetEvents);
//        rcvItemDetalheLista.setAdapter(madpListaDetalhe);
        madpListaDetalhe.setmDataSetEvents(mDataSetEvents);
        madpListaDetalhe.notifyDataSetChanged();
    }
    /* ################################################################################################################################## */
}
