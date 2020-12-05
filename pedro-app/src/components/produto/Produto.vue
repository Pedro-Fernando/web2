<template>

  <section>

    <b-container style="padding: 25px;">
      <b-container>
        <b-row>
          <h1>Produtos</h1>
        </b-row>

        <b-row>
          <b-col cols="10"></b-col>
          <b-col cols="2">
            <b-button variant="success" style="white-space: nowrap;">+ Adicionar</b-button>
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
    editar : function (id){
      console.log(id)
    },
    deletar : function (id){
      console.log(id)
    }
  },
  mounted() {
    axios
        .get('http://localhost:8085/api/produto', {headers: {login: "admin", senha: "123"}})
        .then(response => {
          this.itens = response.data;
          console.log(this.itens)
        }).catch((err) => console.log(err));
  }
}
</script>

<style scoped>

</style>