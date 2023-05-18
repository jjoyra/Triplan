<template>
  <div class="bg">
    <div class="container">
      <div class="ranking-list">
        <h2>인기 여행지 TOP 3</h2>
        <div class="ranking-item-web">
          <!-- <attraction-ranking-item-web
        v-for="attraction in attractions"
        :key="attraction.content_id"
        :attraction="attaraction"
      ></attraction-ranking-item-web> -->
          <attraction-ranking-item-web></attraction-ranking-item-web>
          <attraction-ranking-item-web></attraction-ranking-item-web>
          <attraction-ranking-item-web></attraction-ranking-item-web>
        </div>

        <div class="ranking-item-mobile">
          <attraction-ranking-item-mobile></attraction-ranking-item-mobile>
          <attraction-ranking-item-mobile></attraction-ranking-item-mobile>
          <attraction-ranking-item-mobile></attraction-ranking-item-mobile>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AttractionRankingItemMobile from "./AttractionRankingItemMobile.vue";
import AttractionRankingItemWeb from "./AttractionRankingItemWeb.vue";

export default {
  name: "AttractionRankingList",
  components: {
    AttractionRankingItemWeb,
    AttractionRankingItemMobile,
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
.bg {
  background-color: #fafafa;
  padding: 15px 0px;
}

.ranking-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: wrap;
}

.ranking-item-web,
.ranking-item-mobile {
  margin: 15px 0px;
  flex-direction: row;
}

.ranking-item-web {
  display: flex;
  gap: 50px;
}
.ranking-item-mobile {
  display: none;
}

@media (max-width: 750px) {
  .ranking-item-web {
    display: none;
  }
  .ranking-item-mobile {
    display: flex;
    justify-content: space-between;
  }
}
</style>
