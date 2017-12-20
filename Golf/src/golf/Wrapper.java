package golf;

import com.google.gson.Gson;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public final class Wrapper {

    public static User Wrapp( String json) {
        Gson g = new Gson();
        return g.fromJson(json,User.class);
    }
}
