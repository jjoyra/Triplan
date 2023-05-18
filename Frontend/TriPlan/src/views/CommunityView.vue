<template>
  <div>
    Community View 입니다.
    <br />
    <router-link to="/community/list">List</router-link> |
    <router-link to="/community/detail">Detail</router-link> |
    <router-link to="/community/write">Write</router-link> |
    <router-link to="/community/modify">Modify</router-link>
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "CommunityView",
};
</script>

<style>

</style>