<template>
  <div>
    <b-modal
      v-if="attraction"
      id="modal-detail"
      size="xl"
      centered
      hide-footer
      :header-border-variant="headerBorderVariant"
      :title="attraction.title"
    >
      <b-container>
        <b-row class="content">
          <b-col>
            <img :src="attraction.firstImage" alt="" />
          </b-col>
          <b-col class="text">
            <div class="type">{{ attraction.contentTypeId | formatContentType }}</div>
            <div>{{ attraction.addr1 }}</div>
            <div>{{ attraction.overview }}</div>
            <div>{{ attraction.tel }}</div>
          </b-col>
        </b-row>
        <div class="card-text-wrap">
          <div class="info">
            <span>
              <b-icon id="content-icon" icon="hand-thumbs-up"></b-icon>
              {{ attraction.recommendCnt }}
            </span>
          </div>
        </div>
      </b-container>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionDetailModal",
  data() {
    return {
      headerBorderVariant: "light",
    };
  },
  computed: {
    ...mapState(attractionStore, ["attraction"]),
  },
  filters: {
    formatContentType(contentTypeId) {
      const contentTypes = [
        { text: "관광지", value: 12 },
        { text: "문화시설", value: 14 },
        { text: "축제/공연/행사", value: 15 },
        { text: "여행코스", value: 25 },
        { text: "레포츠", value: 28 },
        { text: "숙박", value: 32 },
        { text: "쇼핑", value: 38 },
        { text: "음식점", value: 39 },
      ];
      return contentTypes.filter((type) => type.value === contentTypeId)[0].text;
    },
  },
  // methods: {
  //   ...mapActions(attractionStore, ["detailAttraction"]),
  // },
};
</script>

<style scoped>
img {
  width: 400px;
}

.info {
  text-align: right;
}

.modal-header {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  padding: 1rem;
  border-bottom: 1px hidden #dee2e6;
  border-top-left-radius: calc(0.3rem - 1px);
  border-top-right-radius: calc(0.3rem - 1px);
}

.content {
  display: flex;
}

.text > div {
  margin-bottom: 20px;
}

.type {
  color: #51abf3;
}

.container {
  padding: 0rem 5rem;
}

@media (max-width: 950px) {
  .container {
    padding: 0rem 1rem;
  }
}
</style>
