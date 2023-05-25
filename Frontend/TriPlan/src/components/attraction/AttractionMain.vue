<template>
  <div>
    <ranking-list title="인기 여행지 TOP3" :bg="true"></ranking-list>
    <div>
      <attraction-search-bar></attraction-search-bar>
      <b-row>
        <b-col xl="8"
          ><kakao-map :attractions="attractions" :peekList="peekList"></kakao-map
        ></b-col>
        <b-col xl="4"><attraction-list @clicked-attraction="openOverlay"></attraction-list></b-col>
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
  data() {
    return {
      peekList: null,
    };
  },
  components: {
    RankingList,
    AttractionSearchBar,
    KakaoMap,
    AttractionList,
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
  },
  methods: {
    openOverlay(contentid) {
      this.peekList = contentid;
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 560px;
}

.list-wrap {
  height: 560px;
}

@media (max-width: 950px) {
  #map {
    height: 453px;
  }
}
</style>
