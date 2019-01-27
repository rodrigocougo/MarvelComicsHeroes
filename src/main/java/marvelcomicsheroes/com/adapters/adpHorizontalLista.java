package marvelcomicsheroes.com.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import marvelcomicsheroes.com.R;
import marvelcomicsheroes.com.api.apiMarvelComicsHerois;
import marvelcomicsheroes.com.classes.Comics;
import marvelcomicsheroes.com.classes.Events;
import marvelcomicsheroes.com.classes.MarvelHeroes;
import marvelcomicsheroes.com.classes.Series;
import marvelcomicsheroes.com.classes.Stories;
import marvelcomicsheroes.com.classes.Url;
import marvelcomicsheroes.com.classes.listaDiffCallBack;

import static aplicacao.MarvelComicHeroes.API_IMAGE_PORTRAIT_UNCANNY;
import static aplicacao.MarvelComicHeroes.API_IMAGE_STANDARD_LARGE;


public class adpHorizontalLista extends RecyclerView.Adapter {

    public Object mDataSetHor;
    public MarvelHeroes mDataSetUnico;
    public Uri[] ImagesLista;
    private final int MC_CHARACTERS = 0, MC_COMICS = 1, MC_SERIES = 2, MC_STORIES = 3, MC_EVENTS = 4, MC_URLS = 5;
    public int LAYOUT_TIPO = -1;


    public adpHorizontalLista(Object mDataSet, int LAYOUT_TIPO, MarvelHeroes mDataSetUnico) {
        this.mDataSetHor = mDataSet;
        this.LAYOUT_TIPO = LAYOUT_TIPO;
        this.mDataSetUnico = mDataSetUnico;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = null;

        switch (viewType){
            case MC_COMICS:
                if(LAYOUT_TIPO != MC_COMICS) return null;
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.comics_itens, viewGroup, false);
                return new ViewHolder(v);
            case MC_SERIES:
                if(LAYOUT_TIPO != MC_SERIES) return null;
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.comics_itens, viewGroup, false);
                return new ViewHolder(v);
            case MC_STORIES:
                if(LAYOUT_TIPO != MC_STORIES) return null;
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.comics_itens, viewGroup, false);
                return new ViewHolder(v);
            case MC_EVENTS:
                if(LAYOUT_TIPO != MC_EVENTS) return null;
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.comics_itens, viewGroup, false);
                return new ViewHolder(v);
            case MC_URLS:
                if(LAYOUT_TIPO != MC_URLS) return null;
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.comics_itens, viewGroup, false);
                return new ViewHolder(v);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;

        switch (holder.TIPO) {
            case MC_COMICS:
                ViewHolder vh1 = (ViewHolder) viewHolder;
                Comics_layout(vh1, i);
                break;
            case MC_SERIES:
                ViewHolder vh2 = (ViewHolder) viewHolder;
                Series_layout(vh2, i);
                break;
            case MC_STORIES:
                ViewHolder vh3 = (ViewHolder) viewHolder;
                Stories_layout(vh3, i);
                break;
            case MC_EVENTS:
                ViewHolder vh4 = (ViewHolder) viewHolder;
                Events_layout(vh4, i);
                break;
            case MC_URLS:
//                ViewHolder vh5 = (ViewHolder) viewHolder;
//                Url_layout(vh5, i);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mDataSetHor instanceof Comics) {
//            Comics comics = (Comics) mDataSetHor;
//            if(comics.items != null){
//                ImagesLista = new Uri[comics.items.size()];
//            }
//            return comics.items != null ? comics.items.size() : 0 ;
            return mDataSetUnico != null ? mDataSetUnico.data.results.size() : 0 ;
        } else if (mDataSetHor instanceof Series) {
//            Series series = (Series) mDataSetHor;
//            if(series.items != null){
//                ImagesLista = new Uri[series.items.size()];
//            }
//            return series.items != null ? series.items.size() : 0 ;
            return mDataSetUnico != null ? mDataSetUnico.data.results.size() : 0 ;
        } else if (mDataSetHor instanceof Stories) {
//            Stories stories = (Stories) mDataSetHor;
//            if(stories.items != null){
//                ImagesLista = new Uri[stories.items.size()];
//            }
//            return stories.items != null ? stories.items.size() : 0 ;
            return mDataSetUnico != null ? mDataSetUnico.data.results.size() : 0 ;
        } else if (mDataSetHor instanceof Events) {
//            Events events = (Events) mDataSetHor;
//            if(events.items != null){
//                ImagesLista = new Uri[events.items.size()];
//            }
//            return events.items != null ? events.items.size() : 0 ;
            return mDataSetUnico != null ? mDataSetUnico.data.results.size() : 0 ;
        } else if (mDataSetHor instanceof Url) {
//            Url urls = (Url) mDataSetHor;
//            return urls != null ? urls : 0 ;
        }
        return 0;
