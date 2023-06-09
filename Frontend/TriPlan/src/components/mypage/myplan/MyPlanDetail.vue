<template>
  <div>
    <div class="btn-left" v-if="userInfo ? userInfo.memberId === plan.members.owner : ''">
      <b-button variant="danger" size="sm" @click="deletePlan" class="h-100">삭제</b-button>
    </div>
    <div class="title-wrap">
      <h2>{{ plan.title }}</h2>
      <div class="date">시작일 : {{ plan.startDate }} ~ 종료일 : {{ plan.startDate }}</div>
    </div>
    <div class="article-wrap">
      <h5>Course</h5>
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
  </div>
</template>

<script>
import { mapState } from "vuex";
import CourseListItem from "@/components/board/CourseListItem.vue";
import { getPlanDetail, deletePlan } from "@/api/plan";

const memberStore = "memberStore";

export default {
  name: "MyPlanDetail",
  data() {
    return {
      plan: null,
      courseList: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  components: {
    CourseListItem,
  },
  created() {
    let planId = this.$route.params.planId;
    getPlanDetail(
      planId,
      ({ data }) => {
        console.log(data);
        this.plan = data;
        this.courseList = data.courseList;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    deletePlan() {
      if (confirm("정말 삭제하시겠습니까?")) {
        deletePlan(
          this.plan.planId,
          () => this.$router.replace("/mypage/ssafy/myplan/list"),
          (err) => console.log(err)
        );
      }
    },
  },
};
</script>

<style scoped>
h5 {
  color: #33a3ff;
}

.date {
  color: #8d9193;
  margin-bottom: 1rem;
}

.article-wrap div ul {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.card img {
  width: 30%;
  height: 215px;
  object-fit: cover;
}

.title-wrap {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.btn-left {
  display: flex;
  justify-content: flex-end;
}
</style>
