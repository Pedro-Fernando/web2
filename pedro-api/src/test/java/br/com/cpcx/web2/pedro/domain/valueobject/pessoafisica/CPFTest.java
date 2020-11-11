package br.com.cpcx.web2.pedro.domain.valueobject.pessoafisica;

import br.com.cpcx.web2.pedro.domain.exceptions.pessoa.CPFInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CPFTest {


    @Test
    void naoDeveCriarEmailQuandoOMesmoForInvalidoTest() {
        assertThrows(CPFInvalidoException.class, () -> criarCPF(null));
        assertThrows(CPFInvalidoException.class, () -> criarCPF("11111"));
        assertThrows(CPFInvalidoException.class, () -> criarCPF(""));
    }

    @Test
    void deveCriarEmailComSucessoQuandoOEmailForValidoTest() {
        String mockCPF = "000.111.222-33";
        CPF cpfCriado = criarCPF(mockCPF);

        assertEquals(cpfCriado.getCpf(), mockCPF);
    }

    @Test
    void deveLancarErroQuandoAlterarParaUmCpfInvalidoTest() {
        String mockCPF = "111.222.333-44";
        CPF cpfCriado = criarCPF(mockCPF);

        assertEquals(cpfCriado.getCpf(), mockCPF);

        assertThrows(CPFInvalidoException.class, () -> cpfCriado.setCpf(null));
        assertThrows(CPFInvalidoException.class, () -> cpfCriado.setCpf(""));
        assertThrows(CPFInvalidoException.class, () -> cpfCriado.setCpf("55555"));
    }

    @Test
    void deveAlterarCPFComSucessoQuandoCPFNovoForValidoTest() {
        String mockCPFAntigo = "000.111.222-33";
        String mockCPFNovo = "111.222.333-44";

        CPF cpfCriado = criarCPF(mockCPFAntigo);
        cpfCriado.setCpf(mockCPFNovo);

        assertEquals(cpfCriado.getCpf(), mockCPFNovo);
    }

    private CPF criarCPF(String cpf) {
        return new CPF(cpf);
    }
}

