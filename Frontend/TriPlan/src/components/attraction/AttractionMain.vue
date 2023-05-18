<template>
  <div>
    <b-container fluid class="ranking">
      <attraction-ranking-list></attraction-ranking-list>
    </b-container>
    <b-container>
      <attraction-search-bar></attraction-search-bar>
      <b-row>
        <b-col xl="8"><kakao-map></kakao-map></b-col>
        <b-col xl="4"><attraction-list></attraction-list></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import AttractionList from "./AttractionList.vue";
import KakaoMap from "./KakaoMap.vue";
import AttractionRankingList from "./AttractionRankingList.vue";
import AttractionSearchBar from "./AttractionSearchBar.vue";
export default {
  name: "AttractionMain",
  components: {
    AttractionRankingList,
    AttractionSearchBar,
    KakaoMap,
    AttractionList,
  },
};
</script>

<style scoped>
.ranking {
  padding: 0px;
}
</style>
