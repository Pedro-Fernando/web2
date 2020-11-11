package br.com.cpcx.web2.pedro.domain.enumeration;

public enum  ETipoPessoa {
    FISICA("Fisica"),
    JURIDICA("Juridica");

    private String tipoPessoa;

    ETipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
}
