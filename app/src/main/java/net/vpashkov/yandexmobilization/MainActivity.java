package net.vpashkov.yandexmobilization;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button translateButton = (Button) findViewById(R.id.translateButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final EditText sourceTextEditor = (EditText)findViewById(R.id.sourceText);
                final String sourceText = sourceTextEditor.getText().toString();
                final AsyncTask translateTask = new RetrieveTranslationTask().execute(sourceText);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    class RetrieveTranslationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... texts) {
            final TranslatorService translatorService = new YandexTranslatorService();
            final String sourceText = texts[0];
            final String translatedText = translatorService.translate(
                    sourceText, Locale.ENGLISH, Locale.forLanguageTag("ru"));
            return translatedText;
        }

        @Override
        protected void onPostExecute(String translatedText) {
            final TextView translatedTextView = (TextView)findViewById(R.id.translatedText);
            translatedTextView.setText(translatedText);
        }
    }
}
