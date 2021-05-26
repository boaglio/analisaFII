package com.boaglio.analisaFII;

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

    public final static Comparator<Rank> maiorRank = Comparator.comparing(Rank::getValue).reversed();

    public static List<Rank> getList(List<FundoImobiliario> fiiList) {

        Map<String, Double> topRank = new HashMap<String, Double>();
        List<Rank> rankList = new ArrayList<>();

        // top por ativos
        fiiList.sort(Main.maiorPatrimonioLiquido);
        List<FundoImobiliario> listAtivos = fiiList.stream().limit(Main.LISTA_DE_FUNDOS).collect(Collectors.toList());
        List<Rank> rankAtivos = listAtivos.stream().map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e) + 1))
                .collect(Collectors.toList());
        listAtivos.forEach(System.out::println);

        // top for DividendYield
        fiiList.sort(Main.maiorDividendYield);
        List<FundoImobiliario> listDividendYield = fiiList.stream().limit(Main.LISTA_DE_FUNDOS)
                .collect(Collectors.toList());
        List<Rank> rankDividendYield = listDividendYield.stream()
                .map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e) + 1)).collect(Collectors.toList());

        // top for DividendYield 12 meses acumulado
        fiiList.sort(Main.maiorDividendYield12Macumulado);
        List<FundoImobiliario> listDividendYield12a = fiiList.stream().limit(Main.LISTA_DE_FUNDOS)
                .collect(Collectors.toList());
        List<Rank> rankDividendYield12a = listDividendYield12a.stream()
                .map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e))).collect(Collectors.toList());

        // top for DividendYield 12 meses media
        fiiList.sort(Main.maiorDividendYield12Mmedia);
        List<FundoImobiliario> listDividendYield12m = fiiList.stream().limit(Main.LISTA_DE_FUNDOS)
                .collect(Collectors.toList());
        List<Rank> rankDividendYield12m = listDividendYield12m.stream()
                .map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e))).collect(Collectors.toList());

        // aplica rank patrimonio liquido
        rankAtivos.stream().forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.getName(), 0.0);
            topRank.put(r.getName(), rankAtual + r.getValue() / PESO_MUITA_IMPORTANCIA);
        });

        // System.out.println("rank ativos:");
        // rankAtivos.forEach(System.out::println);

        // aplica rank Dividend Yield 12 meses media
        rankDividendYield12m.stream().forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.getName(), 0.0);
            topRank.put(r.getName(), rankAtual + r.getValue() / PESO_MEDIA_IMPORTANCIA);
        });

        // aplica rank Dividend Yield
        rankDividendYield.stream().forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.getName(), 0.0);
            topRank.put(r.getName(), rankAtual + r.getValue() / PESO_POUCA_IMPORTANCIA);
        });

        // aplica rank Dividend Yield 12 meses acumulado
        rankDividendYield12a.stream().forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.getName(), 0.0);
            topRank.put(r.getName(), rankAtual + r.getValue() / PESO_POUCA_IMPORTANCIA);
        });

        // orderna os valores
        rankList = topRank.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue())
                .map(x -> new Rank(x.getKey(), x.getValue())).collect(Collectors.toList());

        return rankList;

    }
}
