<template>
  <div class="list-wrap">
    <b-container v-if="attractions && attractions.length != 0">
      <b-card v-for="(attraction, index) in attractions" :key="index">
        <div>
          <span>{{ attraction.title }}</span>
          <br />
          <span>{{ attraction.contentTypeId }}</span>
          <br />
          <span>{{ attraction.addr1 }}</span>
        </div>
        <!-- <div><img src="{{attraction.first_image}}" alt="" /></div> -->
      </b-card>
    </b-container>
    <b-container v-else class="searchEmpty"> <span>여행지 검색 결과</span></b-container>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionList",
  data() {
    return {};
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
  },
  created() {
    this.CLEAR_ATTRACTION_LIST();
  },
  methods: {
    ...mapMutations(attractionStore, ["CLEAR_ATTRACTION_LIST"]),
  },
};
</script>

<style scoped>
.list-wrap {
  width: 100%;
  height: 560px;
  background: #fafafa;
  border-radius: 3px;
  overflow: auto;
}

.card {
  margin-bottom: 10px;
}

span {
  color: #8d9193;
  padding: 10px 0px;
}

.searchEmpty {
  text-align: center;
}
</style>
