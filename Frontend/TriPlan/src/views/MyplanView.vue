<template>
  <div>
    MyPlan View 입니다.
    <br />
    <router-link to="/mypage/myplan/list">List</router-link> |
    <router-link to="/mypage/myplan/detail">Detail</router-link>
    <br />

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "MyPlanView",
};
</script>

<style>

</style>