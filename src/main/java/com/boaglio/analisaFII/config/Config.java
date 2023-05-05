package com.boaglio.analisaFII.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Config {

    private Config() {}

    public static final String URL_FUNDSEXPLORER = "https://www.fundsexplorer.com.br/ranking";

    public static final Locale LOCALE_BR = new Locale("pt", "BR");

    public static final String HOJE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static final String AGORA = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));

    public static final String ARQUIVO_COM_ANALISE = HOJE + "-fundsexplorer.md";

    public static final int FUNDSEXPLORER_COLUMNS = 24;

    public static final int QUANTIDADE_FII = 10;

    public static final double PESO_POUCA_IMPORTANCIA = 1;
    public static final double PESO_MEDIA_IMPORTANCIA = 2;
    public static final double PESO_MUITA_IMPORTANCIA = 4;

    public static int erros = 0;

}
