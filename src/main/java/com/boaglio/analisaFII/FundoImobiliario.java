package com.boaglio.analisaFII;

public class FundoImobiliario {

    private String codigo;
    private String setor;
    private Double precoAtual;
    private Double liquidezDiaria;
    private Double dividendo;
    private Double dividendYield;
    private Double dividendYield3Macumulado;
    private Double dividendYield6Macumulado;
    private Double dividendYield12Macumulado;
    private Double dividendYield3Mmedia;
    private Double dividendYield6Mmedia;
    private Double dividendYield12Mmedia;
    private Double dividendYieldAno;
    private Double variacaoPreco;
    private Double rentabilidadePeriodo;
    private Double rentabilidadeAcumulada;
    private Double patrimonioLiquido;
    private Double VPA;
    private Double PVPA;
    private Double dividendYieldPatrimonial;
    private Double variacaoPatrimonial;
    private Double rentabilidadePatrimonialNoPeríodo;
    private Double rentabilidadePatrimonialAcumulada;
    private Double vacanciaFisica;
    private Double vacanciaFinanceira;
    private Double quantidadeAtivos;

    public FundoImobiliario(String codigo, String setor, Double precoAtual, Double liquidezDiaria, Double dividendo,
            Double dividendYield, Double dividendYield3Macumulado, Double dividendYield6Macumulado,
            Double dividendYield12Macumulado, Double dividendYield3Mmedia, Double dividendYield6Mmedia,
            Double dividendYield12Mmedia, Double dividendYieldAno, Double variacaoPreco, Double rentabilidadePeriodo,
            Double rentabilidadeAcumulada, Double patrimonioLiquido, Double vPA, Double pVPA,
            Double dividendYieldPatrimonial, Double variacaoPatrimonial, Double rentabilidadePatrimonialNoPeríodo,
            Double rentabilidadePatrimonialAcumulada, Double vacanciaFisica, Double vacanciaFinanceira,
            Double quantidadeAtivos) {
        super();
        this.codigo = codigo;
        this.setor = setor;
        this.precoAtual = precoAtual;
        this.liquidezDiaria = liquidezDiaria;
        this.dividendo = dividendo;
        this.dividendYield = dividendYield;
        this.dividendYield3Macumulado = dividendYield3Macumulado;
        this.dividendYield6Macumulado = dividendYield6Macumulado;
        this.dividendYield12Macumulado = dividendYield12Macumulado;
        this.dividendYield3Mmedia = dividendYield3Mmedia;
        this.dividendYield6Mmedia = dividendYield6Mmedia;
        this.dividendYield12Mmedia = dividendYield12Mmedia;
        this.dividendYieldAno = dividendYieldAno;
        this.variacaoPreco = variacaoPreco;
        this.rentabilidadePeriodo = rentabilidadePeriodo;
        this.rentabilidadeAcumulada = rentabilidadeAcumulada;
        this.patrimonioLiquido = patrimonioLiquido;
        VPA = vPA;
        PVPA = pVPA;
        this.dividendYieldPatrimonial = dividendYieldPatrimonial;
        this.variacaoPatrimonial = variacaoPatrimonial;
        this.rentabilidadePatrimonialNoPeríodo = rentabilidadePatrimonialNoPeríodo;
        this.rentabilidadePatrimonialAcumulada = rentabilidadePatrimonialAcumulada;
        this.vacanciaFisica = vacanciaFisica;
        this.vacanciaFinanceira = vacanciaFinanceira;
        this.quantidadeAtivos = quantidadeAtivos;
    }

