<template>
  <div>
    <h2>{{ plan.title }}</h2>

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
import CourseListItem from "@/components/board/CourseListItem.vue";
import { getPlanDetail } from "@/api/plan";

export default {
  name: "MyPlanDetail",
  data() {
    return {
      plan: null,
      courseList: [],
    };
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
};
</script>

<style>
h5 {
  color: #33a3ff;
}
</style>
