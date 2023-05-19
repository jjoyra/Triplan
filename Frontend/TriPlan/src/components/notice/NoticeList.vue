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

    <b-table
      :items="notices"
      :fields="fields"
      selectable
      @row-selected="onRowSelected">
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>

      <template #cell()="data">
        <template v-if="data.item.mustRead">
          <b>{{ data.value }}</b>
        </template>
        <template v-else>
          {{ data.value }}
        </template>
      </template>
    </b-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalCount"
      aria-controls="my-table"
    ></b-pagination>
    
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { getNoticeList, getTotalNoticeCount } from '@/api/notice';
import SearchInput from '@/components/common/SearchInput.vue';

const noticeStore = "noticeStore";

export default {
  name: "NoticeList",
  data() {
    return {
      notices: [],
      currentPage: 1,
      totalCount: 0,
      selected: 'createDate',
      searchWord: '',
      sortkey: '',
      options: [
        { text: '제목순', value: 'title' },
        { text: '조회순', value: 'hit' },
        { text: '작성일순', value: 'createDate' },
      ],
      fields: [
        { label: '글번호', key: 'index' },
        { label: '제목', key: 'title' },
        { label: '조회수', key: 'hit' },
        { label: '작성자', key: 'memberId' },
        { label: '작성일자', key: 'createDate' },
      ],
    }
  },
  computed: {
    ...mapState(noticeStore, ["pgno"]),
  },
  components: {
    SearchInput,
  },
  methods: {
    ...mapActions(noticeStore, ["setPgno"]),
    onRowSelected(selected) {
      this.setPgno(this.currentPage);
      this.$router.push({
        name: "noticedetail",
        params: { noticeId: selected[0].noticeId },
      }).catch(err => {
        console.log('notice detail 이동 실패', err)
      })
    },
    handleSearch(val) {
      this.searchWord = val;
      getNoticeList(
        { word: val },
        ({data}) => {
          this.notices = data.notices;
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  created() {
    this.currentPage = this.pgno;
    let params = {
      word: this.searchWord,
      sortkey: this.sortkey,
      pgno: this.pgno,
    };
    getNoticeList(
      params,
      ({data}) => {
        this.notices = data.notices;
      },
      (err) => {
        console.log(err);
      }
    );
    getTotalNoticeCount(
      params,
      ({data}) => {
        this.totalCount = data.cnt;
      },
      (err) => {
        console.log(err);
      }
    );
  },
  watch: {
    selected(val) {
      let params = {
        word: this.searchWord,
        sortkey: val,
      };
      getNoticeList(
        params,
        ({data}) => {
          this.notices = data.notices;
          this.sortkey = val;
          this.currentPage = 1;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    searchWord(val) {
      let params = {
        word: val,
      };
      getTotalNoticeCount(
        params,
        ({data}) => {
          this.totalCount = data.cnt;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    currentPage(val) {
        let params = {
        word: this.searchWord,
        sortkey: this.sortkey,
        pgno: val,
      };
      getNoticeList(
        params,
        ({data}) => {
          this.notices = data.notices;
          this.setPgno(val);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }
};
</script>

<style scope>
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