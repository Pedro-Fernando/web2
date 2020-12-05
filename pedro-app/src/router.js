import Vue from 'vue'
import Router from 'vue-router'
import Produto from './components/produto/Produto'
import Pessoa from './components/pessoa/Pessoa'
import Login from './components/login/Login'
import Home from './components/home/Home'


Vue.use(Router)

export default new Router({
    mode: "history",
    routes: [
        {path: '', component: Home},
        {path: '/index', component: Login},
        {path: '/produto', component: Produto},
        {path: '/pessoa', component: Pessoa}
    ]
})