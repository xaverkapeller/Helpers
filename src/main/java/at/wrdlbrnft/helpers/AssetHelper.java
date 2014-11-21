package at.wrdlbrnft.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;

public class AssetHelper {
	
	private static final String ASSET_FOLDER_PREFIX = "file:///android_asset/";
	
	public static String readTextFromFile(Context context, String filePath) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		AssetManager manager = context.getAssets();
		InputStream stream = manager.open(filePath.replace(ASSET_FOLDER_PREFIX, ""));
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
			builder.append('\n');
		}
		bufferedReader.close();
			
		return builder.toString();
	}

    public static boolean isAssetUrl(String url) {
        return url != null && url.startsWith("file:///android_asset/");
    }

    public static String getAssetUrl(String relativePath) {
        return String.format("%s%s", ASSET_FOLDER_PREFIX, relativePath);
    }
}
