package com.boaglio.analisaFII;

public class Plot {

    public static void plotTableLine() {
        log("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public static void plotTableLineThin() {
        log("-----------------------------------------------------------------------------------------");
    }

    public static void plotLine() {
        log("=========================================================================================");
    }

    public static void log(String msg) {
        System.out.println(msg);
        FileUtil.saveFile(Config.filename, msg);
    }

    public static void log(String format, String codigo, Double valor) {
        String msg = String.format(Config.LOCALE_BR, format, codigo, valor);
        System.out.println(msg);
        FileUtil.saveFile(Config.filename, msg);
    }

    public static void log(String format, String codigo, int valor) {
        String msg = String.format(Config.LOCALE_BR, format, codigo, valor);
        System.out.println(msg);
        FileUtil.saveFile(Config.filename, msg);
    }

    public static void log(String format, String codigo, Double valor1, Double valor2, Double valor3, Double valor4,
            Double valor5, Double valor6) {
        String msg = String.format(Config.LOCALE_BR, format, codigo, valor1, valor2, valor3, valor4, valor5, valor6);
        System.out.println(msg);
        FileUtil.saveFile(Config.filename, msg);
    }
}
