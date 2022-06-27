package com.boaglio.analisaFII;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    // criterios
    // @formatter:off
    public final static Comparator<FundoImobiliario> maiorPatrimonioLiquido         = Comparator.comparing(FundoImobiliario::getPatrimonioLiquido).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield             = Comparator.comparing(FundoImobiliario::getDividendYield).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield12Macumulado = Comparator.comparing(FundoImobiliario::getDividendYield12Macumulado).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield12Mmedia     = Comparator.comparing(FundoImobiliario::getDividendYield12Mmedia).reversed();
    // @formatter:on

    private static int contador = 0;

    public static void main(String[] args) throws IOException {

        List<FundoImobiliario> listaDeFundos = new ArrayList<>();
        listaDeFundos = parseListaDeFundos().stream().collect(Collectors.toList());

        Map<String, FundoImobiliario> mapFundos = listaDeFundos.stream()
                .collect(Collectors.toMap(FundoImobiliario::getCodigo, Function.identity()));

        Plot.plotTableLine();
        Plot.log(" ----------- Análise dos Fundos Imobiliários -----------");
        Plot.plotTableLine();
        Plot.log(" ================[ " + Config.today + " ]================");
        Plot.plotTableLine();
        Plot.log("Total de fundos lidos: " + contador);
        Plot.log("Tamanho da lista de fundos: " + listaDeFundos.size());
        Plot.log("Erros: " + Config.erros);

        Map<String, List<FundoImobiliario>> fundosPorSetor = listaDeFundos.stream()
                .collect(Collectors.groupingBy(FundoImobiliario::getSetor));

        Plot.log("*** Fundos por setor ***");
        Plot.plotTableLine();

        fundosPorSetor.forEach((k, v) -> Plot.log("%-20s %2d ", k, v.size()));

        List<FundoImobiliario> fundosLogistica = fundosPorSetor.get(TipoDeFundo.SETOR_LOGISTICA.nome());
        List<FundoImobiliario> fundosShopping = fundosPorSetor.get(TipoDeFundo.SETOR_SHOPPINGS.nome());
        List<FundoImobiliario> fundosLajesCorporativas = fundosPorSetor
                .get(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome());
        List<FundoImobiliario> fundosTitulosValMob = fundosPorSetor.get(TipoDeFundo.SETOR_TITULOS_E_VAL_MOB.nome());

        showDetailsFI(TipoDeFundo.SETOR_LOGISTICA.nome(), fundosLogistica);
        showDetailsFI(TipoDeFundo.SETOR_SHOPPINGS.nome(), fundosShopping);
        showDetailsFI(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome(), fundosLajesCorporativas);
        showDetailsFI(TipoDeFundo.SETOR_TITULOS_E_VAL_MOB.nome(), fundosTitulosValMob);

        Plot.log("*** Rankings por setor ***");

        List<Rank> rankFundosLogistica = BestFII.getList(fundosLogistica);
        List<Rank> rankFundosShopping = BestFII.getList(fundosShopping);
        List<Rank> rankFundosLajesCorporativas = BestFII.getList(fundosLajesCorporativas);
        List<Rank> rankFundosTitulosValMob = BestFII.getList(fundosTitulosValMob);

        showRankFI(TipoDeFundo.SETOR_LOGISTICA.nome(), rankFundosLogistica, mapFundos);
        showRankFI(TipoDeFundo.SETOR_SHOPPINGS.nome(), rankFundosShopping, mapFundos);
        showRankFI(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome(), rankFundosLajesCorporativas, mapFundos);
        showRankFI(TipoDeFundo.SETOR_TITULOS_E_VAL_MOB.nome(), rankFundosTitulosValMob, mapFundos);

    }

    private static void showDetailsFI(String setor, List<FundoImobiliario> fundosLogistica) {
        Plot.plotLine();
        Plot.log("*** " + setor + " - Maior patrimônio ***");
        Plot.plotTableLine();
        fundosLogistica.sort(maiorPatrimonioLiquido);
        fundosLogistica.stream().limit(Config.LISTA_DE_FUNDOS)
                .forEach(f -> Plot.log("%-10s R$%15.2f ", f.getCodigo(), f.getPatrimonioLiquido()));

        Plot.plotTableLine();
        Plot.log("--- " + setor + " - Maior Dividend Yield ---");
        Plot.plotTableLine();
        fundosLogistica.sort(maiorDividendYield);
        fundosLogistica.stream().limit(Config.LISTA_DE_FUNDOS)
                .forEach(f -> Plot.log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield()));

        Plot.plotTableLine();
        Plot.log("*** " + setor + " - Maior Dividend Yield acumulado em 12 meses ***");
        Plot.plotTableLine();
        fundosLogistica.sort(maiorDividendYield12Macumulado);
        fundosLogistica.stream().limit(Config.LISTA_DE_FUNDOS)
                .forEach(f -> Plot.log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield12Macumulado()));

        Plot.plotTableLine();
        Plot.log("*** " + setor + " - Maior Dividend Yield médio em 12 meses ***");
        Plot.plotTableLine();
        fundosLogistica.sort(maiorDividendYield12Mmedia);
        // fundosLogistica.stream().limit(LISTA_DE_FUNDOS).forEach(
        // f -> System.out.printf(LOCALE_BR, "%-10s %5.2f%% \n", f.getCodigo(),
        // f.getDividendYield12Mmedia()));
        fundosLogistica.stream().limit(Config.LISTA_DE_FUNDOS)
                .forEach(f -> Plot.log("%-10s %5.2f%% ", f.getCodigo(), f.getDividendYield12Mmedia()));
    }

    private static void showRankFI(String setor, List<Rank> rank, Map<String, FundoImobiliario> mapFundos) {
        Plot.log("*** " + setor + " - melhores FII ***");
        Plot.plotTableLine();
        Plot.log("*** Código|Pontos|  Preço   | Div.Yeld | DY12M m| DY12M a | Patrimônio líquido");
        Plot.plotTableLineThin();
        rank.stream().limit(Config.LISTA_DE_FUNDOS).forEach(r -> {
            FundoImobiliario fii = mapFundos.get(r.getName());
            Plot.log("%-8s - %5.2f - R$ %7.2f - %5.2f%% - %5.2f%% - %5.2f%% - R$%15.2f ", r.getName(), r.getValue(),
                    fii.getPrecoAtual(), fii.getDividendYield(), fii.getDividendYield12Mmedia(),
                    fii.getDividendYield12Macumulado(), fii.getPatrimonioLiquido());
        });
        Plot.plotTableLine();
    }

    private static HashSet<FundoImobiliario> parseListaDeFundos() throws IOException {

        Document doc = Jsoup.connect(TipoDeFundo.URL_FUNDSEXPLORER.nome()).get();
        Set<FundoImobiliario> hashSetListaDeFundos = new HashSet<>();

        Elements rows = doc.select("tr");

        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() > Config.FUNDSEXPLORER_COLUMNS) {
                hashSetListaDeFundos.add(LoadUtil.populateFI(columns));
            }
            contador++;
        }

        if (contador == 0) {
            System.out.println(
                    "Site [" + TipoDeFundo.URL_FUNDSEXPLORER.nome() + "] inacessível. Tente mais tarde... =( ");
            System.exit(0);
        }

        return (HashSet<FundoImobiliario>) hashSetListaDeFundos;
    }

}
