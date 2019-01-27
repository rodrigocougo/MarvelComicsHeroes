package marvelcomicsheroes.com.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.service.voice.AlwaysOnHotwordDetector;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import marvelcomicsheroes.com.ListaItem;
import marvelcomicsheroes.com.MainListaPrincipal;
import marvelcomicsheroes.com.R;
import marvelcomicsheroes.com.classes.Comics;
import marvelcomicsheroes.com.classes.DummyContent;
import marvelcomicsheroes.com.classes.Events;
import marvelcomicsheroes.com.classes.MarvelHeroes;
import marvelcomicsheroes.com.classes.Result;
import marvelcomicsheroes.com.classes.Series;
import marvelcomicsheroes.com.classes.Stories;
import marvelcomicsheroes.com.classes.Url;
import marvelcomicsheroes.com.frgListaItemDetalhe;

public class adpListaDetalhe extends RecyclerView.Adapter {

    public Context context;
    public List<Object> mDataSetCharacteres;
    public MarvelHeroes mDataSetComics;
    public MarvelHeroes mDataSetSeries;
    public MarvelHeroes mDataSetStories;
    public MarvelHeroes mDataSetEvents;
    public adpHorizontalLista madpHorizontalLista;

    private final int MC_CHARACTERS = 0, MC_COMICS = 1, MC_SERIES = 2, MC_STORIES = 3, MC_EVENTS = 4, MC_URLS = 5;

