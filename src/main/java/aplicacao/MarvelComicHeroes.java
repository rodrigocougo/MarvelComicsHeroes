package aplicacao;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MarvelComicHeroes extends Application {

    public static String API_KEY_PUBLIC = "dfd1425d88a03b1e6c7f2706686b39be";
    public static String API_KEY_PRIVATE = "fee053e07443bbf3696340baa1cd2ecfa01abcd7";
    public final static int API_IMAGE_STANDARD_LARGE = 0, API_IMAGE_PORTRAIT_UNCANNY = 1;
    public final static String API_CALL_PUBLIC = "https://gateway.marvel.com:443/";

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
