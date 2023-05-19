<template>
  <div>
    <div class="container">
      <div class="h1-wrap">
        <h1>커뮤니티</h1>
      </div>

      <router-view></router-view>
    </div>
    Community View 입니다.
    <br />
    <router-link to="/community/list">List</router-link> |
    <router-link to="/community/detail">Detail</router-link> |
    <router-link to="/community/write">Write</router-link> |
    <router-link to="/community/modify">Modify</router-link>
    <br />
  </div>
</template>

<script>
export default {
  name: "CommunityView",
};
</script>

<style></style>
