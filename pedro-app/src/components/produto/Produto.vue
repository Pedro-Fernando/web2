<template>

  <section>

    <div>
      <b-card title="Card Title" no-body>
        <b-card-header header-tag="nav">
          <b-nav card-header tabs>
            <b-nav-item to="/produto" exact exact-active-class="active">Produto</b-nav-item>
            <b-nav-item to="/pessoa" exact exact-active-class="active">Pessoa</b-nav-item>
          </b-nav>
        </b-card-header>

        <b-card-body>
          <router-view></router-view>
        </b-card-body>
      </b-card>
    </div>

    <b-container style="padding: 25px;">
      <b-container>
        <b-row>
          <h1>Produtos</h1>
        </b-row>

        <b-row align="left">
          <b-col cols="4">
            <div class="form-group">
              <label>Descricao</label>
              <input type="text"
                     v-model="form.descricao"
                     required
                     class="form-control form-control-md"
                     placeholder="Digite a Descrição"/>
            </div>
          </b-col>

          <b-col cols="4">
            <div class="form-group">
              <label>Preço Máximo</label>
              <input type="text"
                     v-model="form.precoMaximo"
                     required
                     class="form-control form-control-md"
                     placeholder="Digite Valor"/>
            </div>
          </b-col>
        </b-row>

        <b-row>
          <b-col cols="1">
            <b-button v-on:click="buscarPorFiltro()" variant="info" style="white-space: nowrap;">
              Buscar</b-button>
          </b-col>

          <b-col offset="9" cols="2">
            <b-button variant="success" style="white-space: nowrap;" v-on:click="cadastrar()"><strong>+</strong> Novo Registro</b-button>
          </b-col>
        </b-row>

        <b-row>
          <b-col cols="12" style="margin-top: 20px;">
            <b-table class="text-left" striped hover :fields="fields" :items="itens">
              <template v-slot:cell(acoes)="data">

                <b-button-group>
                  <b-button variant="info" @click="editar(data.item.id)">Editar
                  </b-button>
                  <b-button variant="outline-danger" @click="deletar(data.item.id)">Deletar
                  </b-button>
                </b-button-group>

              </template>
            </b-table>
          </b-col>
        </b-row>

      </b-container>


    </b-container>

  </section>
</template>

<script>
const axios = require('axios');

export default {
  name: "Produto",
  data() {
    return {
      form:{
        descricao: null,
        precoMaximo: null
      },
      itens: [],
      fields: [
        {
          key: 'descricao',
          label: 'Descrição'
        },
        {
          key: 'quantidadeEstoque',
          label: 'Quantidade Estoque',
          sortable: true,
        },
        {
          key: 'precoCompra',
          label: 'Preço Compra'
        },
        {
          key: 'precoVenda',
          label: 'Preço Venda'
        },
        {
          key: 'idadePermitida',
          label: 'Idade Permitida'
        },
        {
          key: 'acoes',
          label: 'Ações'
        }
      ]

    }
  },
  methods: {
    cadastrar() {
      this.$router.history.push("/produtoCadastro");
    },
    editar: function (id) {
      console.log(id)
    },
    deletar: function (id) {
      axios
          .delete('http://localhost:8085/api/produto/' + id, {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          }).then(() => {



        this.$bvToast.toast('Produto removida com Sucesso!', {
          title: `Operação realizada!`,
          variant: 'success',
          solid: true
        })


        setTimeout(function () {
          window.location.reload()
        }, 900);


      }).catch((err) => console.log(err));
    },

    montarURL() {
      let url = 'http://localhost:8085/api/produto';
      if (this.form.descricao && this.form.precoMaximo) {
        url += '?descricao=' + this.form.descricao + '&precoMaximo=' + this.form.precoMaximo;
      }

      if (this.form.descricao && !this.form.precoMaximo){
        url += '?descricao=' + this.form.descricao;
      }

      if (!this.form.descricao && this.form.precoMaximo) {
        url += '?precoMaximo=' + this.form.precoMaximo;
      }

      return url;
    },

    buscarPorFiltro() {

      let url = this.montarURL();

      axios
          .get(url, {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          })
          .then(response => {

            this.itens = response.data;
            console.log(this.itens)
          }).catch((err) => console.log(err));
    }
  },
  mounted() {
    axios
        .get('http://localhost:8085/api/usuario/autenticar', {
          headers: {
            login: sessionStorage.getItem('login'),
            senha: sessionStorage.getItem('senha')
          }
        }).then(response => {
          if (!response.data.isUsuarioAutenticado){
            this.$router.history.push("pageDefault");
          }
        }).catch((err) => console.log(err));

    axios
        .get('http://localhost:8085/api/produto', {
          headers: {
            login: sessionStorage.getItem('login'),
            senha: sessionStorage.getItem('senha')
          }
        })
        .then(response => {
          this.itens = response.data;
        }).catch((err) => console.log(err));
  }
}
</script>

<style scoped>

</style>