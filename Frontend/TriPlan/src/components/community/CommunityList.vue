<template>
  <div>
    <ranking-list title="인기글 TOP3"></ranking-list>
  </div>
</template>

<script>
import RankingList from "../ranking/RankingList.vue";

export default {
  name: "CommunityList",
  components: {
    RankingList,
  },
};
</script>

<style scoped>
.fluid-wrap {
  position: relative;
}
.content-wrap {
  position: relative;
  z-index: 1;
}
.fluid-bg {
  width: 100vw;
  height: 100%;
  position: absolute;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  background: #fafafa;
}
</style>