//        return mDataSetHor != null ? mDataSetHor. : 0 ;
    }
    @Override
    public int getItemViewType(int position) {

//        if (mDataSetHor instanceof Comics) {
//            return MC_COMICS;
//        } else if (mDataSetHor instanceof Series) {
//            return MC_SERIES;
//        } else if (mDataSetHor instanceof Stories) {
//            return MC_STORIES;
//        } else if (mDataSetHor instanceof Events) {
//            return MC_EVENTS;
//        } else if (mDataSetHor instanceof Url) {
//            return MC_URLS;
//        }
        return LAYOUT_TIPO;
    }

    /* ######################################################################################### */
    /* VIEW HOLDER's */
    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout rvlComicsItensFundo;
        SimpleDraweeView sdvComcisItemImagem;
        TextView txvComicsItensTitulo;
        TextView txvComicsItensDescricao;
        int TIPO = LAYOUT_TIPO;

        ViewHolder(View view) {
            super(view);
            rvlComicsItensFundo = view.findViewById(R.id.rvlComicsItensFundo);
            sdvComcisItemImagem = view.findViewById(R.id.sdvComcisItemImagem);
            txvComicsItensTitulo = view.findViewById(R.id.txvComicsItensTitulo);
            txvComicsItensDescricao = view.findViewById(R.id.txvComicsItensDescricao);
        }
    }

    /* ######################################################################################### */
    /* Funções BindViewHolder */
    private void Comics_layout(ViewHolder holder, int position){

        /* Carrega a Imagem das revistas, de acordo com o outro DataSet
         * com os dados da Comics */
        if(mDataSetUnico != null) {
            if(mDataSetUnico.data != null) {
                if(mDataSetUnico.data.results.get(position).thumbnail != null) {
                    if (mDataSetUnico.data.results.get(position).thumbnail.path != null &&
                            mDataSetUnico.data.results.get(position).thumbnail.extension != null) {
                        String path = mDataSetUnico.data.results.get(position).thumbnail.path;
                        String extension = mDataSetUnico.data.results.get(position).thumbnail.extension;
                        holder.sdvComcisItemImagem.setImageURI(path + "/portrait_fantastic." + extension);
                    }
                }
            }

            /* Invoca a lista horizontal */
            holder.rvlComicsItensFundo.setVisibility(View.VISIBLE);
            holder.txvComicsItensTitulo.setText(mDataSetUnico.data.results.get(position).title);
            holder.txvComicsItensDescricao.setText(mDataSetUnico.data.results.get(position).description);
        }
    }
    private void Series_layout(ViewHolder holder, int position){

        if(mDataSetUnico != null) {
            if(mDataSetUnico.data != null) {
                if(mDataSetUnico.data.results.get(position).thumbnail != null) {
                    if (mDataSetUnico.data.results.get(position).thumbnail.path != null &&
                            mDataSetUnico.data.results.get(position).thumbnail.extension != null) {
                        String path = mDataSetUnico.data.results.get(position).thumbnail.path;
                        String extension = mDataSetUnico.data.results.get(position).thumbnail.extension;
                        holder.sdvComcisItemImagem.setImageURI(path + "/portrait_fantastic." + extension);
                    }
                }
            }

            /* Invoca a lista horizontal */
            holder.rvlComicsItensFundo.setVisibility(View.VISIBLE);
            holder.txvComicsItensTitulo.setText(mDataSetUnico.data.results.get(position).title);
            holder.txvComicsItensDescricao.setText(mDataSetUnico.data.results.get(position).description);
        }
    }
    private void Stories_layout(ViewHolder holder, int position){

        if(mDataSetUnico != null) {
            if(mDataSetUnico.data != null) {
                if(mDataSetUnico.data.results.get(position).thumbnail != null) {
                    if (mDataSetUnico.data.results.get(position).thumbnail.path != null &&
                            mDataSetUnico.data.results.get(position).thumbnail.extension != null) {
                        String path = mDataSetUnico.data.results.get(position).thumbnail.path;
                        String extension = mDataSetUnico.data.results.get(position).thumbnail.extension;
                        holder.sdvComcisItemImagem.setImageURI(path + "/portrait_fantastic." + extension);
                    }
                }
            }

            /* Invoca a lista horizontal */
            holder.rvlComicsItensFundo.setVisibility(View.VISIBLE);
            holder.txvComicsItensTitulo.setText(mDataSetUnico.data.results.get(position).title);
            holder.txvComicsItensDescricao.setText(mDataSetUnico.data.results.get(position).description);
        }
    }
    private void Events_layout(ViewHolder holder, int position){

        if(mDataSetUnico != null) {
            if(mDataSetUnico.data != null) {
                if(mDataSetUnico.data.results.get(position).thumbnail != null) {
                    if (mDataSetUnico.data.results.get(position).thumbnail.path != null &&
                            mDataSetUnico.data.results.get(position).thumbnail.extension != null) {
                        String path = mDataSetUnico.data.results.get(position).thumbnail.path;
                        String extension = mDataSetUnico.data.results.get(position).thumbnail.extension;
                        holder.sdvComcisItemImagem.setImageURI(path + "/portrait_fantastic." + extension);
                    }
                }
            }

            /* Invoca a lista horizontal */
            holder.rvlComicsItensFundo.setVisibility(View.VISIBLE);
            holder.txvComicsItensTitulo.setText(mDataSetUnico.data.results.get(position).title);
            holder.txvComicsItensDescricao.setText(mDataSetUnico.data.results.get(position).description);
        }
    }
