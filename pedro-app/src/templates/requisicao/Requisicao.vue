<template>
  <section id="requisicao">


    <b-jumbotron border-variant="dark">
      <template #header>Requisições</template>

      <hr class="my-4">

      <div>
        <b-card no-body>
          <b-tabs pills card vertical>
            <b-tab title="GET" active>
              <b-card-text>

                Buscar Todas Pessoas

                <hr class="my-4">

                <b-row class="pb-3">
                  <b-col sm="1">
                    <b-button variant="outline-primary" v-on:click="buscarPessoas">Request</b-button>
                  </b-col>

                  <b-col sm="11" class="text-left" align-v="center">
                    <div style="font-size: 20px;">
                      <strong>URL:</strong> http://localhost:8085/api<strong class="text-success">/pessoa</strong>
                    </div>
                  </b-col>

                </b-row>

                <b-row>
                  <b-col sm="1">
                    <label>Resposta:</label>
                  </b-col>

                  <b-col sm="5">
                    {{responsePessoas}}

                  </b-col>
                </b-row>


              </b-card-text>
            </b-tab>
            <b-tab title="POST">
              <b-card-text>Tab contents 2</b-card-text>

              <hr class="my-4">
              <b-row>
                <b-col sm="2">
                  <label>Corpo Requisição:</label>
                </b-col>
                <b-col sm="10">
                  <b-form-textarea
                      id="textarea-auto-height"
                      placeholder="Auto height textarea"
                      rows="2"
                      max-rows="8"
                  ></b-form-textarea>
                </b-col>
              </b-row>
            </b-tab>
            <b-tab title="PUT">
              <b-card-text>Tab contents 3</b-card-text>
            </b-tab>
            <b-tab title="DELETE">
              <b-card-text>Tab contents 3</b-card-text>
            </b-tab>
          </b-tabs>
        </b-card>
      </div>
    </b-jumbotron>
  </section>

</template>

<script src="./controller.js"></script>
<style scoped src="./estilo.css">

</style>