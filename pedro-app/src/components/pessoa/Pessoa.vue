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

    <b-container fluid="md" class="w-100">
      <b-container fluid="md" class="w-100">
        <b-row>
          <h1>Pessoas</h1>
        </b-row>

        <b-row align="left">
          <b-col cols="4">
            <div class="form-group">
              <label>ID do Responsável</label>
              <input type="text"
                     v-model="form.idResponsavel"
                     class="form-control form-control-md"
                     placeholder="Digite o ID do responsável"/>
            </div>
          </b-col>

          <b-col cols="4">
            <div class="form-group">
              <label>Tipo</label>
              <b-form-select id="input-4" required v-model="form.tipo" :options="tiposDePessoaDS"></b-form-select>
            </div>
          </b-col>
        </b-row>

        <b-row>
          <b-col cols="1">
            <b-button v-on:click="buscarPorFiltro()" variant="info" style="white-space: nowrap;">
              Buscar
            </b-button>
          </b-col>

          <b-col offset="9" cols="2">
            <b-button variant="success" v-on:click="cadastrar()" style="white-space: nowrap;"><strong>+</strong> Novo
              Registro
            </b-button>
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
  name: "Pessoa",
  data() {
    return {
      form: {
        idResponsavel: null,
        tipo: null
      },
      itens: [],
      fields: [
        {
          key: 'nome',
          label: 'Nome'
        },
        {
          key: 'apelido',
          label: 'Apelido'
        },
        {
          key: 'dataNascimento',
          label: 'Nascimento'
        },
        {
          key: 'tipo',
          label: 'Tipo'
        },
        {
          key: 'situacao',
          label: 'Situação'
        },
        {
          key: 'acoes',
          label: 'Ações'
        }
      ],
      tiposDePessoaDS: [
        {value: 'FISICA', text: 'Física'},
        {value: 'JURIDICA', text: 'Jurídica'}
      ]

    }
  },
  methods: {
    cadastrar() {
      this.$router.history.push("pessoaCadastro");
    },

    editar: function (id) {
      this.$router.history.push({path: "pessoaCadastro/" + id});
    },
    deletar: function (id) {
      axios
          .delete('http://localhost:8085/api/pessoa/' + id, {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          }).then(() => {


        this.$bvToast.toast('Pessoa removida com Sucesso!', {
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
      let url = 'http://localhost:8085/api/pessoa';
      if (this.form.idResponsavel && this.form.tipo) {
        url += '?idResponsavel=' + this.form.idResponsavel + '&tipo=' + this.form.tipo;
      }

      if (this.form.idResponsavel && !this.form.tipo){
        url += '?idResponsavel=' + this.form.idResponsavel;
      }

      if (!this.form.idResponsavel && this.form.tipo) {
        url += '?tipo=' + this.form.tipo;
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
      if (!response.data.isUsuarioAutenticado) {
        this.$router.history.push("pageDefault");
      }
    }).catch((err) => console.log(err));

    axios
        .get('http://localhost:8085/api/pessoa', {
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
}
</script>

<style scoped>

</style>