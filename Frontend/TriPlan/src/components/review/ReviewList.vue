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
      <router-link class="btn btn-primary" to="/review/write">리뷰 쓰기</router-link>
    </div>

    <b-list-group>
      <b-list-group-item
        v-for="(data, index) in reviews"
        :key="index"
        class="flex-column align-items-start"
      >
        <b-card no-body border-variant="light" class="review-card-wrap">
          <b-row no-gutters>
            <b-col class="img-wrap">
              <b-card-img
                :src="
                  data.thumbnailUrl.length !== 0
                    ? data.thumbnailUrl
                    : 'https://placekitten.com/300/300'
                "
                alt="Image"
              ></b-card-img>
            </b-col>
            <b-col class="content-wrap">
              <router-link :to="`/review/detail/${data.reviewId}`">
                <b-card-body class="content-body">
                  <b-card-title>{{ data.title }}</b-card-title>
                  <b-card-text class="content">
                    <div class="body">
                      <div>
                        <b-icon class="icon" icon="people"></b-icon>
                        {{ companionList[data.companion] }} {{ data.peopleCnt }}인
                      </div>
                      <div class="mobile-no-display">
                        <b-icon class="icon" icon="calendarWeek"></b-icon>
                        {{ data.startDate | dateFormat }} ~ {{ data.endDate | dateFormat }}
                      </div>
                      <div>
                        <b-icon class="icon" icon="coin"></b-icon>
                        {{ data.totalPrice ? '총' : '인당' }}
                        {{ data.price.toLocaleString() }} 원
                      </div>
                      <div v-html="makeStarIcon(data.rating)"></div>
                      <div class="content-data">{{ data.content | substrText }}</div>
                    </div>

                    <div class="footer">
                      <div class="info">
                        <div><b-icon class="icon" icon="person"></b-icon> {{ data.memberId }}</div>
                        <div>
                          <b-icon class="icon" icon="calendarDate"></b-icon>
                          {{ data.modifyDate | dateFormat }}
                        </div>
                      </div>
                      <div class="cnt-info">
                        <div>
                          <b-icon class="icon" icon="eye"></b-icon>
                          {{ data.hit }}
                        </div>
                        <div>
                          <b-icon class="icon" icon="hand-thumbs-up"></b-icon>
                          {{ data.recommendCnt }}
                        </div>
                      </div>
                    </div>
                  </b-card-text>
                </b-card-body>
              </router-link>
            </b-col>
          </b-row>
        </b-card>
      </b-list-group-item>
    </b-list-group>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalCount"
      aria-controls="my-table"
      @change="handlePage"
    ></b-pagination>
  </div>
</template>

<script>
import moment from 'moment';
import { getReviewList, getTotalReviewCount } from '@/api/review';
import { mapState, mapActions } from 'vuex';
import SearchInput from '../common/SearchInput.vue';

const memberStore = 'memberStore';
const reviewStore = 'reviewStore';

