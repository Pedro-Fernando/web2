// import produtos from "@/extensions/api/produtos";
import Requisicao from '../../templates/requisicao/Requisicao';
// const axios = require('axios');

export default {
    name: "Home.vue",
    components: {
        Requisicao
    },
    data: function (){
        return{
            titulo: null
        }
    },
    methods(){

    }
    // mounted() {
        // axios
        //     .get('http://localhost:8085/api/pessoa',{headers:{login: 'admin', senha: 123}})
        //     .then(response => (this.titulo = response.data))
    // }
}