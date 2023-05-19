<template>
  <div class="fluid-wrap">
    <div class="content-wrap">
      <h2>{{ title }}</h2>
      <div class="ranking-item-wrap web">
        <ranking-item-web></ranking-item-web>
      </div>

      <div class="ranking-item-wrap mobile">
        <ranking-item-mobile></ranking-item-mobile>
      </div>
    </div>
    <div class="fluid-bg"></div>
  </div>
</template>

<script>
import RankingItemMobile from "./RankingItemMobile.vue";
import RankingItemWeb from "./RankingItemWeb.vue";

export default {
  name: "RankingList",
  components: {
    RankingItemMobile,
    RankingItemWeb,
  },
  props: {
    title: String,
  },
  data() {
    return {
      attractions: [],
    };
  },
  created() {},
  methods: {},
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

.web {
  display: block;
}

.mobile {
  display: none;
}

@media (max-width: 750px) {
  .web {
    display: none;
  }
  .mobile {
    display: block;
  }
}
</style>
