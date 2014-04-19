package be.muel.sa.data;

import com.loopj.android.http.*;

/**
 * Created by Samuel on 19/04/2014.
 */
public class ApiClient {
    private static ApiClient ourInstance = new ApiClient();

    public static synchronized ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
    }
}
