import Pessoa from "@/models/pessoa/Pessoa";

export default PessoaBuilder;

class PessoaBuilder {
    constructor() {
        this.reset();
    }

    reset() {
        this._pessoa = new Pessoa();
        return this;
    }

    id(id) {
        this._pessoa._id = id;
        return this;
    }

    idResponsavel(idResponsavel) {
        this._pessoa._idResponsavel = idResponsavel;
        return this;
    }

    tipoPessoa(tipoPessoa) {
        this._pessoa._tipoPessoa = tipoPessoa;
        return this;
    }

    situacaoPessoa(situacaoPessoa) {
        this._pessoa._situacaoPessoa = situacaoPessoa;
        return this;
    }

    nome(nome) {
        this._pessoa._nome = nome;
        return this;
    }

    apelido(apelido) {
        this._pessoa._apelido = apelido;
        return this;
    }

    cpf(cpf) {
        this._pessoa._cpf = cpf;
        return this;
    }

    cnpj(cnpj) {
        this._pessoa._cnpj = cnpj;
        return this;
    }

    rg(rg) {
        this._pessoa._rg = rg;
        return this;
    }

    dataNascimento(dataNascimento) {
        this._pessoa._dataNascimento = dataNascimento;
        return this;
    }

    build() {
        Object.freeze(this._pessoa);
        return this._pessoa;
    }
}