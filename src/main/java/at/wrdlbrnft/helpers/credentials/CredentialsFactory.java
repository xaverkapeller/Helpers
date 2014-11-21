package at.wrdlbrnft.helpers.credentials;

import android.content.Context;
import org.apache.http.auth.UsernamePasswordCredentials;

/**
 * Created with IntelliJ Idea 13
 * User: Xaver
 * Date: 24/06/14
 */
public class CredentialsFactory {

    public static Credentials create(String username, String password) {
        return new BaseCredentials(username, password);
    }

    public static Credentials create(Context context, int usernameResId, int passwordResId) {
        return new ResourceCredentials(context, usernameResId, passwordResId);
    }

    private static class BaseCredentials implements Credentials {

        private final String username;
        private final String password;

        public BaseCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public UsernamePasswordCredentials toUserPasswordCredentials() {
            return new UsernamePasswordCredentials(this.username, this.password);
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public String getPassword() {
            return this.password;
        }
    }

    private static class ResourceCredentials implements Credentials {

        private final Context context;
        private final int usernameResId;
        private final int passwordResId;

        public ResourceCredentials(Context context, int usernameResId, int passwordResId) {
            this.context = context;
            this.usernameResId = usernameResId;
            this.passwordResId = passwordResId;
        }

        @Override
        public UsernamePasswordCredentials toUserPasswordCredentials() {
            String username = this.context.getString(this.usernameResId);
            String password = this.context.getString(this.passwordResId);
            return new UsernamePasswordCredentials(username, password);
        }

        @Override
        public String getUsername() {
            return this.context.getString(this.usernameResId);
        }

        @Override
        public String getPassword() {
            return this.context.getString(this.passwordResId);
        }
    }
}
