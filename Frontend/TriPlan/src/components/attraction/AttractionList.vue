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
        <b-button id="modal-btn" @click="openModal">상세보기</b-button>
        <div>
          <img :src="attraction.firstImage" alt="" />
        </div>
      </b-list-group-item>
    </div>
    <div v-else class="searchEmpty"><span>검색 결과가 없습니다.</span></div>
    <attraction-detail-modal></attraction-detail-modal>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import AttractionDetailModal from "./AttractionDetailModal.vue";

const attractionStore = "attractionStore";

export default {
  name: "AttractionList",
  data() {
    return {};
  },
  components: {
    AttractionDetailModal,
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
  },
  created() {
    this.CLEAR_ATTRACTION_LIST();
  },
  methods: {
    ...mapMutations(attractionStore, ["CLEAR_ATTRACTION_LIST"]),
    openModal() {
      this.$refs.detailModal.$refs.modal.show();
    },
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

.col-xl-4 {
  padding: 0px;
}

.list-wrap {
  width: 100%;
  height: 560px;
  overflow: auto;
}

/* .card {
  margin-bottom: 10px;
} */

.list-group-item {
  border-bottom: 0.3px rgba(0, 0, 0, 0.125);
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
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

img {
  width: 120px;
}
</style>
