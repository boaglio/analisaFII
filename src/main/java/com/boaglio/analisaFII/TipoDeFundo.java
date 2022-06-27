package com.boaglio.analisaFII;

public enum TipoDeFundo {

    SETOR_TITULOS_E_VAL_MOB("Títulos e Val. Mob."),
    SETOR_LAJES_CORPORATIVAS("Lajes Corporativas"),
    SETOR_SHOPPINGS("Shoppings"),
    SETOR_LOGISTICA("Logística"),
    URL_FUNDSEXPLORER("https://www.fundsexplorer.com.br/ranking");

    TipoDeFundo(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String nome() {
        return this.nome;
    }
}
