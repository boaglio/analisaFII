package com.boaglio.analisaFII.type;

public enum TipoDeFundo {

    SETOR_TITULOS_E_VAL_MOB("Títulos e Val. Mob."),
    SETOR_LAJES_CORPORATIVAS("Lajes Corporativas"),
    SETOR_SHOPPINGS("Shoppings"),
    SETOR_LOGISTICA("Logística");
    TipoDeFundo(String nome) {
        this.nome = nome;
    }

    private final String nome;

    public String nome() {
        return this.nome;
    }
}
