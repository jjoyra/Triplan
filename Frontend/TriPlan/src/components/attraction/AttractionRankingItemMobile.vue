<template>
  <div>
    <div class="thumbnail">
      <div><img src="" alt="" /></div>
      <div class="favo">
        <favo-button></favo-button>
      </div>
    </div>
    <div class="desc">
      <div class="type"><span>여행지 유형</span></div>
      <div><span>여행지 제목</span></div>
    </div>
  </div>
</template>

<script>
import FavoButton from "../ui/FavoButton.vue";

export default {
  name: "AttractionRankingItemMobile",
  components: {
    FavoButton,
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
  width: 140px;
  height: 75px;
  border-radius: 3px 3px 0px 0px;
  background-color: #e6e6e6;
  position: relative;
}

img {
  width: 100%;
}

.favo {
  position: absolute;
  top: 60%;
  left: 80%;
}

.desc {
  width: 140px;
  height: 50px;
  border-radius: 0px 0px 3px 3px;
  background-color: #ffffff;
  padding-left: 3px;
}
</style>
