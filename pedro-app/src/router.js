import Vue from 'vue'
import Router from 'vue-router'
import Produto from './components/produto/Produto'
import Pessoa from './components/pessoa/Pessoa'
import Login from './components/login/Login'
import Home from './components/home/Home'
import PageDefault from './components/page-default/AcessoInvalido'
import PessoaCadastro from './components/cadastrar/PessoaCadastro'
import ProdutoCadastro from './components/cadastrar/ProdutoCadastro'
import PessoaEditar from './components/cadastrar/PessoaEditar'


Vue.use(Router)

export default new Router({
    mode: "history",
    routes: [
        {path: '', component: Home},
        {path: '/index', component: Login},
        {path: '/produto', component: Produto},
        {path: '/pessoa', component: Pessoa},
        {path: '/pageDefault', component: PageDefault},
        {path: '/produtoCadastro', component: ProdutoCadastro},
        {path: '/pessoaCadastro', component: PessoaCadastro},
        {path: '/pessoaCadastro/:id', component: PessoaEditar, props: true}
    ]
})