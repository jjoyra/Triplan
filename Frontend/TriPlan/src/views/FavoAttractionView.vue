<template>
  <div>
    FavoAttractionView 입니다.
    <br />
    <router-link to="/mypage/ssafy/favoattraction/list">List</router-link> |
    <!-- <router-link to="/mypage/ssafy/favoattraction/detail">Detail</router-link> -->
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "FavoAttractionView",
};
</script>

<style></style>
