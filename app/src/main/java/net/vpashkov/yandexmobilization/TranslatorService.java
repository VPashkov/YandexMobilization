package net.vpashkov.yandexmobilization;

import java.util.Locale;

interface TranslatorService {
    String translate(String source, Locale sourceLocale, Locale targetLocale);
}
