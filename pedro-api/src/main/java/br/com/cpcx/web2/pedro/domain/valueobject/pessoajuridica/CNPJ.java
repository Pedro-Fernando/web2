package br.com.cpcx.web2.pedro.domain.valueobject.pessoajuridica;

import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.CNPJInvalidoException;

import static java.util.Objects.nonNull;

public class CNPJ {

    private String cnpj;

    public CNPJ(String cpf) {
        criaCPF(cpf);
    }

    private void criaCPF(String cpf) {
        if (isCPFValido(cpf)) {
            this.cnpj = cpf;
        } else {
            throw new CNPJInvalidoException("CNPJ Inválido.");
        }
    }

    private boolean isCPFValido(String cpf) {
        return nonNull(cpf) && validarCPFPorRegex(cpf);
    }

    private boolean validarCPFPorRegex(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}");
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        criaCPF(cnpj);
    }

}
