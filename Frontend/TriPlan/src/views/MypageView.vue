<template>
  <div>
    <!-- MyPage View 입니다.
    <br />
    <router-link to="/mypage/ssafy">MyPage Main</router-link> | 
    <router-link to="/mypage/ssafy/myplan/list">MyPlan</router-link> |
    <router-link to="/mypage/ssafy/favoplan">Favorite Plan</router-link> |
    <router-link to="/mypage/ssafy/favoattraction">Favorite Attraction</router-link>
    <br /> -->
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyPageView",
};
</script>

<style scoped></style>
