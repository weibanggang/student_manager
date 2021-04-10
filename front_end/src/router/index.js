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
            path: '/course/info',
            name: 'courseInfo',
            component: () => import('../components/manager/course/info.vue'),
        }, {
            path: '/student/info',
            name: 'studentInfo',
            component: () => import('../components/manager/student/info.vue'),
        },{
            path: '/satisfaction/info',
            name: 'satisfactionInfo',
            component: () => import('../components/manager/satisfaction/info.vue'),
        }, {
            path: '/classScheduleCard/info',
            name: 'classScheduleCardInfo',
            component: () => import('../components/manager/classScheduleCard/info.vue'),
        },{
            path: '/teacherCheck/info',
            name: 'teacherCheckInfo',
            component: () => import('../components/manager/teacherCheck/info.vue'),
        },{
            path: '/studentCheck/info',
            name: 'studentCheckInfo',
            component: () => import('../components/manager/studentCheck/info.vue'),
        },{
            path: '/manager/info',
            name: 'managerInfo',
            component: () => import('../components/manager/manager/info.vue'),
        },
        ]
    },
    /*----------------------------------------------------------------*/
    {
        path: '/classScheduleCard',
        name: 'classScheduleCard',
        component: () => import('../components/index/index.vue')

    }

]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router
