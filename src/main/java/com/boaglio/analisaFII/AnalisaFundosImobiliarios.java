package com.boaglio.analisaFII;

import com.boaglio.analisaFII.analise.BestFII;
import com.boaglio.analisaFII.config.Config;
import com.boaglio.analisaFII.type.TipoDeFundo;
import com.boaglio.analisaFII.util.FileUtil;
import com.boaglio.analisaFII.util.Plot;
import com.boaglio.analisaFII.util.WebCrawlerUtil;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalisaFundosImobiliarios {

    // criterios
    public final static Comparator<FundoImobiliario> maiorPatrimonioLiquido         = Comparator.comparing(FundoImobiliario::patrimonioLiquido).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield             = Comparator.comparing(FundoImobiliario::dividendYield).reversed();
    public final static Comparator<FundoImobiliario> maiorDividendYield12Mmedia     = Comparator.comparing(FundoImobiliario::dividendYield12Mmedia).reversed();

    private static final String FUNDOS_DIV_YIELD_FORMAT = "| %-10s | %5.2f%% |";
    private static final String FUNDOS_PATRIMONIO_FORMAT = "| %-10s |  R$%,.2f |";
    private static final String FUNDOS_POR_SETOR_FORMAT = "| %-20s |  %2d |";
    private static final String FUNDOS_RANKING_FORMAT = "| %-8s | %5.2f | R$ %7.2f | %5.2f%% | %5.2f%% | %5.2f%% | R$%,.2f |";
    public static int contador = 0;

    public static void main(String[] args) throws IOException {

        List<FundoImobiliario> listaDeFundos = new ArrayList<>(WebCrawlerUtil.parseListaDeFundos());

        Map<String, FundoImobiliario> mapFundos = listaDeFundos.stream()
                .collect(Collectors.toMap(FundoImobiliario::codigo,
                          Function.identity(),
                        (existing, replacement) -> existing));

        FileUtil.deleteOldFile();

        Plot.log("# Análise dos Fundos Imobiliários ");
        Plot.newLine();

        Plot.log("| Data                  | *" + Config.AGORA +"* |");
        Plot.log("| - | - |");
        Plot.log("| Total de fundos lidos | " + contador + "|");
        Plot.log("| Tamanho da lista de fundos | " + listaDeFundos.size()+ "|");
        Plot.log("| Erros | " + Config.erros+ "|");
        Plot.newLine();

        Map<String, List<FundoImobiliario>> fundosPorSetor = listaDeFundos.stream()
                .sorted(Comparator.comparing(FundoImobiliario::setor))
                .collect(Collectors.groupingBy(FundoImobiliario::setor,
                        LinkedHashMap::new, Collectors.toList()));

        Plot.log("## Fundos por setor ");
        Plot.newLine();

        Plot.log("| Fundo | Total | ");
        Plot.tableLine();

        fundosPorSetor.forEach((k, v) -> Plot.log(FUNDOS_POR_SETOR_FORMAT, k, v.size()));
        Plot.newLine();

        List<FundoImobiliario> fundosLogistica = fundosPorSetor.get(TipoDeFundo.SETOR_LOGISTICA.nome());
        List<FundoImobiliario> fundosShopping = fundosPorSetor.get(TipoDeFundo.SETOR_SHOPPINGS.nome());
        List<FundoImobiliario> fundosLajesCorporativas = fundosPorSetor.get(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome());
        List<FundoImobiliario> fundosPapeis = fundosPorSetor.get(TipoDeFundo.SETOR_PAPEL.nome());
        List<FundoImobiliario> fundosMisto = fundosPorSetor.get(TipoDeFundo.SETOR_MISTO.nome());

        showDetailsFI(TipoDeFundo.SETOR_LOGISTICA.nome(), fundosLogistica);
        showDetailsFI(TipoDeFundo.SETOR_SHOPPINGS.nome(), fundosShopping);
        showDetailsFI(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome(), fundosLajesCorporativas);
        showDetailsFI(TipoDeFundo.SETOR_PAPEL.nome(), fundosPapeis);
        showDetailsFI(TipoDeFundo.SETOR_MISTO.nome(), fundosMisto);

        Plot.newLine();
        Plot.log("## Critérios do ranking");
        Plot.log("| Propriedade | Peso | ");
        Plot.tableLine();
        Plot.log("| Patrimonio Líquido | " +  Config.PESO_MUITA_IMPORTANCIA +"| ");
        Plot.log("| Dividend Yield 12 Meses - média | " +  Config.PESO_MEDIA_IMPORTANCIA +"| ");
        Plot.log("| Dividend Yield | " +  Config.PESO_POUCA_IMPORTANCIA +"| ");
        Plot.log("| Preço/Valor Patrimonial (P/VPA) | " +  Config.PESO_POUCA_IMPORTANCIA +"| ");

        Plot.log("## Rankings por setor");
        Plot.newLine();

        List<Rank> rankFundosLogistica = BestFII.getList(fundosLogistica);
        List<Rank> rankFundosShopping = BestFII.getList(fundosShopping);
        List<Rank> rankFundosLajesCorporativas = BestFII.getList(fundosLajesCorporativas);
        List<Rank> rankFundosPapeis = BestFII.getList(fundosPapeis);
        List<Rank> rankFundosMisto = BestFII.getList(fundosMisto);

        showRankFI(TipoDeFundo.SETOR_LOGISTICA.nome(), rankFundosLogistica, mapFundos);
        showRankFI(TipoDeFundo.SETOR_SHOPPINGS.nome(), rankFundosShopping, mapFundos);
        showRankFI(TipoDeFundo.SETOR_LAJES_CORPORATIVAS.nome(), rankFundosLajesCorporativas, mapFundos);
        showRankFI(TipoDeFundo.SETOR_PAPEL.nome(), rankFundosPapeis, mapFundos);
        showRankFI(TipoDeFundo.SETOR_MISTO.nome(), rankFundosMisto, mapFundos);

    }

    private static void showDetailsFI(String setor, List<FundoImobiliario> fundosLogistica) {

        Plot.log("| " + setor + " | Maior patrimônio | ");
        Plot.tableLine();

        fundosLogistica.sort(maiorPatrimonioLiquido);
        fundosLogistica.stream().limit(Config.QUANTIDADE_FII)
                .forEach(f -> Plot.log(FUNDOS_PATRIMONIO_FORMAT, f.codigo(), f.patrimonioLiquido()));
        Plot.newLine();

        Plot.log("| " + setor + " | Maior Dividend Yield  |");
        Plot.tableLine();
        fundosLogistica.sort(maiorDividendYield);
        fundosLogistica.stream().limit(Config.QUANTIDADE_FII)
                .forEach(f -> Plot.log(FUNDOS_DIV_YIELD_FORMAT, f.codigo(), f.dividendYield()));
        Plot.newLine();

        Plot.log("| " + setor + " | Maior Dividend Yield médio em 12 meses |");
        Plot.tableLine();
        fundosLogistica.sort(maiorDividendYield12Mmedia);
        fundosLogistica.stream().limit(Config.QUANTIDADE_FII)
                .forEach(f -> Plot.log(FUNDOS_DIV_YIELD_FORMAT, f.codigo(), f.dividendYield12Mmedia()));
        Plot.newLine();

    }

    private static void showRankFI(String setor, List<Rank> rank, Map<String, FundoImobiliario> mapFundos) {

        Plot.log("### " + setor + " - melhores FII ");
        Plot.newLine();

        Plot.log(" | Código | Pontos no Rank | Preço | Div.Yeld | DY12M m| DY12M a | Patrimônio líquido | ");
        Plot.log(" |-----| ----- | -----| ---- |---- | ---- | ----: |");
        rank.stream().limit(Config.QUANTIDADE_FII).forEach(r -> {
            FundoImobiliario fii = mapFundos.get(r.name());
            Plot.log(FUNDOS_RANKING_FORMAT, r.name(), r.value(),
                    fii.precoAtual(), fii.dividendYield(), fii.dividendYield12Mmedia(),
                    fii.dividendYield12Macumulado(), fii.patrimonioLiquido());
        });
        Plot.newLine();

    }

}