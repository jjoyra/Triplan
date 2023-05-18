<template>
  <div>
    FavoPlanView 입니다.
    <br />
    <router-link to="/mypage/favoplan/list">List</router-link> |
    <!-- <router-link to="/mypage/favoplan/detail">Detail</router-link> -->
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "FavoPlanView",
};
</script>

<style>

</style>