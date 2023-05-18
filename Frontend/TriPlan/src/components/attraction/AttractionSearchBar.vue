<template>
  <div>
    <b-row class="text-center">
      <b-col cols="7">
        <search-input
          v-on:handle-search="handleSearch"
          placeholderTxt="여행지를 찾아보세요"
        ></search-input>
      </b-col>
      <b-col>
        <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
      </b-col>
      <b-col>
        <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
      </b-col>
    </b-row>
    <div class="select-btn">
      <select-button :contentTypes="contentTypes"></select-button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import SearchInput from "../common/SearchInput.vue";
import SelectButton from "../ui/SelectButton.vue";

export default {
  name: "AttractionSearchBar",
  components: {
    SearchInput,
    SelectButton,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      contentTypes: [
        { text: "전체", value: 0 },
        { text: "관광지", value: 12 },
        { text: "문화시설", value: 14 },
        { text: "축제/공연/행사", value: 15 },
        { text: "여행코스", value: 25 },
        { text: "레포츠", value: 28 },
        { text: "숙박", value: 32 },
        { text: "쇼핑", value: 38 },
        { text: "음식점", value: 39 },
      ],
      sidos: [],
      guguns: [],
    };
  },
  created() {
    this.sidos = [{ text: "시도", value: null }];
    this.guguns = [{ text: "구군", value: null }];

    http.get(`/attraction/sido`).then(({ data }) => {
      data.sidos.forEach((sido) => {
        this.sidos.push({ text: sido.sidoName, value: sido.sidoCode });
      });
    });
  },
  computed: {},
  methods: {
    handleSearch(val) {
      console.log("여행지 검색", val);
    },
  },
};
</script>

<style scoped>
.select-btn {
  padding: 20px 0px;
}
</style>
