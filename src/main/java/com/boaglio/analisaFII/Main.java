package com.boaglio.analisaFII;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    private static final Locale LOCALE_BR = new Locale("pt", "BR");

    public static final int LISTA_DE_FUNDOS = 10;

    private static final String SETOR_TITULOS_E_VAL_MOB = "Títulos e Val. Mob.";

    private static final String SETOR_LAJES_CORPORATIVAS = "Lajes Corporativas";

    private static final String SETOR_SHOPPINGS = "Shoppings";

    private static final String SETOR_LOGISTICA = "Logística";

    private static final int FUNDSEXPLORER_COLUMNS = 24;

    private static final String URL_FUNDSEXPLORER = "https://www.fundsexplorer.com.br/ranking";

    static DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(LOCALE_BR);

    private static String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private static String filename = today + "-fundsexplorer.txt";

    public static int erros = 0;

    // criterios
    public final static Comparator<FundoImobiliario> maiorPatrimonioLiquido         = Comparator
            .comparing(FundoImobiliario::getPatrimonioLiquido).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield             = Comparator
            .comparing(FundoImobiliario::getDividendYield).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield12Macumulado = Comparator
            .comparing(FundoImobiliario::getDividendYield12Macumulado).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield12Mmedia     = Comparator
            .comparing(FundoImobiliario::getDividendYield12Mmedia).reversed();

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(URL_FUNDSEXPLORER).get();

        List<FundoImobiliario> listaDeFundos = new ArrayList<>();

        Elements rows = doc.select("tr");
        int contador = 0;
        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() > FUNDSEXPLORER_COLUMNS) {
                listaDeFundos.add(LoadUtil.populateFI(columns));
            }
            contador++;
        }

        if (contador == 0) {
            System.out.println("Site [" + URL_FUNDSEXPLORER + "] inacessível. Tente mais tarde... =( ");
            System.exit(0);
        }

        Map<String, FundoImobiliario> mapFundos = listaDeFundos.stream()
                .collect(Collectors.toMap(FundoImobiliario::getCodigo, Function.identity()));

        plotTableLine();
        log(" ----------- Análise dos Fundos Imobiliários -----------");
        plotTableLine();
        log(" ================[ " + today + " ]================");
        plotTableLine();
        log("Total de fundos lidos: " + contador);
        log("Tamanho da lista de fundos: " + listaDeFundos.size());
        log("Erros: " + erros);

        Map<String, List<FundoImobiliario>> fundosPorSetor = listaDeFundos.stream()
                .collect(Collectors.groupingBy(FundoImobiliario::getSetor));

        log("*** Fundos por setor ***");
        plotTableLine();

        fundosPorSetor.forEach((k, v) -> log("%-20s %2d ", k, v.size()));

        List<FundoImobiliario> fundosLogistica = fundosPorSetor.get(SETOR_LOGISTICA);
        List<FundoImobiliario> fundosShopping = fundosPorSetor.get(SETOR_SHOPPINGS);
        List<FundoImobiliario> fundosLajesCorporativas = fundosPorSetor.get(SETOR_LAJES_CORPORATIVAS);
        List<FundoImobiliario> fundosTitulosValMob = fundosPorSetor.get(SETOR_TITULOS_E_VAL_MOB);

        showDetailsFI(SETOR_LOGISTICA, fundosLogistica);
        showDetailsFI(SETOR_SHOPPINGS, fundosShopping);
        showDetailsFI(SETOR_LAJES_CORPORATIVAS, fundosLajesCorporativas);
        showDetailsFI(SETOR_TITULOS_E_VAL_MOB, fundosTitulosValMob);

        log("*** Rankings por setor ***");

        List<Rank> rankFundosLogistica = BestFII.getList(fundosLogistica);
        List<Rank> rankFundosShopping = BestFII.getList(fundosShopping);
        List<Rank> rankFundosLajesCorporativas = BestFII.getList(fundosLajesCorporativas);
        List<Rank> rankFundosTitulosValMob = BestFII.getList(fundosTitulosValMob);

        showRankFI(SETOR_LOGISTICA, rankFundosLogistica, mapFundos);
        showRankFI(SETOR_SHOPPINGS, rankFundosShopping, mapFundos);
        showRankFI(SETOR_LAJES_CORPORATIVAS, rankFundosLajesCorporativas, mapFundos);
        showRankFI(SETOR_TITULOS_E_VAL_MOB, rankFundosTitulosValMob, mapFundos);

    }

    private static void showDetailsFI(String setor, List<FundoImobiliario> fundosLogistica) {
        plotLine();
        log("*** " + setor + " - Maior patrimônio ***");
        plotTableLine();
        fundosLogistica.sort(maiorPatrimonioLiquido);
        fundosLogistica.stream().limit(LISTA_DE_FUNDOS)
                .forEach(f -> log("%-10s R$%15.2f ", f.getCodigo(), f.getPatrimonioLiquido()));

        plotTableLine();
        log("--- " + setor + " - Maior Dividend Yield ---");
        plotTableLine();
        fundosLogistica.sort(maiorDividendYield);
        fundosLogistica.stream().limit(LISTA_DE_FUNDOS)
                .forEach(f -> log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield()));

        plotTableLine();
        log("*** " + setor + " - Maior Dividend Yield acumulado em 12 meses ***");
        plotTableLine();
        fundosLogistica.sort(maiorDividendYield12Macumulado);
        fundosLogistica.stream().limit(LISTA_DE_FUNDOS)
                .forEach(f -> log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield12Macumulado()));

        plotTableLine();
        log("*** " + setor + " - Maior Dividend Yield médio em 12 meses ***");
        plotTableLine();
        fundosLogistica.sort(maiorDividendYield12Mmedia);
        // fundosLogistica.stream().limit(LISTA_DE_FUNDOS).forEach(
        // f -> System.out.printf(LOCALE_BR, "%-10s %5.2f%% \n", f.getCodigo(),
        // f.getDividendYield12Mmedia()));
        fundosLogistica.stream().limit(LISTA_DE_FUNDOS)
                .forEach(f -> log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield12Mmedia()));
    }

    private static void showRankFI(String setor, List<Rank> rank, Map<String, FundoImobiliario> mapFundos) {
        log("*** " + setor + " - melhores FII ***");
        plotTableLine();
        log("*** Código|Pontos|  Preço   | Div.Yeld | DY12M m| DY12M a | Patrimônio líquido");
        plotTableLineThin();
        rank.stream().limit(LISTA_DE_FUNDOS).forEach(r -> {
            FundoImobiliario fii = mapFundos.get(r.getName());
            log("%-8s - %5.2f - R$ %7.2f - %5.2f%% - %5.2f%% - %5.2f%% - R$%15.2f ", r.getName(), r.getValue(),
                    fii.getPrecoAtual(), fii.getDividendYield(), fii.getDividendYield12Mmedia(),
                    fii.getDividendYield12Macumulado(), fii.getPatrimonioLiquido());
        });

        plotTableLine();
    }

    private static void log(String msg) {
        System.out.println(msg);
        FileUtil.saveFile(filename, msg);
    }

    private static void log(String format, String codigo, Double valor) {
        String msg = String.format(LOCALE_BR, format, codigo, valor);
        System.out.println(msg);
        FileUtil.saveFile(filename, msg);
    }

    private static void log(String format, String codigo, int valor) {
        String msg = String.format(LOCALE_BR, format, codigo, valor);
        System.out.println(msg);
        FileUtil.saveFile(filename, msg);
    }

    private static void log(String format, String codigo, Double valor1, Double valor2, Double valor3, Double valor4,
            Double valor5, Double valor6) {
        String msg = String.format(LOCALE_BR, format, codigo, valor1, valor2, valor3, valor4, valor5, valor6);
        System.out.println(msg);
        FileUtil.saveFile(filename, msg);
    }

    private static void plotTableLine() {
        log("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    private static void plotTableLineThin() {
        log("-----------------------------------------------------------------------------------------");
    }

    private static void plotLine() {
        log("=========================================================================================");
    }

}
