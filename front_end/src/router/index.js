import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../components/login/login.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'login',
    component: login
},
    /*----------------------------------------------------------------*/
    {
        path: '/manager',
        name: 'manager',
        component: () => import('../components/main/manager.vue'),
        children: [{
            path: '/',
            name: '',
            component: () => import('../components/index/manager.vue'),
        }, {
            path: '/classa/info',
            name: 'classaInfo',
            component: () => import('../components/manager/classa/info.vue'),
        }, {
            path: '/teacher/info',
            name: 'teacherInfo',
            component: () => import('../components/manager/teacher/info.vue'),
        },  {
            path: '/manager/order',
            name: 'order',
            component: () => import('../components/manager/productManager/info.vue'),
        },{
            path: '/manager/vipManager',
            name: 'vipManagerInfo',
            component: () => import('../components/manager/vipManager/info.vue'),
        }, {
            path: '/manager/info',
            name: 'managerInfo',
            component: () => import('../components/manager/manager/info.vue'),
        },
        ]
    },
    /*----------------------------------------------------------------*/
    {
        path: '/vip',
        name: 'vip',
        component: () => import('../components/main/vip.vue'),
        children: [{
            path: '/',
            name: '',
            component: () => import('../components/index/vip.vue'),
        }, {
            path: '/vip/gwcs',
            name: 'gwcs',
            component: () => import('../components/vip/gwcs.vue'),
        }]
    }

]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router
