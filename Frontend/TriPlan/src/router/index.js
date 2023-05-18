import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/notice',
    name: 'notice',
    redirect: '/notice/list',
    component: () => import(/* webpackChunkName: "notice" */ '../views/NoticeView.vue'),
    children: [
      {
        path: 'list',
        name: 'noticelist',
        component: () => import(/* webpackChunkName: "notice" */ '../components/notice/NoticeList')
      },
      {
        path: 'detail/:noticeId',
        name: 'noticedetail',
        component: () => import(/* webpackChunkName: "notice" */ '../components/notice/NoticeDetail')
      },
      {
        path: 'write',
        name: 'noticewrite',
        component: () => import(/* webpackChunkName: "notice" */ '../components/notice/NoticeWrite')
      },
      {
        path: 'modify',
        name: 'noticemodify',
        component: () => import(/* webpackChunkName: "notice" */ '../components/notice/NoticeModify')
      },
    ],
  },
  {
    path: '/attraction',
    name: 'attraction',
    redirect: '/attraction/main',
    component: () => import(/* webpackChunkName: "attraction" */ '../views/AttractionView.vue'),
    children: [
      {
        path: 'main',
        name: 'attractionmain',
        component: () => import(/* webpackChunkName: "attraction" */ '../components/attraction/AttractionMain')
      },
    ],
  },
  {
    path: '/community',
    name: 'community',
    redirect: '/community/list',
    component: () => import(/* webpackChunkName: "community" */ '../views/CommunityView.vue'),
    children: [
      {
        path: 'list',
        name: 'communitylist',
        component: () => import(/* webpackChunkName: "community" */ '../components/community/CommunityList')
      },
      {
        path: 'detail',
        name: 'communitydetail',
        component: () => import(/* webpackChunkName: "community" */ '../components/community/CommunityDetail')
      },
      {
        path: 'write',
        name: 'communitywrite',
        component: () => import(/* webpackChunkName: "community" */ '../components/community/CommunityWrite')
      },
      {
        path: 'modify',
        name: 'communitymodify',
        component: () => import(/* webpackChunkName: "community" */ '../components/community/CommunityModify')
      },
    ],
  },
  {
    path: '/review',
    name: 'review',
    redirect: '/review/list',
    component: () => import(/* webpackChunkName: "review" */ '../views/ReviewView.vue'),
    children: [
      {
        path: 'list',
        name: 'reviewlist',
        component: () => import(/* webpackChunkName: "review" */ '../components/review/ReviewList')
      },
      {
        path: 'detail',
        name: 'reviewdetail',
        component: () => import(/* webpackChunkName: "review" */ '../components/review/ReviewDetail')
      },
      {
        path: 'write',
        name: 'reviewwrite',
        component: () => import(/* webpackChunkName: "review" */ '../components/review/ReviewWrite')
      },
      {
        path: 'modify',
        name: 'reviewmodify',
        component: () => import(/* webpackChunkName: "review" */ '../components/review/ReviewModify')
      },
    ],
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import(/* webpackChunkName: "mypage" */ '../views/MypageView.vue'),
    children: [
      {
        path: 'myplan',
        name: 'myplan',
        redirect: '/mypage/myplan/list',
        component: () => import(/* webpackChunkName: "mypage" */ '../views/MyplanView.vue'),
        children: [
          {
            path: 'list',
            name: 'myplanlist',
            component: () => import(/* webpackChunkName: "mypage" */ '../components/mypage/myplan/MyPlanList'),
          },
          {
            path: 'detail',
            name: 'myplandetail',
            component: () => import(/* webpackChunkName: "mypage" */ '../components/mypage/myplan/MyPlanDetail'),
          },
        ],
      },
      {
        path: 'favoplan',
        name: 'favoplan',
        redirect: '/mypage/favoplan/list',
        component: () => import(/* webpackChunkName: "mypage" */ '../views/FavoPlanView.vue'),
        children: [
          {
            path: 'list',
            name: 'planlist',
            component: () => import(/* webpackChunkName: "mypage" */ '../components/mypage/favoplan/FavoPlanList'),
          },
        ],
      },
      {
        path: 'favoattraction',
        name: 'favoattraction',
        redirect: '/mypage/favoattraction/list',
        component: () => import(/* webpackChunkName: "mypage" */ '../views/FavoAttractionView.vue'),
        children: [
          {
            path: 'list',
            name: 'favoattractionlist',
            component: () => import(/* webpackChunkName: "mypage" */ '../components/mypage/favoattraction/FavoAttractionList'),
          },
        ],
      },
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
