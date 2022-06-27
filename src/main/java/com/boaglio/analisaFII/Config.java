package com.boaglio.analisaFII;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Config {

    public static final Locale LOCALE_BR = new Locale("pt", "BR");

    public static String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static String filename = today + "-fundsexplorer.txt";

    public static final int FUNDSEXPLORER_COLUMNS = 24;

    public static final int LISTA_DE_FUNDOS = 10;

    public static int erros = 0;

}
