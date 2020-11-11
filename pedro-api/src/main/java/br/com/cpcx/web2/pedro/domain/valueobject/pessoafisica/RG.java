package br.com.cpcx.web2.pedro.domain.valueobject.pessoafisica;

import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.RGInvalidoException;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class RG {
    private String rg;

    public RG(String rg) {
        criaRG(rg);
    }

//    public static void validarRG(String rg) {
//        new RG(rg);
////    }

    private void criaRG(String rg) {
        if (isRGValido(rg)) {
            this.rg = rg;
        } else {
            throw new RGInvalidoException("RG Inválido.");
        }
    }

    private boolean isRGValido(String rg) {
        return nonNull(rg) && validarRGPorRegex(rg);
    }

    private boolean validarRGPorRegex(String rg) {
        //todo foi considerado RGs com 9 digitos
        return rg.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}");
    }


    public void setCpf(String rg) {
        criaRG(rg);
    }

    public static void validarRG(String rg) {

    }
}
