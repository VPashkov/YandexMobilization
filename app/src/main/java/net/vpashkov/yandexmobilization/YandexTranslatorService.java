package net.vpashkov.yandexmobilization;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

@TargetApi(Build.VERSION_CODES.KITKAT)
final class YandexTranslatorService implements TranslatorService {

    private final String baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private final String apiKey = BuildConfig.YANDEX_TRANSLATE_API_KEY;

    @Override
    public @NonNull String translate(@NonNull final String source,
                                     @NonNull final Locale sourceLocale,
                                     @NonNull final Locale targetLocale) {

        final String lang = String.format("%s-%s", sourceLocale, targetLocale);

        final Uri uri = new Uri.Builder()
                .encodedPath(baseUrl)
                .appendQueryParameter("key", apiKey)
                .appendQueryParameter("lang", lang)
                .appendQueryParameter("text", source)
                .build();

        try {
            final URL url = new URL(uri.toString());
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            try(final InputStream inputStream = connection.getInputStream()) {
                final String json = getString(inputStream);
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray textArray = jsonObject.getJSONArray("text");
                final String text = (String) textArray.get(0);
                return Uri.decode(text);
            }

        // TODO: Wrap exceptions in API exception and rethrow
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private @NonNull String getString(@NonNull final InputStream inputStream) throws IOException {
        final StringBuilder content = new StringBuilder();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            content.append(line + "\n");
        }
        return content.toString();
    }
}
