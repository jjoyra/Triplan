<template>
  <div>
    Review View 입니다.
    <br />
    <router-link to="/review/list">List</router-link> |
    <router-link to="/review/detail">Detail</router-link> |
    <router-link to="/review/write">Write</router-link> |
    <router-link to="/review/modify">Modify</router-link>
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "ReviewView",
};
</script>

<style>

</style>