//    private void Url_layout(ViewHolder holder, int position){
//
//        if(mDataSetUnico != null) {
//            String path = mDataSetUnico.data.results.get(position).thumbnail.path;
//            String extension = mDataSetUnico.data.results.get(position).thumbnail.extension;
//
//            /* Invoca a lista horizontal */
//            holder.rvlComicsItensFundo.setVisibility(View.VISIBLE);
//            holder.sdvComcisItemImagem.setImageURI(path + "/portrait_fantastic." + extension);
//            holder.txvComicsItensTitulo.setText(mDataSetUnico.data.results.get(position).title);
//            holder.txvComicsItensDescricao.setText(mDataSetUnico.data.results.get(position).description);
//        }
//    }

    /* ######################################################################################### */
    /* UTILs */
    public void setmDataSetUnico(MarvelHeroes VFmDataSetUnico, int VFposition){
//        this.mDataSetUnico = VFmDataSetUnico;
//
//        if (mDataSetHor instanceof Comics) {
//            mDataSetHor = mDataSetUnico.data.results.get(VFposition).comics;
//        } else if (mDataSetHor instanceof Series) {
//            mDataSetHor = mDataSetUnico.data.results.get(VFposition).series;
//        } else if (mDataSetHor instanceof Stories) {
//            mDataSetHor = mDataSetUnico.data.results.get(VFposition).stories;
//        } else if (mDataSetHor instanceof Events) {
//            mDataSetHor = mDataSetUnico.data.results.get(VFposition).events;
//        } else if (mDataSetHor instanceof Url) {
////            mDataSetHor = mDataSetUnico.data.results.get(VFposition).urls
//        }

        updateEmployeeListItems(VFmDataSetUnico);

    }
    public void updateEmployeeListItems(MarvelHeroes VFmDataSetUnico) {
        final listaDiffCallBack diffCallback = new listaDiffCallBack(this.mDataSetUnico, VFmDataSetUnico);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mDataSetUnico = null;
        this.mDataSetUnico = VFmDataSetUnico;
        diffResult.dispatchUpdatesTo(this);
    }
//    public void updateEmployeeListItems(Object NewmDataSetHor) {
//        final listaDiffCallBack diffCallback = new listaDiffCallBack(this.mDataSetHor, NewmDataSetHor);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//
//        this.mDataSetUnico = null;
//        this.mDataSetUnico = NewmDataSetHor;
//        diffResult.dispatchUpdatesTo(this);
//    }
    public void Comics_conteudo_completo(Url url, int position){
//        apiMarvelComicsHerois mapiMarvelComicsHerois = new apiMarvelComicsHerois(context.getApplicationContext());

    }
    public void addItemImage(Uri uri, int position){
//        String image = "";
//        switch (tipo){
//            case API_IMAGE_STANDARD_LARGE:
//                image = "/standard_large." + extension;
//                break;
//            case API_IMAGE_PORTRAIT_UNCANNY:
//                image = "/portrait_uncanny." + extension;
//                break;
//        }
//        if(image.equals(""))return;
        ImagesLista[position] = uri;
        notifyItemChanged(position);
    }
}
