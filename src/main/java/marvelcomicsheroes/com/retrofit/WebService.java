package marvelcomicsheroes.com.retrofit;

import java.util.List;
import java.util.Map;

import marvelcomicsheroes.com.classes.MarvelHeroes;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    /* "Dados fornecidos pela Marvel. © 2014 Marvel" */

    /* Monitoramento de retorno das chamadas, principais aspectos:
     * CODIGOS DE STATUS DE ERRO:
      *  HTTP Status Code	Reason
      *  409	Limit greater than 100.
      *  409	Limit invalid or below 1.
      *  409	Invalid or unrecognized parameter.
      *  409	Empty parameter.
      *  409	Invalid or unrecognized ordering parameter.
      *  409	Too many values sent to a multi-value list filter.
      *  409	Invalid value passed to filter.
      *
     * PARAMETROS UTILIZADOS:
      * orderBy = string{name, modified, -name, -modified}
      * name = string
      * limit = int
      * offset = int
      *
     * CABEÇALHO:
      * {
      *    "Date": "Fri, 25 Jan 2019 02:42:36 GMT",
      *    "Content-Type": "application/json; charset=utf-8"
      * } */


    /* Chamada: https://gateway.marvel.com:443/v1/public/characters
     * Tipo: Public */
    @GET("/v1/public/characters?orderBy=name&")
    Call<MarvelHeroes> getCharacters(@Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash, @Query("offset") int Voffset, @Query("limit") int Vlimit);

    @GET("/v1/public/characters/{id}/comics?")
    Call<MarvelHeroes> getCharactersComics(@Path("id") int VFid, @Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash);

    @GET("/v1/public/characters/{id}/series?")
    Call<MarvelHeroes> getCharactersSeries(@Path("id") int VFid, @Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash);

    @GET("/v1/public/characters/{id}/stories?")
    Call<MarvelHeroes> getCharactersStories(@Path("id") int VFid, @Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash);

    @GET("/v1/public/characters/{id}/events?")
    Call<MarvelHeroes> getCharactersEvents(@Path("id") int VFid, @Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash);

    /* Chamada: https://gateway.marvel.com:443/v1/public/characters + ID heroi
     * Tipo: Public */
    @GET("/v1/public/characters/{id}?")
    Call<MarvelHeroes> getCharactersID(@Path("id") int VFid, @Query("ts") String Vts, @Query("apikey") String Vapikey, @Query("hash") String Vhash);

}
