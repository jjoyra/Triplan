<template>
  <div class="mobile-wrap">
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
  width: 150px;
  height: 90px;
  border-radius: 3px 3px 0px 0px;
  background-color: #e6e6e6;
  position: relative;
}

.mobile-wrap:hover {
  box-shadow: 1px 1px 20px #e1e1e19b;
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
  width: 150px;
  height: 60px;
  border-radius: 0px 0px 3px 3px;
  background-color: #ffffff;
  padding-left: 3px;
}
</style>
