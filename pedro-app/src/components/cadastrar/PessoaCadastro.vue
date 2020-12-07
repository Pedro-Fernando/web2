<template>
  <section>
    <b-container align="left">
      <b-form v-if="true">
        <b-row>
          <b-col>
            <div class="form-group">
              <label>Nome</label>
              <input type="text"
                     v-model="form.nome"
                     required
                     class="form-control form-control-md"
                     placeholder="Digite o Nome"/>
            </div>
          </b-col>

          <b-col cols="5">
            <div class="form-group">
              <label>Apelido</label>
              <input type="text"
                     v-model="form.apelido"
                     required
                     class="form-control form-control-md"
                     placeholder="Digite o Apelido"/>
            </div>
          </b-col>

        </b-row>

        <b-row>
          <b-col cols="7">
            <label>Data de Nascimento</label>
            <input
                v-on:change="calcularIdade()"
                class="form-control form-control-md"
                id="input-3"
                required
                v-model="form.dataNascimento"
                type="date"/>
          </b-col>

          <b-col cols="3">
            <b-form-group
                id="input-tipo"
                label="Tipo"
                label-for="input-4">
              <b-form-select id="input-4" required v-model="form.tipo" :options="tiposDePessoaDS"
                             v-on:change="limparCamposDeDocumentosDePessoa()"></b-form-select>
            </b-form-group>
          </b-col>

          <b-col>
            <b-form-group
                id="input-situacao"
                label="Situação:"
                label-for="input-5">
              <b-form-checkbox v-model="form.situacaoEscolhida" required name="check-button" switch>
                {{ form.situacaoEscolhida ? 'Ativo' : 'Inativo' }}
              </b-form-checkbox>
            </b-form-group>
          </b-col>

        </b-row>

        <b-row v-show="form.tipo">
          <b-col cols="6" v-show="form.tipo === 'FISICA'">
            <label>CPF</label>
            <input type="text" v-model="form.cpf" class="form-control form-control-md"/>
          </b-col>

          <b-col cols="6" v-show="form.tipo === 'FISICA'">
            <label>RG</label>
            <input type="text" v-model="form.rg" class="form-control form-control-md"/>
          </b-col>

          <b-col cols="7" v-show="form.tipo === 'JURIDICA'">
            <label>CNPJ</label>
            <input type="text" v-model="form.cnpj" class="form-control form-control-md"/>
          </b-col>
        </b-row>

        <b-row style="margin-top: 10px;">
          <b-col v-if="showResponsavel">
            <label>Responsável</label>
            <b-form-select required v-model="form.idResponsavel" :options="responsaveisDS"></b-form-select>
          </b-col>

        </b-row>

        <b-row>
          <b-col offset="10">
            <b-button-group>
              <b-button variant="outline-danger" v-on:click="limparCamposERetornar()">Cancelar</b-button>
              <b-button variant="success" v-on:click="salvarPessoa()">Salvar</b-button>
            </b-button-group>

          </b-col>
        </b-row>

      </b-form>
      <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
      </b-card>
    </b-container>

  </section>

</template>

<script>

const axios = require('axios');

function montarResponsavelDS(pessoa) {
  return {
    value: pessoa.id,
    text: pessoa.nome
  };
}

function carregarDSResponsaveis(data) {
  let dsResponsaveis = data.map(montarResponsavelDS)
  return dsResponsaveis;
}


export default {
  name: "PessoaCadastro",
  data() {
    return {
      form: {
        nome: '',
        apelido: '',
        dataNascimento: null,
        tipo: null,
        situacaoEscolhida: true,
        idResponsavel: null,
        rg: null,
        cnpj: null,
        cpf: null
      },

      showResponsavel: false,

      tiposDePessoaDS: [
        {value: 'FISICA', text: 'Física'},
        {value: 'JURIDICA', text: 'Jurídica'}
      ],
      responsaveisDS: []
    }
  },
  methods: {

    validarCamposObrigatorios() {
      let msgAlerta = []
      if (!this.form.nome) {
        msgAlerta.push('Nome')
      }
      if (!this.form.apelido) {
        msgAlerta.push('Apelido')
      }
      if (!this.form.dataNascimento) {
        msgAlerta.push('Data de nascimento')
      }
      if (!this.form.tipo) {
        msgAlerta.push('Tipo')
      } else {
        if (this.form.tipo === 'FISICA') {
          if (!this.form.cpf) {
            msgAlerta.push('CPF')
          }
          if (!this.form.rg) {
            msgAlerta.push('RG')
          }

        } else {
          if (!this.form.cnpj) {
            msgAlerta.push('CNPJ')
          }
        }
      }

      if (this.showResponsavel) {
        if (this.form.idResponsavel === null) {
          msgAlerta.push('Respnsável')
        }
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
        nome: '',
        apelido: '',
        dataNascimento: null,
        tipo: null,
        situacaoEscolhida: true,
        idResponsavel: null,
        rg: null,
        cnpj: null,
        cpf: null
      }


      this.$router.history.push("pessoa");
    },

    salvarPessoa() {
      if (this.validarCamposObrigatorios()){
        return
      }

      let novaPessoa = this.montarObjParaSalvar();

      axios
          .post('http://localhost:8085/api/pessoa', novaPessoa, {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          })
          .then(response => {
            this.responsaveisDS = carregarDSResponsaveis(response.data);
          }).catch((err) => console.log(err));

      this.$router.history.push("pessoa");
    },

    limparCamposDeDocumentosDePessoa() {
      this.form.cpf = null;
      this.form.cnpj = null;
      this.form.rg = null;
    },

    calcularIdade() {
      if (this.form.dataNascimento === null) {
        this.form.dataNascimento = null;
        this.form.idResponsavel = null;
      }

      let anoAtual = new Date().getFullYear();
      let dataInformada = this.form.dataNascimento.split('-')

      this.showResponsavel = (anoAtual - dataInformada[0]) <= 18;
    },

    montarObjParaSalvar() {
      return {
        nome: this.form.nome,
        apelido: this.form.apelido,
        situacaoPessoa: this.form.situacaoEscolhida ? 'ATIVO' : 'INATIVO',
        dataNascimento: this.form.dataNascimento,
        tipoPessoa: this.form.tipo,
        idResponsavel: this.form.idResponsavel,
        cpf: this.form.cpf,
        cnpj: this.form.cnpj,
        rg: this.form.rg
      }
    },

    verificarAutenticacao() {

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

    },

    buscarResponsaveisDeMaior() {
      axios
          .get('http://localhost:8085/api/pessoa/maiorIdade', {
            headers: {
              login: sessionStorage.getItem('login'),
              senha: sessionStorage.getItem('senha')
            }
          })
          .then(response => {
            this.responsaveisDS = carregarDSResponsaveis(response.data);
          }).catch((err) => console.log(err));
    },


  },
  mounted() {
    console.log('Cadastro')
    this.verificarAutenticacao();
    this.buscarResponsaveisDeMaior();
  }

}

</script>

<style scoped>

</style>