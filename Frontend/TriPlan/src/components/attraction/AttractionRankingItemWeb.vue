<template>
  <div>
    <div class="thumbnail">
      <div><img src="" alt="" /></div>
      <div>
        <!-- <medal-emoji></medal-emoji> -->
        <div class="desc">
          <div class="type"><span>여행지 유형</span></div>
          <div><h2>여행지 제목</h2></div>
        </div>
        <div class="favo">
          <favo-button></favo-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FavoButton from "../ui/FavoButton.vue";
// import MedalEmoji from "../ui/MedalEmoji.vue";

export default {
  name: "AttractionRankingItemWeb",
  components: {
    FavoButton,
    // MedalEmoji,
  },
  data() {
    return {
      message: "",
    };
  },
  props: {
    attraction: Object,
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.thumbnail {
  width: 340px;
  height: 190px;
  border-radius: 3px;
  background-color: #ffffff;
  position: relative;
}

.thumbnail:hover {
  box-shadow: 1px 1px 20px #e1e1e19b;
}

img {
  width: 100%;
}

.favo {
  position: absolute;
  bottom: 15%;
  right: 10%;
}

.desc {
  position: absolute;
  bottom: 10%;
  left: 10%;
}

.type {
  color: #bbbbbb;
  margin-bottom: 2px;
}
</style>
