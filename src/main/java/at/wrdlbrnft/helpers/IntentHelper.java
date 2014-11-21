package at.wrdlbrnft.helpers;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by Xaver on 26/08/14.
 */
public class IntentHelper {

    private static final String PLAYSTORE_APP_URI_PREFIX = "market://details?id=";

    public static Intent openInPlaystore(String packageName) {
        final String url = String.format("%s%s", PLAYSTORE_APP_URI_PREFIX, packageName);
        return view(url);
    }

    public static Intent view(String url) {
        final Uri uri = Uri.parse(url);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static Intent mail(String address, String subject, String body) {
        String url = MailTo.PRE_FIX + address + "?" + MailTo.PARAM_SUBJECT + subject + "&" + MailTo.PARAM_BODY + body;
        return view(url);
    }

    public static Intent mail(String address, String subject) {
        String url = MailTo.PRE_FIX + address + "?" + MailTo.PARAM_SUBJECT + subject;
        return view(url);
    }

    public static Intent mail(String address) {
        String url = MailTo.PRE_FIX + address;
        return view(url);
    }

    private static class MailTo {
        public static final String PRE_FIX = "mailto:";
        public static final String PARAM_SUBJECT = "subject=";
        public static final String PARAM_BODY = "body=";
        public static final String PARAM_BCC = "bcc=";
        public static final String PARAM_CC = "cc=";
        public static final String PARAMETER_PREFIX_FIRST = "?";
        public static final String PARAMETER_PREFIX_DEFAULT = "&";
        public static final String PARAMETER_SEPARATOR = ",";
    }
}
