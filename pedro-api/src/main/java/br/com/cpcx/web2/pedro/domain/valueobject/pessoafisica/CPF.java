package br.com.cpcx.web2.pedro.domain.valueobject.pessoafisica;

import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.CPFInvalidoException;

import static java.util.Objects.nonNull;

public class CPF {

    private String cpf;

    public CPF(String cpf) {
        criaCPF(cpf);
    }

    public static void validarCPF(String cpf) {
        new CPF(cpf);
    }

    private void criaCPF(String cpf) {
        if (isCPFValido(cpf)) {
            this.cpf = cpf;
        } else {
            throw new CPFInvalidoException("CPF Inválido.");
        }
    }

    private boolean isCPFValido(String cpf) {
        return nonNull(cpf) && validarCPFPorRegex(cpf);
    }

    private boolean validarCPFPorRegex(String cpf) {
        return cpf.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}-?\\d{2}");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        criaCPF(cpf);
    }
}
