import Vue from 'vue'
import Router from 'vue-router'
import Cart from '@/components/Cart.vue'
import App from '@/App.vue'


Vue.use(Router)

export default new Router({
  routes: [
    // eslint-disable-next-line spaced-comment
    /*{
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },*/
    {
      path: '/Cart',
      name: 'Cart',
      component: Cart
    }, {
      path: '/',
      name: 'App',
      component: App
    }
    // eslint-disable-next-line spaced-comment
    /*{
      path: '/b',
      name: 'Bbb',
      component: (resolve) => require(['@/pages/bbb.vue'], resolve)
    }*/
  ],
  mode: 'history'
})
