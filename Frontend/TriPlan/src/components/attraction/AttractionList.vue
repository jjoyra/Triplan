<template>
  <div class="list-wrap">
    <div v-if="attractions && attractions.length != 0">
      <b-list-group-item v-for="(attraction, index) in attractions" :key="index">
        <div>
          <span class="title">{{ attraction.title }}</span>
          <br />
          <span class="type">{{ attraction.contentTypeId }}</span>
          <br />
          <span>{{ attraction.addr1 }}</span>
        </div>
        <div>
          <img :src="attraction.firstImage" alt="" />
        </div>
      </b-list-group-item>
    </div>
    <div v-else class="searchEmpty"><span>여행지 검색 결과</span></div>
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
.title {
  font-size: 1rem;
  color: #383838;
}

.type {
  font-size: 0.8rem;
  color: #51abf3;
}

.list-wrap {
  width: 100%;
  height: 560px;
  border-radius: 3px;
  overflow: auto;
}

/* .card {
  margin-bottom: 10px;
} */

.list-group-item {
  border: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
  display: flex;
  justify-content: space-between;
}

span {
  color: #8d9193;
  padding: 10px 0px;
}

.searchEmpty {
  padding: 30px 0px;
  width: 100%;
  height: 560px;
  text-align: center;
  background: #fafafa;
}

img {
  width: 120px;
}
</style>
