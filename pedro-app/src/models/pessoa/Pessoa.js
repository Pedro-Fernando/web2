export default Pessoa;

class Pessoa{
    constructor(id, idResponsavel, tipoPessoa, situacaoPessoa, nome, apelido, cpf, cnpj, rg, dataNascimento) {
        this._id = id;
        this._idResponsavel = idResponsavel;
        this._tipoPessoa = tipoPessoa;
        this._situacaoPessoa = situacaoPessoa;
        this._nome = nome;
        this._apelido = apelido;
        this._cpf = cpf;
        this._cnpj = cnpj;
        this._rg = rg;
        this._dataNascimento = dataNascimento;

    }

    get id(){
        return this._id;
    }
    get idResponsavel(){
        return this._idResponsavel;
    }

    get tipoPessoa() {
        return this._tipoPessoa;
    }

    get situacaoPessoa() {
        return this._situacaoPessoa;
    }

    get nome() {
        return this._nome;
    }

    get apelido() {
        return this._apelido;
    }

    get cpf() {
        return this._cpf;
    }

    get cnpj() {
        return this._cnpj;
    }

    get rg() {
        return this._rg;
    }

    get dataNascimento() {
        return this._dataNascimento;
    }
}
