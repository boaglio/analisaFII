package com.boaglio.analisaFII;

import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestFII {

    private static final int PESO_POUCA_IMPORTANCIA = 3;
    private static final int PESO_MEDIA_IMPORTANCIA = 2;
    private static final int PESO_MUITA_IMPORTANCIA = 1;

    public final static Comparator<Rank> maiorRank = Comparator.comparing(Rank::value).reversed();

    public static List<Rank> getList(List<FundoImobiliario> fiiList) {

        Map<String, Double> topRank = new HashMap<String, Double>();
        List<Rank> rankList = new ArrayList<>();

        // top por ativos
        fiiList.sort(AnalisaFundosImobiliarios.maiorPatrimonioLiquido);
        List<FundoImobiliario> listAtivos = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankAtivos = listAtivos.stream().map(e -> new Rank(e.codigo(), fiiList.indexOf(e) + 1)).toList();
        listAtivos.forEach(System.out::println);

        // top for DividendYield
        fiiList.sort(AnalisaFundosImobiliarios.maiorDividendYield);
        List<FundoImobiliario> listDividendYield = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankDividendYield = listDividendYield.stream()
                .map(e -> new Rank(e.codigo(), fiiList.indexOf(e) + 1)).toList();

        // top for DividendYield 12 meses acumulado
        fiiList.sort(AnalisaFundosImobiliarios.maiorDividendYield12Macumulado);
        List<FundoImobiliario> listDividendYield12a = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankDividendYield12a = listDividendYield12a.stream()
                .map(e -> new Rank(e.codigo(), fiiList.indexOf(e))).toList();

        // top for DividendYield 12 meses media
        fiiList.sort(AnalisaFundosImobiliarios.maiorDividendYield12Mmedia);
        List<FundoImobiliario> listDividendYield12m = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankDividendYield12m = listDividendYield12m.stream()
                .map(e -> new Rank(e.codigo(), fiiList.indexOf(e))).toList();

        // aplica rank patrimonio liquido
        rankAtivos.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() / PESO_MUITA_IMPORTANCIA);
        });

        // System.out.println("rank ativos:");
        // rankAtivos.forEach(System.out::println);

        // aplica rank Dividend Yield 12 meses media
        rankDividendYield12m.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() / PESO_MEDIA_IMPORTANCIA);
        });

        // aplica rank Dividend Yield
        rankDividendYield.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() / PESO_POUCA_IMPORTANCIA);
        });

        // aplica rank Dividend Yield 12 meses acumulado
        rankDividendYield12a.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() / PESO_POUCA_IMPORTANCIA);
        });

        // orderna os valores
        rankList = topRank.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue())
                .map(x -> new Rank(x.getKey(), x.getValue())).collect(Collectors.toList());

        return rankList;

    }
}
