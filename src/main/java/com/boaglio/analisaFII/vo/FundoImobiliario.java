package com.boaglio.analisaFII.vo;

public record FundoImobiliario(
        String codigo,
        String setor,
        Double precoAtual,
        Double liquidezDiaria,
        Double PVP,
        Double dividendo,
        Double dividendYield,
        Double dividendYield3Macumulado,
        Double dividendYield6Macumulado,
        Double dividendYield12Macumulado,
        Double dividendYield3Mmedia,
        Double dividendYield6Mmedia,
        Double dividendYield12Mmedia,
        Double dividendYieldAno,
        Double variacaoPreco,
        Double rentabilidadePeriodo,
        Double rentabilidadeAcumulada,
        Double patrimonioLiquido,
        Double VPA,
        Double PVPA,
        Double dividendYieldPatrimonial,
        Double variacaoPatrimonial,
        Double rentabilidadePatrimonialNoPeríodo,
        Double rentabilidadePatrimonialAcumulada,
        Double vacanciaFisica,
        Double vacanciaFinanceira,
        Double quantidadeAtivos) {

}
