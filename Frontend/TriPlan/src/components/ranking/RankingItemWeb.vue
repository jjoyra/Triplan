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
      <p class="type">{{ plan.title }}</p>
      <p class="title">{{ plan.title }}</p>
    </b-card-text>
  </b-card>
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
};
</script>

<style scoped>
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
