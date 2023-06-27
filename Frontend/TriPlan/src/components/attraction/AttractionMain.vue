<template>
  <div>
    <ranking-list
      title="인기 여행지 TOP3"
      :bg="true"
      :isPublic="true"
      :attractionList="tops"
    ></ranking-list>
    <div>
      <attraction-search-bar></attraction-search-bar>
      <b-row>
        <b-col xl="8"
          ><kakao-map :attractions="attractions" :peekList="peekList"></kakao-map
        ></b-col>
        <b-col><attraction-list @clicked-attraction="openOverlay"></attraction-list></b-col>
      </b-row>
    </div>
    <attraction-detail-modal></attraction-detail-modal>
  </div>
</template>

<script>
import AttractionList from "./AttractionList.vue";
import KakaoMap from "./KakaoMap.vue";
import RankingList from "@/components/ranking/RankingList.vue";
import AttractionSearchBar from "./AttractionSearchBar.vue";
import AttractionDetailModal from "./AttractionDetailModal.vue";
import { getRecommendTop3AttractionList } from "@/api/attraction";
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionMain",
  data() {
    return {
      peekList: null,
      tops: [],
    };
  },
  components: {
    RankingList,
    AttractionSearchBar,
    KakaoMap,
    AttractionList,
    AttractionDetailModal,
  },
  created() {
    getRecommendTop3AttractionList(
      ({ data }) => {
        this.tops = data.attractions;
        console.log(data);
      },
      (error) => {
        console.error(error);
      }
    );
  },
  computed: {
    ...mapState(attractionStore, ["attractions", "worldCupTops"]),
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
