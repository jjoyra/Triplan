<template>
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
</template>

<script>
import FavoButton from "../ui/FavoButton.vue";

export default {
  name: "RankingItemMobile",
  components: {
    FavoButton,
  },
};
</script>

<style scoped>
.card-text {
  position: relative;
}
.card-text .type,
.card-text .title {
  margin-bottom: 0;
  margin-bottom: 0.25rem;
}

.card-text .type {
  color: #bbb;
  font-size: 0.8rem;
}
</style>
