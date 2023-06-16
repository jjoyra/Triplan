<template>
  <div>
    <template v-if="review">
      <b-row class="mb-3 ml-2 mr-2 justify-content-end">
        <button class="btn btn-primary" @click="movePage('/review')">목록</button>
      </b-row>
      <b-row class="mb-2">
        <b-col>
          <b-card class="review-card">
            <b-card-title>{{ review.title }} <favo-button></favo-button></b-card-title>
            <b-card-text>
              <div class="card-text-wrap">
                <div class="info">
                  <router-link :to="`/mypage/${review.memberId}`">
                    <img id="profileImg" src="../../assets/300.png" alt="300.png" />
                    {{ review.memberId }}
                  </router-link>
                  <div class="info-review-wrap">
                    <span> <b-icon id="content-icon" icon="eye"></b-icon> {{ review.hit }} </span>
                    <span>
                      <b-icon id="content-icon" icon="hand-thumbs-up"></b-icon>
                      {{ review.recommendCnt }}
                    </span>
                    <span>
                      <b-icon id="content-icon" icon="calendar-date"></b-icon>
                      {{ review.modifyDate | dateFormat }}
                    </span>
                  </div>
                </div>
                <div class="content">
                  <div class="article-wrap">
                    <h5>INFO</h5>
                    <div class="info-wrap">
                      <ul>
                        <li>
                          <div>만족도</div>
                          <div class="icon" v-html="makeStarIcon(review.rating)"></div>
                        </li>
                        <li>
                          <div>누구와</div>
                          <div>
                            #{{ companionList[review.companion] }} ({{ review.peopleCnt }}인)
                          </div>
                        </li>
                        <li>
                          <div>여행경비</div>
                          <div>
                            {{ review.totalPrice ? "총" : "인당" }}
                            {{ review.price.toLocaleString() }} 원
                          </div>
                        </li>
                        <li>
                          <div>기간</div>
                          <div>
                            {{ review.startDate | dateFormat }} ~ {{ review.endDate | dateFormat }}
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>

                  <div class="article-wrap">
                    <h5>COMMENT</h5>
                    <div>{{ review.content }}</div>
                  </div>

                  <div class="article-wrap">
                    <h5>REVIEW</h5>
                    <div class="attraction-review" v-if="courseList.length !== 0">
                      <ul>
                        <course-list-item
                          v-for="course in courseList"
                          :key="course.contentId"
                          :course="course"
                          :comment="course.comment"
                        ></course-list-item>
                      </ul>
                    </div>
                  </div>

                  <div class="article-wrap">
                    <h5>MAP</h5>
                    <div class="attraction-map">
                      <!-- 카카오맵 -->
                      <kakao-map :courseList="courseList"></kakao-map>
                    </div>
                  </div>

                  <div class="btn-wrap">
                    <button class="btn-icon-wrap btn btn-round">
                      <b-icon id="content-icon" icon="hand-thumbs-up"></b-icon>
                      <span>추천하기</span>
                    </button>
                    <button class="btn btn-round-primary" @click="movePage('/review')">
                      목록으로
                    </button>
                  </div>
                </div>
              </div>
            </b-card-text>
          </b-card>
        </b-col>
      </b-row>
    </template>
  </div>
</template>

<script>
import moment from "moment";
import { getReviewDetail } from "@/api/review";
import FavoButton from "../ui/FavoButton.vue";
import CourseListItem from "../board/CourseListItem.vue";
import KakaoMap from "../attraction/KakaoMap.vue";

export default {
  name: "ReviewDetail",
  components: {
    FavoButton,
    CourseListItem,
    KakaoMap,
  },
  data() {
    return {
      review: null,
      courseList: [],
      companionList: ["혼자", "친구와", "연인과", "가족과", "부모님과", "배우자와", "반려동물과"],
    };
  },
  created() {
    const reviewId = this.$route.params.reviewId;
    console.log(reviewId);
    if (reviewId.length !== 0) {
      getReviewDetail(
        reviewId,
        ({ data }) => {
          console.log("courseList", data.courseList);
          this.review = data.review;
          this.courseList = data.courseList;
        },
        (err) => console.log(err)
      );
    }
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD.");
    },
    substrText(text) {
      return text.substr(0, 60) + "...";
    },
  },
  methods: {
    makeStarIcon(fill) {
      const unfill = 5 - fill;
      const fillIcon = `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star fill" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star-fill icon b-icon bi"><g data-v-52c730ed=""><path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path></g></svg>`;
      const unfillIcon = `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star icon b-icon bi"><g data-v-52c730ed=""><path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"></path></g></svg>`;
      return `${fillIcon.repeat(fill)}${unfillIcon.repeat(unfill)}`;
    },
    movePage(url) {
      if (this.$route.path != url) this.$router.push(url);
    },
  },
};
</script>

<style scoped>
h5 {
  color: #33a3ff;
}
.review-card {
  border: none;
}
.card-body {
  padding: 0 0.3rem 2rem;
}

.card-text-wrap {
  display: flex;
  flex-direction: column;
}
.card-text-wrap .content {
  padding: 0 3rem;
}
.card-title {
  position: relative;
  padding-right: 2rem;
}
.info {
  padding: 0.8rem;
  border-bottom: 0.3px solid #e7e7e7;
  margin-bottom: 1.5rem;
}
.info,
.info-review-wrap {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  flex-wrap: wrap;
  color: #a1a1a1;
  align-items: baseline;
}

.info #profileImg {
  width: 30px;
  height: 30px;
  display: inline-block;
  border-radius: 50%;
  margin-right: 0.3rem;
}

.article-wrap {
  margin-bottom: 3rem;
}
.article-wrap:last-child {
  margin-bottom: 0;
}
.article-wrap > div {
  padding: 1rem 0.25rem;
}
.article-wrap div ul {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}
.article-wrap .info-wrap ul li {
  display: flex;
  flex-direction: row;
}
.article-wrap .info-wrap ul li div:first-child {
  flex: 0.2;
  font-weight: bold;
}
.article-wrap .info-wrap ul li div:last-child {
  flex: 0.8;
}

.article-wrap .attraction-review {
  padding-left: 2.5rem;
}

.content .icon {
  color: #51abf3;
}

.info a {
  color: #a1a1a1;
}
.info:after {
  display: none;
}

.btn-wrap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
}
.btn-wrap .btn.btn-round svg {
  margin-right: 0.5rem;
}
.btn-wrap .btn.btn-round,
.btn-wrap .btn-round-primary {
  width: 8rem;
}
.btn-icon-wrap {
}

@media (max-width: 750px) {
  .card-text-wrap .content {
    padding: 0 0.5rem;
  }

  .favo-icon-wrap {
    top: 0;
    right: 0;
  }
}
</style>
