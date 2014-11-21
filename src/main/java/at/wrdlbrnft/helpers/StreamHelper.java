package at.wrdlbrnft.helpers;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created with IntelliJ Idea 13
 * User: Xaver
 * Date: 20/05/14
 */
public class StreamHelper {

    public static String read(InputStream stream) {
        Scanner scanner = new Scanner(stream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
