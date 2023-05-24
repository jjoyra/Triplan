<template>
  <!-- Review View 입니다.
    <br />
    <router-link to="/review/list">List</router-link> |
    <router-link to="/review/detail">Detail</router-link> |
    <router-link to="/review/write">Write</router-link> |
    <router-link to="/review/modify">Modify</router-link>
    <br /> -->
  <div class="container">
    <div class="h1-wrap">
      <h1>플랜 리뷰</h1>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "ReviewView",
};
</script>

<style></style>