export default {
  components: { SearchInput },
  name: 'ReviewList',
  watch: {
    selected(val) {
      this.setSortkey(val);
    },
    sortkey(val) {
      console.log('sortkey', val);
      this.setSortkey(val);
      this.handleReviewList();
    },
    pgno(val) {
      console.log('pgno', val);
      this.setPgno(val);
      this.handleReviewList();
    },
    reviewSearchWord(val) {
      console.log('reviewSearchWord', val);
      this.setReviewSearchWord(val);
      this.handleReviewList();
    },
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
    ...mapState(reviewStore, ['sortkey', 'pgno', 'reviewSearchWord']),
  },
  data() {
    return {
      reviews: [],
      currentPage: 1,
      totalCount: 0,
      selected: 'create_date',
      options: [
        { text: '작성일순', value: 'create_date' },
        { text: '제목순', value: 'title' },
        { text: '추천순', value: 'recommend_cnt' },
        { text: '조회순', value: 'hit' },
        { text: '별점순', value: 'rating' },
        { text: '경비순', value: 'price' },
      ],
      companionList: ['혼자', '친구와', '연인과', '가족과', '부모님과', '배우자와', '반려동물과'],
    };
  },
  methods: {
    ...mapActions(memberStore, ['getUserInfo']),
    ...mapActions(reviewStore, ['setSortkey', 'setPgno', 'setReviewSearchWord']),
    onRowSelected(selected) {
      console.log('선택', selected);
      this.$router.push(`/review/detail/${selected[0].reviewId}`).catch((err) => {
        console.log('notice detail 이동 실패', err);
      });
    },
    handleSearch(val) {
      console.log('검색', val);
      this.setReviewSearchWord(val);
    },
    handlePage(page) {
      console.log('페이지 이동', page);
      this.setPgno(page);
    },
    handleReviewList() {
      let params = {
        sortkey: this.sortkey,
        word: this.reviewSearchWord,
        pgno: this.pgno,
      };
      getReviewList(
        params,
        ({ data }) => {
          this.reviews = data.reviews;
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
    changePageActiveClass() {
      let items = document.querySelectorAll('li.page-item');
      items.forEach((item) => {
        item.classList.remove('active');
      });
      items[this.pgno + 1].classList.add('active');
    },
    makeStarIcon(fill) {
      const unfill = 5 - fill;
      const fillIcon = `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star fill" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star-fill icon b-icon bi"><g data-v-52c730ed=""><path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path></g></svg>`;
      const unfillIcon = `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star icon b-icon bi"><g data-v-52c730ed=""><path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"></path></g></svg>`;
      return `${fillIcon.repeat(fill)}${unfillIcon.repeat(unfill)}`;
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format('YY.MM.DD.');
    },
    substrText(text) {
      return text.substr(0, 60) + '...';
    },
  },
  created() {
    this.currentPage = this.pgno;
    this.handleReviewList();
  },
  updated() {
    this.changePageActiveClass();
  },
};
</script>

<style scoped>
.review-card-wrap {
  width: 100%;
  border: 0;
  position: relative;
  margin-right: 12rem;
  background: transparent;
}
.content-wrap .content-body {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.content,
.content .body,
.content .footer {
  display: flex;
}

.content {
  flex-direction: column;
  justify-content: space-between;
}
.content .body {
  flex-direction: column;
  gap: 10px;
}
.content .footer {
  flex-direction: row;
  justify-content: space-between;
}
.content .footer .info {
  display: flex;
  flex-direction: row;
}
.content .footer .cnt-info {
  display: flex;
  flex-direction: row;
  gap: 10px;
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

.mobile-no-display {
  display: none;
}

.icon {
  color: #51abf3;
  margin-right: 0.25rem;
}

.footer,
.footer .icon {
  color: #ababab;
}

.footer .info {
  flex: 0.6;
  justify-content: space-between;
}
.footer .cnt-info {
  flex: 0.4;
  justify-content: flex-end;
}

.info:after {
  display: none;
}

.card-title {
  padding-right: 1rem;
}

.list-group {
  gap: 1rem;
  margin-bottom: 2rem;
}
.list-group-item {
  border-radius: 3px;
  padding: 1.25rem;
}
.list-group-item + .list-group-item,
.list-group-item:last-child {
  border-top-width: thin;
}
.list-group-item a {
  text-decoration: none;
}
.list-group-item a:hover {
  color: #383838;
}

.img-wrap {
  flex-grow: 0.3;
}
.img-wrap .card-img {
  object-fit: cover;
  height: 100%;
  width: 100%;
}
.content-wrap {
  flex-grow: 0.7;
}

@media (max-width: 990px) {
  .img-wrap {
    flex: 0.4;
  }
  .content-wrap {
    flex: 0.6;
  }
  .card-title {
    padding: 0;
    font-size: 1.25rem;
  }

  .review-card-wrap {
    margin: 0;
  }
  .form-wrap li {
    margin-right: 0;
  }
  .content-wrap .content-body {
    padding: 0.5rem 1rem;
  }
  .content .body {
    gap: 5px;
  }
  .content .footer {
    flex-direction: column;
  }
  .footer .cnt-info {
    justify-content: flex-start;
  }
  .content-data {
    display: none;
  }
}

@media (min-width: 991px) {
  .img-wrap {
    flex: 0.5;
    margin-right: 1rem;
  }
  .card-body {
    padding: 0;
  }
  .content {
    height: 100%;
    font-size: 1.1rem;
  }
  .content > .body {
    margin-bottom: 0.5rem;
  }
  .list-group {
    flex-direction: row;
    flex-wrap: wrap;
  }
  .list-group-item {
    flex: 0.5;
  }
  .list-group-item:nth-child(odd):last-child {
    flex: 1;
    padding-right: 1rem;
  }
  .review-card-wrap,
  .review-card-wrap > div {
    height: 100%;
  }
}
</style>
