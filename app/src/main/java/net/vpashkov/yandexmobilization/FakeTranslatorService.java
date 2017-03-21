package net.vpashkov.yandexmobilization;

import java.util.Locale;

class FakeTranslatorService implements TranslatorService {

    @Override
    public String translate(String source, Locale sourceLocale, Locale targetLocale) {
        return "Привет мир!";
    }
}
