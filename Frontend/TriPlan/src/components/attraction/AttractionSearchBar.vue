<template>
  <div>
    <b-row class="text-center">
      <b-col cols="7">
        <search-input
          v-on:handle-search="handleSearch"
          placeholderTxt="여행지를 찾아보세요"
        ></search-input>
      </b-col>
      <select-sido-gugun @select-gugun="handleAttractionSearch"></select-sido-gugun>
    </b-row>
    <div class="select-btn">
      <select-button
        :contentTypes="contentTypes"
        @select-content-type="handleAttractionSearch"
      ></select-button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import SearchInput from "../common/SearchInput.vue";
import SelectSidoGugun from "../common/SelectSidoGugun.vue";
import SelectButton from "../ui/SelectButton.vue";

const attractionStore = "attractionStore";

export default {
  name: "AttractionSearchBar",
  components: {
    SearchInput,
    SelectButton,
    SelectSidoGugun,
  },
  data() {
    return {
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
      searchParams: {
        sidoCode: null,
        gugunCode: null,
        contentTypeList: [],
        title: null,
      },
    };
  },
  created() {},
  computed: {},
  methods: {
    ...mapActions(attractionStore, ["getAttractionList"]),

    handleAttractionSearch(params) {
      this.searchParams = {
        ...this.searchParams,
        ...params,
      };
      console.log(this.searchParams);
      this.getAttractionList(this.searchParams);
    },

    handleSearch(val) {
      this.searchParams.title = val;

      this.getAttractionList(this.searchParams);
    },
  },
};
</script>

<style scoped>
.select-btn {
  padding: 20px 0px;
}
</style>
