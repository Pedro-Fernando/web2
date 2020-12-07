<template>
  <section>
    <b-container align="left">
      <b-form v-if="true">
        <b-row>
          <b-col>
            <div class="form-group">
              <label>Descrição</label>
              <input type="text"
                     v-model="form.descricao"
                     required
                     class="form-control form-control-md"
                     placeholder="Digite o Nome"/>
            </div>
          </b-col>

          <b-col cols="3">
            <div class="form-group">
              <label>Quantidade em Estoque</label>
              <input type="number"
                     min="1"
                     v-model="form.quantidadeEstoque"
                     required
                     class="form-control form-control-md"/>
            </div>
          </b-col>

        </b-row>

        <b-row>
          <b-col cols="6">
            <label>Idade Permitida</label>
            <input
                class="form-control form-control-md"
                id="input-3"
                required
                min="1"
                v-model="form.idadePermitida"
                type="number"/>
          </b-col>

          <b-col cols="3">
            <div class="form-group">
              <label>Preço de Compra</label>
              <input type="number"
                     min="1"
                     v-model="form.precoCompra"
                     required
                     class="form-control form-control-md"/>
            </div>
          </b-col>

          <b-col cols="3">
            <div class="form-group">
              <label>Proço de Venda</label>
              <input type="number"
                     min="1"
                     v-model="form.precoVenda"
                     required
                     class="form-control form-control-md"/>
            </div>
          </b-col>

        </b-row>

        <b-row>
          <b-col offset="10">
            <b-button-group>
              <b-button variant="outline-danger" v-on:click="limparCamposERetornar()">Cancelar</b-button>
              <b-button variant="success" v-on:click="salvarProduto()">Salvar</b-button>
            </b-button-group>

          </b-col>
        </b-row>

      </b-form>
      <b-card class="mt-3" header="Objeto Produto">
        <pre class="m-0">{{ form }}</pre>
      </b-card>
    </b-container>

  </section>

</template>

<script>
const axios = require('axios');

export default {
  name: "PessoaCadastro",
  data() {
    return {
      form: {
        id: null,
        descricao: '',
        quantidadeEstoque: null,
        idadePermitida: null,
        precoCompra: null,
        precoVenda: true,
      },
    }
  },
  methods: {


    validarCamposObrigatorios() {
      let msgAlerta = []

      if (!this.form.descricao) {
        msgAlerta.push('Descrição')
      }
      if (!this.form.quantidadeEstoque) {
        msgAlerta.push('Quantidade Em Estoque')
      }
      if (!this.form.idadePermitida) {
        msgAlerta.push('Idade máxima permitida')
      }
      if (!this.form.precoCompra) {
        msgAlerta.push('Preço de Compra')
      }
      if (!this.form.precoVenda) {
        msgAlerta.push('Preço de Venda')
      }

      if (msgAlerta.length > 0) {

        let texto = msgAlerta.join(', ')

        this.$bvToast.toast(texto, {
          title: `Falta as seguintes informações`,
          variant: 'warning',
          solid: true
        })

        return true;
      }

      return false;
    },
    limparCamposERetornar() {
      this.form = {
        id: null,
        descricao: '',
        quantidadeEstoque: null,
        idadePermitida: null,
        precoCompra: null,
        precoVenda: true,
      }

      this.$router.history.push("/produto");
    },

    salvarProduto() {
      if (this.validarCamposObrigatorios()) {
        return
      }

      let novoProduto = this.montarObjParaSalvar();

      axios
          .post('http://localhost:8085/api/produto', novoProduto, {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          })
          .then(() => {
            this.$bvToast.toast('Produto Salvo!', {
              title: `Operação realizada com sucesso`,
              variant: 'success',
              solid: true
            })
          }).catch((err) => console.log(err));

      this.$router.history.push("/produto");
    },

    limparCamposDeDocumentosDePessoa() {
      this.form.cpf = null;
      this.form.cnpj = null;
      this.form.rg = null;
    },


    montarObjParaSalvar() {
      return {
        descricao: this.form.descricao,
        quantidadeEstoque: this.form.quantidadeEstoque,
        idadePermitida: this.form.idadePermitida,
        precoCompra: this.form.precoCompra,
        precoVenda: this.form.precoVenda
      }
    }
    ,

    verificarAutenticacao() {
      axios
          .get('http://localhost:8085/api/usuario/autenticar', {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          }).then(response => {
        if (!response.data.isUsuarioAutenticado) {
          this.$router.history.push("pageDefault");
        }
      }).catch((err) => console.log(err));

    }
  },
  mounted() {
    this.verificarAutenticacao();
  }

}

</script>

<style scoped>

</style>