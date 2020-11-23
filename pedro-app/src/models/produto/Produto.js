export default Produto;

class Produto{
    constructor(id, descricao, quantidadeEstoque, idadePermitida, precoCompra, precoVenda) {
        this._id = id;
        this._descricao = descricao;
        this._quantidadeEstoque = quantidadeEstoque;
        this._idadePermitida = idadePermitida;
        this._precoCompra = precoCompra;
        this._precoVenda = precoVenda;

    }

        get id(){
            return this._id;
        }
        get descricao(){
            return this._descricao;
        }
        get quantidadeEstoque(){
            return this._quantidadeEstoque;
        }
        get idadePermitida(){
            return this._idadePermitida;
        }
        get precoCompra(){
            return this._precoCompra;
        }
        get precoVenda(){
            return this._precoVenda;
        }

}
