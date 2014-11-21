package at.wrdlbrnft.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

import at.wrdlbrnft.helpers.credentials.Credentials;
import at.wrdlbrnft.helpers.credentials.CredentialsFactory;

public class NetworkHelper {

    private static final String CREDENTIALS_ENCODING = "UTF-8";

	public static boolean hasConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetwork = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

		return isNetworkConnected(wifiNetwork) || isNetworkConnected(mobileNetwork) || isNetworkConnected(activeNetwork);
	}

	private static boolean isNetworkConnected(NetworkInfo info) {
		return info != null && info.isConnected();
	}

    public static String read(String url) throws IOException {
        return read(url, null);
    }

    public static String read(String url, String username, String password) throws IOException {
        Credentials credentials = CredentialsFactory.create(username, password);
        return read(url, credentials);
    }

    public static String read(String url, Credentials credentials) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        if (credentials != null) {
            request.addHeader(BasicScheme.authenticate(credentials.toUserPasswordCredentials(), CREDENTIALS_ENCODING, false));
        }

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        InputStream stream = entity.getContent();

        return StreamHelper.read(stream);
    }

    public static InputStream open(String url) throws IOException {
        return open(url, null);
    }

    public static InputStream open(String url, String username, String password) throws IOException {
        Credentials credentials = CredentialsFactory.create(username, password);
        return open(url, credentials);
    }

    public static InputStream open(String url, Credentials credentials) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        if (credentials != null) {
            client.getCredentialsProvider().setCredentials(
                    new AuthScope(null, -1),
                    credentials.toUserPasswordCredentials());
        }

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
}
