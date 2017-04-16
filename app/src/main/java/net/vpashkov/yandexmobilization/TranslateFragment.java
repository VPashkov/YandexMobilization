package net.vpashkov.yandexmobilization;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class TranslateFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translate, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        Button translateButton = (Button) view.findViewById(R.id.translateButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final EditText sourceTextEditor = (EditText) view.findViewById(R.id.sourceText);
                final String sourceText = sourceTextEditor.getText().toString();
                final AsyncTask translateTask = new RetrieveTranslationTask().execute(sourceText);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    class RetrieveTranslationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... texts) {
            final TranslatorService translatorService = new YandexTranslatorService();
            final String sourceText = texts[0];
            final String translatedText = translatorService.translate(
                    sourceText, Locale.ENGLISH, Locale.forLanguageTag("ru"));
            return translatedText;
        }

        @Override
        protected void onPostExecute(final String translatedText) {
            final TextView translatedTextView = (TextView) getView().findViewById(R.id.translatedText);
            translatedTextView.setText(translatedText);
        }
    }
}