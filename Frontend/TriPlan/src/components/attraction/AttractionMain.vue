<template>
  <div>
    <ranking-list title="인기 여행지 TOP3" bg="true"></ranking-list>
    <div>
      <attraction-search-bar></attraction-search-bar>
      <b-row>
        <b-col xl="8"><kakao-map :attractions="attractions"></kakao-map></b-col>
        <b-col xl="4"><attraction-list></attraction-list></b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import AttractionList from "./AttractionList.vue";
import KakaoMap from "./KakaoMap.vue";
import RankingList from "@/components/ranking/RankingList.vue";
import AttractionSearchBar from "./AttractionSearchBar.vue";
import { mapState } from "vuex";

const attractionStore = "attractionStore";
export default {
  name: "AttractionMain",
  components: {
    RankingList,
    AttractionSearchBar,
    KakaoMap,
    AttractionList,
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
  },
};
</script>

<style scoped></style>