    public adpListaDetalhe(Context context, List<Object> mDataSetCharacteres, MarvelHeroes mDataSetComics) {
        this.context = context;
        this.mDataSetCharacteres = mDataSetCharacteres;
        this.mDataSetComics = mDataSetComics;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;

        switch (viewType){
            case MC_CHARACTERS:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.result_itens, parent, false);
                return new Texto_ViewHolder(v);
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.horizontal_lista, parent, false);
                return new Horizontal_ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case MC_CHARACTERS:
                Texto_ViewHolder vh1 = (Texto_ViewHolder) viewHolder;
                Characters_Layout(vh1, i, MC_CHARACTERS);
                break;
            case MC_COMICS:
                Horizontal_ViewHolder vh2 = (Horizontal_ViewHolder) viewHolder;
                Horizontal_layout(vh2, i, MC_COMICS);
                break;
            case MC_SERIES:
                Horizontal_ViewHolder vh3 = (Horizontal_ViewHolder) viewHolder;
                Horizontal_layout(vh3, i, MC_SERIES);
                break;
            case MC_STORIES:
                Horizontal_ViewHolder vh4 = (Horizontal_ViewHolder) viewHolder;
                Horizontal_layout(vh4, i, MC_STORIES);
                break;
            case MC_EVENTS:
                Horizontal_ViewHolder vh5 = (Horizontal_ViewHolder) viewHolder;
                Horizontal_layout(vh5, i, MC_EVENTS);
                break;
            case MC_URLS:
                Horizontal_ViewHolder vh6 = (Horizontal_ViewHolder) viewHolder;
                Horizontal_layout(vh6, i, MC_URLS);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSetCharacteres != null ? mDataSetCharacteres.size() : 0 ;
    }
    @Override
    public int getItemViewType(int position) {
        if (mDataSetCharacteres.get(position) instanceof String) {
            return MC_CHARACTERS;
        } else if (mDataSetCharacteres.get(position) instanceof Comics) {
            return MC_COMICS;
        } else if (mDataSetCharacteres.get(position) instanceof Series) {
            return MC_SERIES;
        } else if (mDataSetCharacteres.get(position) instanceof Stories) {
            return MC_STORIES;
        } else if (mDataSetCharacteres.get(position) instanceof Events) {
            return MC_EVENTS;
        } else if (mDataSetCharacteres.get(position) instanceof Url) {
            return MC_URLS;
        }
        return -1;
    }


    /* ######################################################################################### */
    /* VIEW HOLDER's */
    class Texto_ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cslResulItensFundo;
        TextView txvResulItensName;
        TextView txvResulItensDescription;
        TextView txvResulItensModification;

        Texto_ViewHolder(View view) {
            super(view);
            cslResulItensFundo = view.findViewById(R.id.cslResulItensFundo);
            txvResulItensName = view.findViewById(R.id.txvResulItensName);
            txvResulItensDescription = view.findViewById(R.id.txvResulItensDescription);
            txvResulItensModification = view.findViewById(R.id.txvResulItensModification);
        }
    }
    class Horizontal_ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cslHorListaBloco;
        RecyclerView rcvHorLista;
        TextView txvHorListaTitulo;
        Texto_ViewHolder vh1;
        Texto_ViewHolder vh2;
        Texto_ViewHolder vh3;
        Texto_ViewHolder vh4;
        Texto_ViewHolder vh5;

        Horizontal_ViewHolder(View view) {
            super(view);
            cslHorListaBloco = view.findViewById(R.id.cslHorListaBloco);
            rcvHorLista = view.findViewById(R.id.rcvHorLista);
            txvHorListaTitulo = view.findViewById((R.id.txvHorListaTitulo));

            cslHorListaBloco.setVisibility(View.GONE);
        }
    }


    /* ######################################################################################### */
    /* Funções BindViewHolder */
    private void Characters_Layout(Texto_ViewHolder holder, int position, int TIPO){
        String result = (String) mDataSetCharacteres.get(position);
        String[] separados = SplitConteudo(result);
        if(!result.equals("")){
            holder.cslResulItensFundo.setVisibility(View.VISIBLE);
            holder.txvResulItensName.setText(separados[0]);
            holder.txvResulItensDescription.setText(separados[1]);
            holder.txvResulItensModification.setText(separados[2]);
        }else{
            holder.cslResulItensFundo.setVisibility(View.GONE);
        }
    }
    private void Horizontal_layout(Horizontal_ViewHolder holder, int position, int TIPO){

        /* Titulo da lista horizontal */
        switch (TIPO){
            case MC_COMICS:
                Comics marvelHeroes1 = (Comics) mDataSetCharacteres.get(position);
                if(marvelHeroes1.items == null) {
                    holder.cslHorListaBloco.setVisibility(View.GONE);
                    break;
                }
                if(mDataSetComics != null) {
                    if(mDataSetComics.data != null) {
                        if (mDataSetComics.data.total > 0) {
                            holder.cslHorListaBloco.setVisibility(View.VISIBLE);
                            holder.txvHorListaTitulo.setText("LISTA DE COMICS");
                            madpHorizontalLista = new adpHorizontalLista(mDataSetCharacteres.get(position), TIPO, mDataSetComics);
                            holder.rcvHorLista.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            holder.rcvHorLista.setAdapter(madpHorizontalLista);
                            holder.rcvHorLista.smoothScrollToPosition(0);
                        } else {
                            holder.cslHorListaBloco.setVisibility(View.GONE);
                        }
                    }
                }
                break;
            case MC_SERIES:
                Series marvelHeroes2 = (Series) mDataSetCharacteres.get(position);
                if(marvelHeroes2.items == null) {
                    holder.cslHorListaBloco.setVisibility(View.GONE);
                    break;
                }
                if(mDataSetSeries != null) {
                    if(mDataSetSeries.data != null) {
                        if (mDataSetSeries.data.total > 0) {
                            holder.cslHorListaBloco.setVisibility(View.VISIBLE);
                            holder.txvHorListaTitulo.setText("LISTA DE SERIES");
                            madpHorizontalLista = new adpHorizontalLista(mDataSetCharacteres.get(position), TIPO, mDataSetSeries);
                            holder.rcvHorLista.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            holder.rcvHorLista.setAdapter(madpHorizontalLista);
                            holder.rcvHorLista.smoothScrollToPosition(0);
                        } else {
                            holder.cslHorListaBloco.setVisibility(View.GONE);
                        }
                    }
                }
                break;
            case MC_STORIES:
                Stories marvelHeroes3 = (Stories) mDataSetCharacteres.get(position);
                if(marvelHeroes3.items == null) {
                    holder.cslHorListaBloco.setVisibility(View.GONE);
                    break;
                }
                if(mDataSetStories != null) {
                    if(mDataSetStories.data != null) {
                        if (mDataSetStories.data.total > 0) {
                            holder.cslHorListaBloco.setVisibility(View.VISIBLE);
                            holder.txvHorListaTitulo.setText("LISTA DE STORIES");
                            madpHorizontalLista = new adpHorizontalLista(mDataSetCharacteres.get(position), TIPO, mDataSetStories);
                            holder.rcvHorLista.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            holder.rcvHorLista.setAdapter(madpHorizontalLista);
                            holder.rcvHorLista.smoothScrollToPosition(0);
                        } else {
                            holder.cslHorListaBloco.setVisibility(View.GONE);
                        }
                    }
                }
                break;
            case MC_EVENTS:
                Events marvelHeroes4 = (Events) mDataSetCharacteres.get(position);
                if(marvelHeroes4.items == null) {
                    holder.cslHorListaBloco.setVisibility(View.GONE);
                    break;
                }
                if(mDataSetEvents != null) {
                    if(mDataSetEvents.data != null) {
                        if (mDataSetEvents.data.total > 0) {
                            holder.cslHorListaBloco.setVisibility(View.VISIBLE);
                            holder.txvHorListaTitulo.setText("LISTA DE EVENTS");
                            madpHorizontalLista = new adpHorizontalLista(mDataSetCharacteres.get(position), TIPO, mDataSetEvents);
                            holder.rcvHorLista.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            holder.rcvHorLista.setAdapter(madpHorizontalLista);
                            holder.rcvHorLista.smoothScrollToPosition(0);
                        } else {
                            holder.cslHorListaBloco.setVisibility(View.GONE);
                        }
                    }
                }
                break;
            case MC_URLS:
//                Url marvelHeroes5 = (Url) mDataSetCharacteres.get(position);
//                if(marvelHeroes5.items == null) break;
//                holder.txvHorListaTitulo.setText("OUTRAS EDIÇÕES");
                break;
        }

        /**
         * PARAMETROS
         * DataSetUnico da aplicação, com todos ITENS do CHARACTER em formato OBJECT para definição dos dados
         * TIPO de dados (ITEM do CHARACTER) que está sendo enviado no formato GLOBAL da API da MARVEL
         */
        /* Invoca a lista horizontal */

    }


    /* ######################################################################################### */
    /* UTILS */
    private String[] SplitConteudo(String VFvalor){
        String[] separated = VFvalor.split("::");
        String[] separated2 = separated[2].split("T");
        separated[2] = "Atualizado em " + separated2[0];
        return separated;
    }

    public void setmDataSetComics(MarvelHeroes mDataSetComics) {
        this.mDataSetComics = mDataSetComics;
    }

    public void setmDataSetSeries(MarvelHeroes mDataSetSeries) {
        this.mDataSetSeries = mDataSetSeries;
    }

    public void setmDataSetStories(MarvelHeroes mDataSetStories) {
        this.mDataSetStories = mDataSetStories;
    }

    public void setmDataSetEvents(MarvelHeroes mDataSetEvents) {
        this.mDataSetEvents = mDataSetEvents;
    }
}
