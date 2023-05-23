<template>
  <div class="fluid-wrap">
    <div class="content-wrap">
      <h2>{{ title }}</h2>
      <div class="ranking-item-wrap web">
        <!-- <ranking-item-web v-for="item in items" :key="item"></ranking-item-web> -->
        <ranking-item-web></ranking-item-web>
        <ranking-item-web></ranking-item-web>
        <ranking-item-web></ranking-item-web>
      </div>

      <div class="ranking-item-wrap mobile">
        <!-- <ranking-item-mobile v-for="item in items" :key="item"></ranking-item-mobile> -->
        <ranking-item-mobile></ranking-item-mobile>
        <ranking-item-mobile></ranking-item-mobile>
        <ranking-item-mobile></ranking-item-mobile>
      </div>
    </div>
    <div :class="{ 'fluid-bg': bg }"></div>
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
    bg: Boolean,
  },
  data() {
    return {
      items: [],
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
  margin-bottom: 2rem;
  padding: 2rem 0;
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
  display: flex;
  flex-direction: row;
  gap: 10px;
}

.mobile {
  display: none;
}

.hover-box-shadow:hover {
  cursor: pointer;
  box-shadow: 1px 1px 20px #e1e1e19b;
}

@media (max-width: 750px) {
  .content-wrap {
    padding: 1rem 0;
  }
  .web {
    display: none;
  }
  .mobile {
    display: flex;
    flex-direction: row;
    gap: 10px;
  }
}
</style>
