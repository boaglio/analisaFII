package com.boaglio.analisaFII;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import org.jsoup.select.Elements;

public class LoadUtil {

    private static final Locale  LOCALE_BR = new Locale("pt", "BR");
    private static DecimalFormat df        = (DecimalFormat) DecimalFormat.getInstance(LOCALE_BR);

    public static FundoImobiliario populateFI(Elements columns) {
        int pos = 0;
        String codigo = readString(columns, pos++);
        String setor = readString(columns, pos++);
        Double precoAtual = readDouble(columns, pos++);
        Double liquidezDiaria = readDouble(columns, pos++);
        Double dividendo = readDouble(columns, pos++);
        Double dividendYield = readDouble(columns, pos++);
        Double dividendYield3Macumulado = readDouble(columns, pos++);
        Double dividendYield6Macumulado = readDouble(columns, pos++);
        Double dividendYield12Macumulado = readDouble(columns, pos++);
        Double dividendYield3Mmedia = readDouble(columns, pos++);
        Double dividendYield6Mmedia = readDouble(columns, pos++);
        Double dividendYield12Mmedia = readDouble(columns, pos++);
        Double dividendYieldAno = readDouble(columns, pos++);
        Double variacaoPreco = readDouble(columns, pos++);
        Double rentabilidadePeriodo = readDouble(columns, pos++);
        Double rentabilidadeAcumulada = readDouble(columns, pos++);
        Double patrimonioLiquido = readDouble(columns, pos++);
        Double VPA = readDouble(columns, pos++);
        Double PVPA = readDouble(columns, pos++);
        Double dividendYieldPatrimonial = readDouble(columns, pos++);
        Double variacaoPatrimonial = readDouble(columns, pos++);
        Double rentabilidadePatrimonialNoPeríodo = readDouble(columns, pos++);
        Double rentabilidadePatrimonialAcumulada = readDouble(columns, pos++);
        Double vacanciaFisica = readDouble(columns, pos++);
        Double vacanciaFinanceira = readDouble(columns, pos++);
        Double quantidadeAtivos = readDouble(columns, pos++);

        FundoImobiliario fi = new FundoImobiliario(codigo, setor, precoAtual, liquidezDiaria, dividendo, dividendYield,
                dividendYield3Macumulado, dividendYield6Macumulado, dividendYield12Macumulado, dividendYield3Mmedia,
                dividendYield6Mmedia, dividendYield12Mmedia, dividendYieldAno, variacaoPreco, rentabilidadePeriodo,
                rentabilidadeAcumulada, patrimonioLiquido, VPA, PVPA, dividendYieldPatrimonial, variacaoPatrimonial,
                rentabilidadePatrimonialNoPeríodo, rentabilidadePatrimonialAcumulada, vacanciaFisica,
                vacanciaFinanceira, quantidadeAtivos);
        return fi;
    }

    private static Double readDouble(Elements el, int position) {
        String str = null;
        Double value = 0d;
        try {
            str = el.get(position).text().replace("R$ ", "").replace("%", "").replace("N/A", "").replace("Inf", "");

            if (str != null && !str.isEmpty()) {
                Object obj = df.parse(str);
                if (obj instanceof Long) {
                    Long l = (Long) obj;
                    value = Double.valueOf(l);
                } else {
                    value = (Double) obj;
                }
            }

        } catch (NumberFormatException | ParseException | ClassCastException nfe) {
            System.out.println("Erro na leitura: [" + str + "] " + nfe.getMessage());
            Main.erros++;
        }
        return value;
    }

    private static String readString(Elements el, int position) {
        return el.get(position).text();
    }

}