    public FundoImobiliario(String codigo, String setor, Double dividendYield, Double dividendYield12Macumulado,
            Double dividendYield12Mmedia, Double patrimonioLiquido) {
        super();
        this.codigo = codigo;
        this.setor = setor;
        this.dividendYield = dividendYield;
        this.dividendYield12Macumulado = dividendYield12Macumulado;
        this.dividendYield12Mmedia = dividendYield12Mmedia;
        this.patrimonioLiquido = patrimonioLiquido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(Double precoAtual) {
        this.precoAtual = precoAtual;
    }

    public Double getLiquidezDiaria() {
        return liquidezDiaria;
    }

    public void setLiquidezDiaria(Double liquidezDiaria) {
        this.liquidezDiaria = liquidezDiaria;
    }

    public Double getDividendo() {
        return dividendo;
    }

    public void setDividendo(Double dividendo) {
        this.dividendo = dividendo;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public Double getDividendYield3Macumulado() {
        return dividendYield3Macumulado;
    }

    public void setDividendYield3Macumulado(Double dividendYield3Macumulado) {
        this.dividendYield3Macumulado = dividendYield3Macumulado;
    }

    public Double getDividendYield6Macumulado() {
        return dividendYield6Macumulado;
    }

    public void setDividendYield6Macumulado(Double dividendYield6Macumulado) {
        this.dividendYield6Macumulado = dividendYield6Macumulado;
    }

    public Double getDividendYield12Macumulado() {
        return dividendYield12Macumulado;
    }

    public void setDividendYield12Macumulado(Double dividendYield12Macumulado) {
        this.dividendYield12Macumulado = dividendYield12Macumulado;
    }

    public Double getDividendYield3Mmedia() {
        return dividendYield3Mmedia;
    }

    public void setDividendYield3Mmedia(Double dividendYield3Mmedia) {
        this.dividendYield3Mmedia = dividendYield3Mmedia;
    }

    public Double getDividendYield6Mmedia() {
        return dividendYield6Mmedia;
    }

    public void setDividendYield6Mmedia(Double dividendYield6Mmedia) {
        this.dividendYield6Mmedia = dividendYield6Mmedia;
    }

    public Double getDividendYield12Mmedia() {
        return dividendYield12Mmedia;
    }

    public void setDividendYield12Mmedia(Double dividendYield12Mmedia) {
        this.dividendYield12Mmedia = dividendYield12Mmedia;
    }

    public Double getDividendYieldAno() {
        return dividendYieldAno;
    }

    public void setDividendYieldAno(Double dividendYieldAno) {
        this.dividendYieldAno = dividendYieldAno;
    }

    public Double getVariacaoPreco() {
        return variacaoPreco;
    }

    public void setVariacaoPreco(Double variacaoPreco) {
        this.variacaoPreco = variacaoPreco;
    }

    public Double getRentabilidadePeriodo() {
        return rentabilidadePeriodo;
    }

    public void setRentabilidadePeriodo(Double rentabilidadePeriodo) {
        this.rentabilidadePeriodo = rentabilidadePeriodo;
    }

    public Double getRentabilidadeAcumulada() {
        return rentabilidadeAcumulada;
    }

    public void setRentabilidadeAcumulada(Double rentabilidadeAcumulada) {
        this.rentabilidadeAcumulada = rentabilidadeAcumulada;
    }

    public Double getPatrimonioLiquido() {
        return patrimonioLiquido;
    }

    public void setPatrimonioLiquido(Double patrimonioLiquido) {
        this.patrimonioLiquido = patrimonioLiquido;
    }

    public Double getVPA() {
        return VPA;
    }

    public void setVPA(Double vPA) {
        VPA = vPA;
    }

    public Double getPVPA() {
        return PVPA;
    }

    public void setPVPA(Double pVPA) {
        PVPA = pVPA;
    }

    public Double getDividendYieldPatrimonial() {
        return dividendYieldPatrimonial;
    }

    public void setDividendYieldPatrimonial(Double dividendYieldPatrimonial) {
        this.dividendYieldPatrimonial = dividendYieldPatrimonial;
    }

    public Double getVariacaoPatrimonial() {
        return variacaoPatrimonial;
    }

    public void setVariacaoPatrimonial(Double variacaoPatrimonial) {
        this.variacaoPatrimonial = variacaoPatrimonial;
    }

    public Double getRentabilidadePatrimonialNoPeríodo() {
        return rentabilidadePatrimonialNoPeríodo;
    }

    public void setRentabilidadePatrimonialNoPeríodo(Double rentabilidadePatrimonialNoPeríodo) {
        this.rentabilidadePatrimonialNoPeríodo = rentabilidadePatrimonialNoPeríodo;
    }

    public Double getRentabilidadePatrimonialAcumulada() {
        return rentabilidadePatrimonialAcumulada;
    }

    public void setRentabilidadePatrimonialAcumulada(Double rentabilidadePatrimonialAcumulada) {
        this.rentabilidadePatrimonialAcumulada = rentabilidadePatrimonialAcumulada;
    }

    public Double getVacanciaFisica() {
        return vacanciaFisica;
    }

    public void setVacanciaFisica(Double vacanciaFisica) {
        this.vacanciaFisica = vacanciaFisica;
    }

    public Double getVacanciaFinanceira() {
        return vacanciaFinanceira;
    }

    public void setVacanciaFinanceira(Double vacanciaFinanceira) {
        this.vacanciaFinanceira = vacanciaFinanceira;
    }

    public Double getQuantidadeAtivos() {
        return quantidadeAtivos;
    }

    public void setQuantidadeAtivos(Double quantidadeAtivos) {
        this.quantidadeAtivos = quantidadeAtivos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dividendYield12Mmedia == null) ? 0 : dividendYield12Mmedia.hashCode());
        result = prime * result + ((dividendYield3Mmedia == null) ? 0 : dividendYield3Mmedia.hashCode());
        result = prime * result + ((dividendYield6Mmedia == null) ? 0 : dividendYield6Mmedia.hashCode());
        result = prime * result + ((patrimonioLiquido == null) ? 0 : patrimonioLiquido.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FundoImobiliario other = (FundoImobiliario) obj;
        if (dividendYield12Mmedia == null) {
            if (other.dividendYield12Mmedia != null)
                return false;
        } else if (!dividendYield12Mmedia.equals(other.dividendYield12Mmedia))
            return false;
        if (dividendYield3Mmedia == null) {
            if (other.dividendYield3Mmedia != null)
                return false;
        } else if (!dividendYield3Mmedia.equals(other.dividendYield3Mmedia))
            return false;
        if (dividendYield6Mmedia == null) {
            if (other.dividendYield6Mmedia != null)
                return false;
        } else if (!dividendYield6Mmedia.equals(other.dividendYield6Mmedia))
            return false;
        if (patrimonioLiquido == null) {
            if (other.patrimonioLiquido != null)
                return false;
        } else if (!patrimonioLiquido.equals(other.patrimonioLiquido))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FundoImobiliario [codigo=" + codigo + ", setor=" + setor + ", precoAtual=" + precoAtual
                + ", liquidezDiaria=" + liquidezDiaria + ", dividendo=" + dividendo + ", dividendYield=" + dividendYield
                + ", dividendYield3Macumulado=" + dividendYield3Macumulado + ", dividendYield6Macumulado="
                + dividendYield6Macumulado + ", dividendYield12Macumulado=" + dividendYield12Macumulado
                + ", dividendYield3Mmedia=" + dividendYield3Mmedia + ", dividendYield6Mmedia=" + dividendYield6Mmedia
                + ", dividendYield12Mmedia=" + dividendYield12Mmedia + ", dividendYieldAno=" + dividendYieldAno
                + ", variacaoPreco=" + variacaoPreco + ", rentabilidadePeriodo=" + rentabilidadePeriodo
                + ", rentabilidadeAcumulada=" + rentabilidadeAcumulada + ", patrimonioLiquido=" + patrimonioLiquido
                + ", VPA=" + VPA + ", PVPA=" + PVPA + ", dividendYieldPatrimonial=" + dividendYieldPatrimonial
                + ", variacaoPatrimonial=" + variacaoPatrimonial + ", rentabilidadePatrimonialNoPeríodo="
                + rentabilidadePatrimonialNoPeríodo + ", rentabilidadePatrimonialAcumulada="
                + rentabilidadePatrimonialAcumulada + ", vacanciaFisica=" + vacanciaFisica + ", vacanciaFinanceira="
                + vacanciaFinanceira + ", quantidadeAtivos=" + quantidadeAtivos + "]";
    }

}
