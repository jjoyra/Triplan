<template>
  <div>
    <div v-if="plan !== undefined && plan !== null">
      <!-- <router-link to="/mypage/:memberId/myplan/detail"> -->
      <b-card
        :img-src="plan.thumbnailUrl ? plan.thumbnailUrl : 'https://picsum.photos/600/300/?image=25'"
        img-top
        tag="article"
        class="mb-2 hover-box-shadow"
        @click="clickedCard"
      >
        <b-card-text>
          <favo-button></favo-button>
          <p class="type">{{ plan.startDate }}</p>
          <p class="title">{{ plan.title }}</p>
        </b-card-text>
      </b-card>
      <!-- </router-link> -->
    </div>
    <div v-else-if="attraction !== undefined && attraction !== null">
      <!-- <router-link to="/mypage/:memberId/myplan/detail"> -->
      <b-card
        :img-src="
          attraction.firstImage ? attraction.firstImage : 'https://picsum.photos/600/300/?image=25'
        "
        img-top
        tag="article"
        class="mb-2 hover-box-shadow"
        @click="openModal(attraction.contentId)"
      >
        <b-card-text>
          <favo-button></favo-button>
          <p class="type">{{ attraction.contentTypeId | formatContentType }}</p>
          <p class="title">{{ attraction.title }}</p>
        </b-card-text>
      </b-card>
      <!-- </router-link> -->
    </div>
    <div v-else>
      <b-card
        img-src="@/assets/loding_img02.png"
        img-alt="Image"
        img-top
        tag="article"
        class="mb-2 hover-box-shadow"
      >
        <b-card-text>
          <favo-button></favo-button>
          <p class="type">여행지 유형</p>
          <p class="title">여행지 제목</p>
        </b-card-text>
      </b-card>
    </div>
  </div>
</template>

<script>
import FavoButton from '../ui/FavoButton.vue';
import { mapActions } from 'vuex';

const attractionStore = 'attractionStore';

export default {
  name: 'RankingItemWeb',
  props: {
    plan: Object,
    attraction: Object,
  },
  components: {
    FavoButton,
  },
  data() {
    return {
      memberId: '',
    };
  },
  watch: {
    $route(to) {
      this.memberId = to.params.memberId;
    },
  },
  filters: {
    formatContentType(contentTypeId) {
      const contentTypes = [
        { text: '관광지', value: 12 },
        { text: '문화시설', value: 14 },
        { text: '축제/공연/행사', value: 15 },
        { text: '여행코스', value: 25 },
        { text: '레포츠', value: 28 },
        { text: '숙박', value: 32 },
        { text: '쇼핑', value: 38 },
        { text: '음식점', value: 39 },
      ];
      return contentTypes.filter((v) => v.value == contentTypeId)[0].text;
    },
  },
  methods: {
    ...mapActions(attractionStore, ['detailAttraction']),
    clickedCard() {
      this.$router
        .push({
          name: 'myplandetail',
          params: { userId: this.memberID, planId: this.plan.planId },
        })
        .catch((err) => {
          console.log('myplan detail 이동 실패', err);
        });
    },
    openModal(contentId) {
      this.detailAttraction(contentId);
      this.$bvModal.show('modal-detail');
    },
  },
};
</script>

<style scoped>
a:hover {
  text-decoration: none;
  color: #383838;
}

.hover-box-shadow:hover {
  cursor: pointer;
  box-shadow: 1px 1px 20px #e1e1e19b;
}

img {
  flex-shrink: 0;
  height: 180px;
  object-fit: cover;
}

.card-text {
  position: relative;
}

.card-text .type,
.card-text .title {
  margin-bottom: 0;
}

.card-text .type {
  color: #bbb;
  font-size: 1rem;
  margin-bottom: 0.25rem;
}
.card-text .title {
  font-size: 1.25rem;
}
</style>
