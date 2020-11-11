package br.com.cpcx.web2.pedro.domain.enumeration;

public enum ESituacaoPessoa {
    ATIVO("Ativo"), INATIVO("Inativo");

    private String situacao;

    ESituacaoPessoa(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
}
