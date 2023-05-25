<template>
  <div class="fluid-wrap">
    <div class="content-wrap">
      <div class="h2-wrap">
        <h2>{{ title }}</h2>
        <template v-if="isMore">
          <router-link :to="link"
            ><b-icon class="rightArrow" icon="ChevronRight"></b-icon
          ></router-link>
        </template>
      </div>
      <template v-if="isPublic || isMyInfo">
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
      </template>
      <template v-else>
        <div>비공개 유저입니다.</div>
      </template>
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
    isMore: Boolean,
    link: String,
    isPublic: Boolean,
    isMyInfo: Boolean,
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

.h2-wrap {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}
.h2-wrap h2 {
  margin-bottom: 0;
}
.h2-wrap svg {
  font-size: 1.2rem;
}
.h2-wrap a:hover {
  color: #383838;
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
