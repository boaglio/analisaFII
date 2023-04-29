package com.boaglio.analisaFII;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Config {

    private Config() {}

    public static final Locale LOCALE_BR = new Locale("pt", "BR");

    public static final String HOJE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static final String ARQUIVO_COM_ANALISE = HOJE + "-fundsexplorer.md";

    public static final int FUNDSEXPLORER_COLUMNS = 24;

    public static final int LISTA_DE_FUNDOS = 10;

    public static int erros = 0;

}
