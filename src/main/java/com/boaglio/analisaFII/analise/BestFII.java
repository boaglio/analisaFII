package com.boaglio.analisaFII.analise;

import com.boaglio.analisaFII.AnalisaFundosImobiliarios;
import com.boaglio.analisaFII.config.Config;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestFII {

    public static List<Rank> getList(List<FundoImobiliario> fiiList) {

        Map<String, Double> topRank = new HashMap<>();
        List<Rank> rankList;

        // top por ativos
        fiiList.sort(AnalisaFundosImobiliarios.maiorPatrimonioLiquido);
        List<FundoImobiliario> listAtivos = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankAtivos = listAtivos.stream().map(e -> new Rank(e.codigo(), fiiList.indexOf(e) + 1 * Config.PESO_MUITA_IMPORTANCIA)).toList();

        // top for DividendYield
        fiiList.sort(AnalisaFundosImobiliarios.maiorDividendYield);
        List<FundoImobiliario> listDividendYield = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankDividendYield = listDividendYield.stream().map(e -> new Rank(e.codigo(), fiiList.indexOf(e) + 1 * Config.PESO_POUCA_IMPORTANCIA )).toList();

        // top for DividendYield 12 meses media
        fiiList.sort(AnalisaFundosImobiliarios.maiorDividendYield12Mmedia);
        List<FundoImobiliario> listDividendYield12m = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankDividendYield12m = listDividendYield12m.stream().map(e -> new Rank(e.codigo(), fiiList.indexOf(e)+1 * Config.PESO_MEDIA_IMPORTANCIA )).toList();

        // top for Preço/Valor Patrimonial
        fiiList.sort(AnalisaFundosImobiliarios.maiorPVPA);
        List<FundoImobiliario> listPVPA = fiiList.stream().limit(Config.LISTA_DE_FUNDOS).toList();
        List<Rank> rankPVPA = listPVPA.stream().map(e -> new Rank(e.codigo(), fiiList.indexOf(e)+1 * Config.PESO_POUCA_IMPORTANCIA)).toList();

        // aplica rank patrimonio liquido
        rankAtivos.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() );
        });

        // aplica rank Dividend Yield 12 meses media
        rankDividendYield12m.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value());
        });

        // aplica rank Dividend Yield
        rankDividendYield.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() );
        });

        // aplica rank P/VPA
        rankPVPA.forEach(r -> {
            double rankAtual = topRank.getOrDefault(r.name(), 0.0);
            topRank.put(r.name(), rankAtual + r.value() );
        });

        // orderna os valores
        rankList = topRank.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(x -> new Rank(x.getKey(), x.getValue()))
                .collect(Collectors.toList());

        return rankList;

    }
}
