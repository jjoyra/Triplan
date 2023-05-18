<template>
  <div>
    FavoAttractionView 입니다.
    <br />
    <router-link to="/mypage/favoattraction/list">List</router-link> |
    <!-- <router-link to="/mypage/favoattraction/detail">Detail</router-link> -->
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "FavoAttractionView",
};
</script>

<style>

</style>