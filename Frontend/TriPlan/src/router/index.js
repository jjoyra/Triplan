import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthAdmin = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    // router.push({ name: "login" });
    router.push({
      name: "main",
      params: { showLoginModal: true },
    });
  } else {
    if (checkUserInfo.role === 0) {
      console.log("관리자 로그인 완료");
      next();
    } else {
      console.log("사용자 로그인 완료");
      alert("관리자 권한이 필요한 페이지입니다..");
      router.push({ name: "main" });
    }
    console.log("userinfo", checkUserInfo);
  }
};

// const onlyAuthUser = async (to, from, next) => {
//   const checkUserInfo = store.getters["memberStore/checkUserInfo"];
//   const checkToken = store.getters["memberStore/checkToken"];
//   let token = sessionStorage.getItem("access-token");
//   console.log("로그인 처리 전", checkUserInfo, token);

//   if (checkUserInfo != null && token) {
//     console.log("토큰 유효성 체크");
//     await store.dispatch("memberStore/getUserInfo", token);
//   }
//   if (!checkToken || checkUserInfo === null) {
//     alert("로그인이 필요한 페이지입니다..");
//     // next({ name: "login" });
//     // router.push({ name: "login" });
//     router.push({
//       name: "main",
//       params: { showLoginModal: true },
//     });
//   } else {
//     console.log("로그인 완료");
//     next();
//   }
// };

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
        beforeEnter: onlyAuthAdmin,
        component: () => import(/* webpackChunkName: "notice" */ '../components/notice/NoticeWrite')
      },
      {
        path: 'modify',
        name: 'noticemodify',
        beforeEnter: onlyAuthAdmin,
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
