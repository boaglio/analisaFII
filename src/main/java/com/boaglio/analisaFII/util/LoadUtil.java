package com.boaglio.analisaFII.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import com.boaglio.analisaFII.config.Config;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import org.jsoup.select.Elements;

public class LoadUtil {

    private static final Locale  LOCALE_BR = new Locale("pt", "BR");
    private static final DecimalFormat DECIMAL_FORMAT = (DecimalFormat) DecimalFormat.getInstance(LOCALE_BR);

    public static FundoImobiliario populateFI(Elements columns) {
        int pos = 0;
        var codigo = readString(columns, pos++);
        var setor = readString(columns, pos++);
        var precoAtual = readDouble(columns, pos++);
        var liquidezDiaria = readDouble(columns, pos++);
        var dividendo = readDouble(columns, pos++);
        var dividendYield = readDouble(columns, pos++);
        var dividendYield3Macumulado = readDouble(columns, pos++);
        var dividendYield6Macumulado = readDouble(columns, pos++);
        var dividendYield12Macumulado = readDouble(columns, pos++);
        var dividendYield3Mmedia = readDouble(columns, pos++);
        var dividendYield6Mmedia = readDouble(columns, pos++);
        var dividendYield12Mmedia = readDouble(columns, pos++);
        var dividendYieldAno = readDouble(columns, pos++);
        var variacaoPreco = readDouble(columns, pos++);
        var rentabilidadePeriodo = readDouble(columns, pos++);
        var rentabilidadeAcumulada = readDouble(columns, pos++);
        var patrimonioLiquido = readDouble(columns, pos++);
        var VPA = readDouble(columns, pos++);
        var PVPA = readDouble(columns, pos++);
        var dividendYieldPatrimonial = readDouble(columns, pos++);
        var variacaoPatrimonial = readDouble(columns, pos++);
        var rentabilidadePatrimonialNoPeríodo = readDouble(columns, pos++);
        var rentabilidadePatrimonialAcumulada = readDouble(columns, pos++);
        var vacanciaFisica = readDouble(columns, pos++);
        var vacanciaFinanceira = readDouble(columns, pos++);
        var quantidadeAtivos = readDouble(columns, pos);

        if (setor.isEmpty()) setor = "Outros";

        return new FundoImobiliario(codigo, setor, precoAtual, liquidezDiaria, dividendo, dividendYield,
                dividendYield3Macumulado, dividendYield6Macumulado, dividendYield12Macumulado, dividendYield3Mmedia,
                dividendYield6Mmedia, dividendYield12Mmedia, dividendYieldAno, variacaoPreco, rentabilidadePeriodo,
                rentabilidadeAcumulada, patrimonioLiquido, VPA, PVPA, dividendYieldPatrimonial, variacaoPatrimonial,
                rentabilidadePatrimonialNoPeríodo, rentabilidadePatrimonialAcumulada, vacanciaFisica,
                vacanciaFinanceira, quantidadeAtivos);

    }

    private static Double readDouble(Elements el, int position) {
        String str = null;
        var value = 0d;
        try {
            str = el.get(position).text().replace("R$ ", "").replace("%", "").replace("N/A", "").replace("Inf", "");

            if (!str.isEmpty()) {
                Object obj = DECIMAL_FORMAT.parse(str);
                if (obj instanceof Long l) {
                    value = Double.valueOf(l);
                } else {
                    value = (Double) obj;
                }
            }

        } catch (NumberFormatException | ParseException | ClassCastException nfe) {
            System.out.println("Erro na leitura: [" + str + "] " + nfe.getMessage());
            Config.erros++;
        }
        return value;
    }

    private static String readString(Elements el, int position) {
        return el.get(position).text();
    }

}
