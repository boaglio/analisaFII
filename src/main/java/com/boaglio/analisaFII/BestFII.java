package com.boaglio.analisaFII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestFII {

    private static final int PESO_1 = 1;
    private static final int PESO_2 = 2;

    public final static Comparator<Rank> maiorRank = Comparator.comparing(Rank::getValue).reversed();

    public static List<Rank> getList(List<FundoImobiliario> fiiList) {

        Map<String, Integer> topRank = new HashMap<String, Integer>();
        List<Rank> rankList = new ArrayList<>();

        // top por ativos
        fiiList.sort(Main.maiorPatrimonioLiquido);
        List<FundoImobiliario> listAtivos = fiiList.stream().limit(Main.LISTA_DE_FUNDOS).collect(Collectors.toList());
        List<Rank> rankAtivos = listAtivos.stream().map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e)))
                .collect(Collectors.toList());

        // top for DividendYield
        fiiList.sort(Main.maiorDividendYield);
        List<FundoImobiliario> listDividendYield = fiiList.stream().limit(Main.LISTA_DE_FUNDOS)
                .collect(Collectors.toList());
        List<Rank> rankDividendYield = listDividendYield.stream().map(e -> new Rank(e.getCodigo(), fiiList.indexOf(e)))
                .collect(Collectors.toList());

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

        // aplica rank

        rankAtivos.stream().forEach(r -> {
            int rankAtual = topRank.getOrDefault(r.getName(), 0);
            topRank.put(r.getName(), rankAtual + r.getValue() * PESO_2);
        });

        rankList = topRank.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(x -> new Rank(x.getKey(), x.getValue())).collect(Collectors.toList());

        rankList.sort(maiorRank);

        return rankList;

    }
}
