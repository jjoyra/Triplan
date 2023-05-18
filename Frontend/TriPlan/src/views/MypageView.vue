<template>
  <div>
    MyPage View 입니다.
    <br />
    <router-link to="/mypage">MyPage Main</router-link> | 
    <router-link to="/mypage/myplan/list">MyPlan</router-link> |
    <router-link to="/mypage/favoplan">Favorite Plan</router-link> |
    <router-link to="/mypage/favoattraction">Favorite Attraction</router-link>
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "MyPageView",
};
</script>

<style>

</style>