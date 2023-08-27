<template>
  <div>
    Review Write Vue 입니다.

    <!-- 
      member_id       "test"
      people_cnt      3
      title           "경주로 가자"
      content         "부모님이랑 당일치기로 경주 다녀왔다!! 첨성대 빵 꼭 먹어줘"
      recommend_cnt   0
      hit             0
      create_date     now()
      modify_date     now()
      start_date      "2023-05-29"
      end_date        "2023-05-29"
      plan_id         1
      rating          4
      companion       4
      is_total_price  false
      price           170000
      thumbnail_url   imgurl
    -->
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit.stop.prevent="onSubmit" @reset="onReset">
          <!-- 제목 -->
          <b-form-group id="title-group" label="제목" label-for="title">
            <b-form-input
              id="title"
              v-model="reviewDto.title"
              type="text"
              placeholder="제목"
            ></b-form-input>
          </b-form-group>

          <!-- 만족도 -->
          <b-form-group id="rating-wrap" label="만족도" label-for="rating">
            <span class="rating" v-for="index in 5" :key="index" @click="starRating(index)">
              <span v-if="index < reviewDto.rating"><span v-html="fillStar"></span></span>
              <span v-if="index >= reviewDto.rating"><span v-html="star"></span></span>
            </span>
          </b-form-group>

          <!-- 누구와 -->
          <b-form-group id="companion" label="동행자" label-for="companion">
            <div class="companion-radio-wrap">
              <b-form-radio
                v-model="reviewDto.companion"
                v-for="(companion, index) in companionList"
                :key="index"
                :value="companion.value"
                @change="changeCompanion"
                button
              >
                {{ companion.text }}
              </b-form-radio>
            </div>
          </b-form-group>

          <!-- 여행 경비 -->

          <!-- 여행 기간 -->

          <!-- 코멘트 -->

          <!-- 리뷰 -->

          <b-form-group id="content-group" label="내용" label-for="content">
            <b-form-textarea
              id="content"
              v-model="reviewDto.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-form-group label="총 금액" label-for="checkbox-1">
            <b-form-checkbox
              id="checkbox-1"
              v-model="reviewDto.isTotalPrice"
              name="checkbox-1"
              unchecked-value="not_accepted"
            >
              총액
            </b-form-checkbox>
          </b-form-group>

          <b-form-group label="금액" label-for="checkbox-1">
            <b-form-input
              id="number"
              v-model="reviewDto.price"
              type="number"
              placeholder="여행에 사용한 비용"
            ></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'"
            >글작성</b-button
          >
          <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from 'vuex';

const memberStore = 'memberStore';

export default {
  name: 'ReviewWrite',
  data() {
    return {
      type: 'register',
      reviewDto: {
        memberId: 'ssafy',
        peopleCnt: 3,
        title: 'testtest',
        content: 'test22222222222222222222',
        startDate: '2013-05-24',
        endDate: '2024-03-29',
        planId: 2,
        rating: 1,
        companion: 2,
        isTotalPrice: true,
        price: 400000,
        thumbnailUrl: 'http://tong.visitkorea.or.kr/cms/resource/31/2826331_image2_1.jpg',
      },
      courseList: [
        {
          planId: 2,
          contentId: '2847895',
          comment: '공원이 좋더라',
        },
        {
          planId: 2,
          contentId: '2826337',
          comment: '재밌었음',
        },
        {
          planId: 2,
          contentId: '2821304',
          comment: '오션캠프 좋다',
        },
      ],
      planList: [],
      companionList: [
        { text: '혼자', value: 0 },
        { text: '친구와', value: 1 },
        { text: '연인과', value: 2 },
        { text: '가족과', value: 3 },
        { text: '부모님과', value: 4 },
        { text: '배우자와', value: 5 },
        { text: '반려동물과', value: 6 },
      ],
      fillStar: `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star fill" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star-fill icon b-icon bi"><g data-v-52c730ed=""><path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path></g></svg>`,
      star: `<svg data-v-52c730ed="" viewBox="0 0 16 16" width="1em" height="1em" focusable="false" role="img" aria-label="star" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi-star icon b-icon bi"><g data-v-52c730ed=""><path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"></path></g></svg>`,
    };
  },
  created() {
    if (this.userInfo) {
      console.log(this.userInfo);
      this.reviewDto.memberId = this.userInfo.memberId;
    }
    // 플랜 목록 불러와서 뿌려주기
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
  methods: {
    onSubmit() {},
    onReset() {},
    starRating(index) {
      console.log('index', index);
      this.reviewDto.rating = index + 1;
    },
  },
};
</script>

<style scoped>
.companion-radio-wrap {
  display: flex;
  gap: 1rem;
}
</style>
