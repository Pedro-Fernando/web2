const axios = require('axios');

export default {
    name: "Requisicao",

    data() {
        return {
            responsePessoas: ''
        }
    },
    methods: {
       buscarPessoas(){
           axios
               .get('http://localhost:8085/api/pessoa',{headers:{login: 'admin', senha: 123}})
               .then(response => (this.responsePessoas = response.data))
       }
    }
}