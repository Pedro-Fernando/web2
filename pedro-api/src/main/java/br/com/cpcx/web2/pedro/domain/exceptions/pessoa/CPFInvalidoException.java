package br.com.cpcx.web2.pedro.domain.exceptions.pessoa;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String s) {
        super(s);
    }
}
