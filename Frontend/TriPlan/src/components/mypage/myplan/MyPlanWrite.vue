<template>
  <div id="wrap">
    <div class="side-wrap">
      <attraction-search-bar></attraction-search-bar>
      <attraction-list></attraction-list>
    </div>
    <div class="side-wrap write-plan">
      <b-form @submit="onPlanSubmit">
        <b-row>
          <b-col
            ><b-form-datepicker
              v-model="form.startDate"
              placeholder="연도 . 월 . 일"
            ></b-form-datepicker
          ></b-col>
          <b-col
            ><b-form-datepicker
              v-model="form.endDate"
              placeholder="연도 . 월 . 일"
            ></b-form-datepicker
          ></b-col>
        </b-row>
        <div>
          <b-form-input v-model="form.title" placeholder="플랜 이름을 작성해주세요."></b-form-input>
          <b-button type="submit">등록</b-button>
        </div>
        <div class="plan-list">
          <b-card
            img-src="https://placekitten.com/300/300"
            img-alt="Card image"
            img-left
            class="mb-3"
          >
            <b-card-text>
              Some quick example text to build on the card and make up the bulk of the card's
              content.
            </b-card-text>
          </b-card>
        </div>
      </b-form>
    </div>
    <kakao-map></kakao-map>
  </div>
</template>

<script>
import KakaoMap from "@/components/attraction/KakaoMap.vue";
import AttractionSearchBar from "@/components/attraction/AttractionSearchBar.vue";
import AttractionList from "@/components/attraction/AttractionList.vue";

export default {
  name: "MyPlanWrite",
  data() {
    return {
      form: {
        title: "",
        startDate: "",
        endDate: "",
      },
    };
  },
  components: {
    KakaoMap,
    AttractionSearchBar,
    AttractionList,
  },
  methods: {
    onPlanSubmit(event) {
      event.preventDefault();
      alert(JSON.stringify(this.form));
    },
  },
};
</script>

<style scoped>
#wrap {
  /* display: flex; */
}

.side-wrap {
  background-color: #fff;
  z-index: 21;
  width: 30%;
  position: absolute;
  padding: 1rem;
}

.write-plan {
  right: 0%;
  height: 100%;
}

.plan-list {
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

.card {
  /* width: 70%; */
}

img {
  width: 220px;
  height: 50%;
}
</style>
