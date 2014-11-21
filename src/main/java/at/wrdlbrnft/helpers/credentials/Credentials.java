package at.wrdlbrnft.helpers.credentials;

import org.apache.http.auth.UsernamePasswordCredentials;

/**
* Created with IntelliJ Idea 13
* User: Xaver
* Date: 26/05/14
*/
public interface Credentials {
    public UsernamePasswordCredentials toUserPasswordCredentials();
    public String getUsername();
    public String getPassword();
}
