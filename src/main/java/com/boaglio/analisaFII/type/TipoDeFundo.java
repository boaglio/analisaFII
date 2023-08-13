package com.boaglio.analisaFII.type;

public enum TipoDeFundo {

    SETOR_LAJES_CORPORATIVAS("Lajes Corporativas"),
    SETOR_SHOPPINGS("Shoppings"),
    SETOR_LOGISTICA("Imóveis Industriais e Logísticos"),
    SETOR_PAPEL("Papéis"),
    SETOR_MISTO("Misto"),
    SETOR_FUNDO_DE_FUNDOS("Fundo de Fundos"),
    INDEFINIDO("Indefinido")
    ;
    TipoDeFundo(String nome) {
        this.nome = nome;
    }

    private final String nome;

    public String nome() {
        return this.nome;
    }
}
