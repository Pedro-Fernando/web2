<template>
  <div class="vue-tempalte">
    <form
        style="width: 500px; margin-left: auto;margin-right: auto; padding: 15px; box-shadow: #000 2px 2px 8px; border-radius: 10px;">
      <h3>Entrar</h3>

      <div class="form-group">
        <label style="position: relative; right: 42%;">Login</label>
        <input type="text" v-model="usuario" class="form-control form-control-lg"/>
      </div>

      <div class="form-group">
        <label style="position: relative; right: 42%;">Senha</label>
        <input type="password" v-model="senha" class="form-control form-control-lg"/>
      </div>

      <div v-on:click="logar()" class="btn btn-info btn-lg btn-block">Sign In</div>
      <p class="forgot-password text-center mt-2 mb-4">
        <b-alert class="bounce-enter-active" v-model="mostrarErro" variant="danger" dismissible>
          {{ textoAlert }}
        </b-alert>
      </p>
    </form>
  </div>
</template>

<script>
const axios = require('axios');

export default {
  name: "Login",
  data() {
    return {
      usuario: null,
      senha: null,
      titulo: null,
      textoAlert: '',
      mostrarErro: false
    }
  },
  methods: {
    gerarMensagemSemInformacaoNosInputs: function (usuario, senha) {
      let usuarioVazio = !usuario;
      let senhaVazia = !senha;

      if (usuarioVazio && senhaVazia) {
        return 'Deve ser informado o login e senha do usuário.';
      } else if (usuarioVazio) {
        return 'Deve ser informado o login do usuário.';
      } else if (senhaVazia) {
        return 'Deve ser informado a senha do usuário.';
      }
    },

    logar() {
      let usuarioVazio = !this.usuario;
      let senhaVazia = !this.senha;

      if (usuarioVazio || senhaVazia) {
        this.textoAlert = this.gerarMensagemSemInformacaoNosInputs(this.usuario, this.senha);
        this.mostrarErro = true;
        return;
      }
      axios
          .get('http://localhost:8085/api/usuario/autenticar', {headers: {login: this.usuario, senha: this.senha}})
          .then(response => {
            if (response.data.isUsuarioAutenticado) {
              sessionStorage.setItem('login', this.usuario);
              sessionStorage.setItem('senha', this.senha);
              this.$router.history.push("produto");
            } else {
              this.textoAlert = 'usuário não cadastrado no sistema.'
              this.mostrarErro = true;
            }

          }).catch((err) => console.log(err));
    }
  }


}
</script>

<style scoped>

.vue-tempalte {
  width: 100%;
  height: 100%;
}

.vertical-center .form-control:focus {
  border-color: #2554FF;
  box-shadow: none;
}

.vertical-center h3 {
  text-align: center;
  margin: 0;
  line-height: 1;
  padding-bottom: 20px;
}

.forgot-password,
.forgot-password a {
  text-align: right;
  font-size: 13px;
  padding-top: 10px;
  color: #7a7a7a;
  margin: 0;
}

.forgot-password a {
  color: #2554FF;
}

.social-icons ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.social-icons ul li {
  display: inline-block;
  zoom: 1;
  width: 65px;
  vertical-align: middle;
  border: 1px solid #e3e8f9;
  font-size: 15px;
  height: 40px;
  line-height: 40px;
  margin-right: 5px;
  background: #f4f6ff;
}

.social-icons ul li a {
  display: block;
  font-size: 1.4em;
  margin: 0 5px;
  text-decoration: none;
}

.social-icons ul li a i {
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

.social-icons ul li a:focus i,
.social-icons ul li a:active i {
  transition: none;
  color: #4cf36b;
}


.bounce-enter-active {
  animation: bounce-in .5s;
}
.bounce-leave-active {
  animation: bounce-in .5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.5);
  }
  100% {
    transform: scale(1);
  }
}

</style>