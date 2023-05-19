<template>
  <div class="list-wrap">
    <b-container v-if="attractions && attractions.length != 0">
      <div v-for="(attraction, index) in attractions" :key="index">
        {{ attraction.title }} {{ attraction.contentTypeId }} {{ attraction.sidoCode }}
        {{ attraction.gugunCode }}
      </div>
    </b-container>
    <b-container v-else>여행지 검색 결과</b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionList",
  data() {
    return {};
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
  },
};
</script>

<style scoped>
.list-wrap {
  width: 100%;
  height: 560px;
  background: #fafafa;
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
