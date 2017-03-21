package net.vpashkov.yandexmobilization;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

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
                final TranslatorService translatorService = new FakeTranslatorService();
                final EditText sourceTextEditor = (EditText)findViewById(R.id.sourceText);
                final TextView translatedTextView = (TextView)findViewById(R.id.translatedText);
                final String sourceText = sourceTextEditor.getText().toString();
                final String translatedText = translatorService.translate(
                        sourceText, Locale.ENGLISH, Locale.forLanguageTag("ru-RU"));
                translatedTextView.setText(translatedText);
            }
        });
    }
}
