package marvelcomicsheroes.com.api;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import marvelcomicsheroes.com.ListaItem;
import marvelcomicsheroes.com.MainListaPrincipal;
import marvelcomicsheroes.com.Util;
import marvelcomicsheroes.com.adapters.adpHorizontalLista;
import marvelcomicsheroes.com.adapters.adpListaPrincipal;
import marvelcomicsheroes.com.classes.MarvelHeroes;
import marvelcomicsheroes.com.retrofit.DataServiceMarvelComic;
import marvelcomicsheroes.com.retrofit.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aplicacao.MarvelComicHeroes.API_CALL_PUBLIC;
import static aplicacao.MarvelComicHeroes.API_KEY_PRIVATE;
import static aplicacao.MarvelComicHeroes.API_KEY_PUBLIC;

/**
 *  Esta classe é usada exclusivamente para manipulação da api https://developer.marvel.com,
 *  e direcionamento para os adapters ajacentes.
 *  É utilizada em combinação com os respectivos adapters vinculados as views de apresentação
 *  dos herois, divididos por algumas ~requisições utilizadas da api marvel
 *  */

public class apiMarvelComicsHerois extends AndroidViewModel {

    private DataServiceMarvelComic mDataServiceMarvelComicPublic = new DataServiceMarvelComic(API_CALL_PUBLIC);
    private WebService MarvelComicServicePublic = DataServiceMarvelComic.createService(WebService.class);

    public long unixTime = 0;
    public ProgressBar progressBar;

    /* Inicialização da api com a inicialização do servidor Retrofit e serviços, quenecessitam
    * do nivel de aplicação */
    public apiMarvelComicsHerois(@NonNull Application application) {
        super(application);
    }

    /* Download de herois {lista} */
    public void DownloadHeroes(int VFinicio, int VFlimit, final adpListaPrincipal madpListaPrincipal){

        if(VFinicio == -1){
            VFinicio = madpListaPrincipal.getItemCount();
            VFlimit = VFinicio + 10;
        }

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharacters(String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash(), VFinicio, VFlimit);
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    if(progressBar != null){
                        progressBar.setVisibility(View.GONE);
                    }
                    MarvelHeroes re = response.body();
                    madpListaPrincipal.addHeroes(re);
                }else{
                    DownloadHeroes(-1,10, madpListaPrincipal);
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* Download de herois po ID {unico} */
    public void DownloadHeroesID(int VFid){

        long unixTime = System.currentTimeMillis() / 1000L;
        Util util = new Util();
        String hash = Util.MD5_Hash(unixTime + API_KEY_PRIVATE + API_KEY_PUBLIC);

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharactersID(VFid, String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash());
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    MarvelHeroes re = response.body();
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* Chamadas para download dos dados do HEROI, conforme a api, CADA HEROI possui estes critérios por perfil */
    /* ################################################################################################################################## */
    /* Download  CHARACTER/COMICS */
    public void DownloadHeroiComics(final int id, final ListaItem activity, final int position){

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharactersComics(id, String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash());
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    MarvelHeroes re = response.body();
                    activity.setmDataSetComics(re, position);
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* ################################################################################################################################## */
    /* Download  CHARACTER/SERIES */
    public void DownloadHeroiSeries(final int id, final ListaItem activity, final int position){

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharactersSeries(id, String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash());
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    MarvelHeroes re = response.body();
                    activity.setmDataSetSeries(re, position);
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* ################################################################################################################################## */
    /* Download  CHARACTER/STORIES */
    public void DownloadHeroiStories(final int id, final ListaItem activity, final int position){

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharactersStories(id, String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash());
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    MarvelHeroes re = response.body();
                    activity.setmDataSetStories(re, position);
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* ################################################################################################################################## */
    /* Download  CHARACTER/EVENTS */
    public void DownloadHeroiEvents(final int id, final ListaItem activity, final int position){

        Call<MarvelHeroes> call = MarvelComicServicePublic.getCharactersEvents(id, String.valueOf(getUnixTime()), API_KEY_PUBLIC, getHash());
        call.enqueue(new Callback<MarvelHeroes>() {
            @Override
            public void onResponse(Call<MarvelHeroes> call, Response<MarvelHeroes> response) {
                if (response.isSuccessful()) {
                    MarvelHeroes re = response.body();
                    activity.setmDataSetEvents(re, position);
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroes> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                // errors
            }
        });
    }

    /* ################################################################################################################################## */
    /* ################################################################################################################################## */
    /* Funções auxiliares */
    private long getUnixTime(){
        unixTime = System.currentTimeMillis() / 10000L;
        return unixTime;
    }
    private String getHash(){
        Util util = new Util();
        String hash = Util.MD5_Hash(unixTime + API_KEY_PRIVATE + API_KEY_PUBLIC);
        return hash;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    /* ################################################################################################################################## */
}
