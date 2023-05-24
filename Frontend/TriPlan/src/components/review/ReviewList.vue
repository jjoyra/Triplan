<template>
  <div>
    <div class="form-wrap">
      <search-input v-on:handle-search="handleSearch" placeholderTxt="제목 내용"></search-input>
      <b-form-group>
        <b-form-radio-group
          id="radio-slots"
          v-model="selected"
          :options="options"
          name="radio-options-slots"
        >
        </b-form-radio-group>
      </b-form-group>
      <router-link class="btn btn-primary" to="/notice/write">글쓰기</router-link>
    </div>

    뭐ㅓ지?
    <b-card img-src="https://placekitten.com/300/300" img-alt="Card image" img-left class="mb-3">
      <b-card-text>
        Some quick example text to build on the card and make up the bulk of the card's content.
      </b-card-text>
    </b-card>

    <b-table :items="reviews" :fields="fields" selectable @row-selected="onRowSelected">
      <!-- <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>

      <template #cell()="data">
        <template v-if="data.item.mustRead">
          <b>{{ data.value }}</b>
        </template>
        <template v-else>
          {{ data.value }}
        </template>
      </template> -->
    </b-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalCount"
      aria-controls="my-table"
      @change="handlePage"
    ></b-pagination>
  </div>
</template>

<script>
import { getReviewList, getTotalReviewCount } from "@/api/review";
import { mapState, mapActions } from "vuex";
import SearchInput from "../common/SearchInput.vue";

const memberStore = "memberStore";
const reviewStore = "reviewStore";

export default {
  components: { SearchInput },
  name: "ReviewList",
  watch: {
    selected(val) {
      let params = {
        word: this.searchWord,
        sortkey: val,
        pgno: this.pgno,
      };
      this.handleReviewList(params, () => {
        this.sortkey = val;
      });
    },
    searchWord(val) {
      let params = {
        word: val,
        sortkey: this.sortkey,
        pgno: this.pgno,
      };
      this.handleReviewList(params, () => {});
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(reviewStore, ["sortkey", "pgno", "reviewSearchWord"]),
  },
  data() {
    return {
      reviews: [],
      currentPage: 1,
      totalCount: 0,
      selected: "create_date",
      searchWord: "",
      // sortkey: "",
      options: [
        { text: "작성일순", value: "create_date" },
        { text: "제목순", value: "title" },
        { text: "추천순", value: "recommend_cnt" },
        { text: "조회순", value: "hit" },
        { text: "별점순", value: "rating" },
        { text: "경비순", value: "price" },
      ],
      fields: [
        { label: "글번호", key: "index" },
        { label: "제목", key: "title" },
        { label: "조회수", key: "hit" },
        { label: "작성자", key: "memberId" },
        { label: "작성일자", key: "createDate" },
      ],
    };
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo"]),
    ...mapActions(reviewStore, ["setSortkey", "setPgno", "setReviewSearchWord"]),
    onRowSelected(selected) {
      console.log("선택", selected);
      // this.$router
      //   .push({
      //     name: "noticedetail",
      //     params: { noticeId: selected[0].noticeId },
      //   })
      //   .catch((err) => {
      //     console.log("notice detail 이동 실패", err);
      //   });
    },
    handleSearch(val) {
      this.searchWord = val;
      //   getReviewList(
      //     { word: val },
      //     ({ data }) => {
      //       console.log(data);
      //       // this.reviews = data.reviews;
      //     },
      //     (err) => {
      //       console.log(err);
      //     }
      //   );
    },
    handlePage(page) {
      //   let params = {
      //     word: this.searchWord,
      //     sortkey: this.sortkey,
      //     pgno: page,
      //   };
      page;
      //   this.handleReviewList(params, () => {
      //     this.setPgno(page);
      //   });
    },
    handleReviewList(params, next) {
      console.log(params, next);
      getReviewList(
        params,
        ({ data }) => {
          console.log("data", data);
          this.reviews = data.reviews;
          next(data);
        },
        (err) => {
          console.log(err);
        }
      );
      getTotalReviewCount(
        params,
        ({ data }) => {
          this.totalCount = data.cnt;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    // changeActive() {
    //   let items = document.querySelectorAll("li.page-item");
    //   items.forEach((item) => {
    //     item.classList.remove("active");
    //   });
    //   items[this.pgno + 1].classList.add("active");
    // },
  },
  created() {
    console.log("크리에이트");
    this.currentPage = this.pgno;
    let params = {
      word: this.searchWord,
      sortkey: this.sortkey,
      pgno: this.pgno,
    };
    this.handleReviewList(params, () => {});
  },
  // updated() {
  //   this.changeActive();
  // },
};
</script>

<style scoped>
.card-img-left {
  width: 10rem;
  height: 10rem;
}
.display-list {
  display: none;
}

.form-wrap {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: flex-end;
  gap: 10px;
  margin-bottom: 1rem;
}
.form-wrap li {
  /* align-self: center; */
  margin-right: 30px;
}

table {
  word-break: keep-all;
  text-align: center;
}

@media (max-width: 950px) {
  .form-wrap li {
    margin-right: 0;
  }
}
</style>
