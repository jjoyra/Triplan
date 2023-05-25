<template>
  <div>
    <div v-if="plan !== undefined && plan !== null">
      <!-- <router-link to="/mypage/:memberId/myplan/detail"> -->
      <b-card
        :img-src="plan.thumbnailUrl"
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
    <div v-else>
      <b-card
        img-src="https://picsum.photos/600/300/?image=25"
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
import FavoButton from "../ui/FavoButton.vue";

export default {
  name: "RankingItemWeb",
  props: {
    plan: Object,
  },
  components: {
    FavoButton,
  },
  data() {
    return {
      memberId: "",
    };
  },
  watch: {
    $route(to) {
      this.memberId = to.params.memberId;
    },
  },
  methods: {
    clickedCard() {
      this.$router
        .push({
          name: "myplandetail",
          params: { userId: this.memberID, planId: this.plan.planId },
        })
        .catch((err) => {
          console.log("myplan detail 이동 실패", err);
        });
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
  width: 360px;
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
