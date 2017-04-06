package net.vpashkov.yandexmobilization;

import java.util.Locale;

interface TranslatorService {
    String translate(final String source,
                     final Locale sourceLocale,
                     final Locale targetLocale);
}
