<template>
  <div>
    <div class="h1-wrap"><h1>여행지 둘러보기</h1></div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "AttractionView",
};
</script>

<style scpoe>
.h1-wrap {
  margin: 2rem auto;
}
</style>
