import Produto from "@/models/produto/Produto";

export default ProdutoBuilder;

class ProdutoBuilder{
    constructor() {
        this.reset();
    }

    reset(){
        this._produto = new Produto();
        return this;
    }

    id(id){
        this._produto._id = id;
        return this;
    }

    descricao(descricao){
        this._produto._descricao = descricao;
        return this;
    }

    quantidadeEstoque(quantidadeEstoque){
        this._produto._quantidadeEstoque = quantidadeEstoque;
        return this;
    }

    idadePermitida(idadePermitida){
        this._produto._idadePermitida = idadePermitida;
        return this;
    }

    precoCompra(precoCompra){
        this._produto._precoCompra = precoCompra;
        return this;
    }

    precoVenda(precoVenda){
        this._produto._precoVenda = precoVenda;
        return this;
    }

    build(){
        Object.freeze(this._produto);
        return this._produto;
    }
}