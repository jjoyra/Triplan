<template>
  <!-- <ranking-list :planList="planList" :isPublic="true"></ranking-list> -->
  <div class="list-wrap">
    <my-plan-list-item
      v-for="(plan, index) in planList"
      :key="index"
      :plan="plan"
    ></my-plan-list-item>
  </div>
</template>

<script>
// import RankingList from "@/components/ranking/RankingList.vue";
import { getUserPlanList } from "@/api/plan";
import MyPlanListItem from "./MyPlanListItem.vue";

export default {
  name: "MyPlanList",
  data() {
    return {
      planList: [],
    };
  },
  components: {
    // RankingList,
    MyPlanListItem,
  },
  created() {
    getUserPlanList(this.$route.params.memberId, ({ data }) => {
      this.planList = data;
      // console.log(data);
    });
  },
};
</script>

<style>
.h2-wrap {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}
.h2-wrap h2 {
  margin-bottom: 0;
}
.h2-wrap svg {
  font-size: 1.2rem;
}
.h2-wrap a:hover {
  color: #383838;
}

.list-wrap {
  display: flex;
  flex-direction: row;
  gap: 10px;
  flex-wrap: wrap;
}
</style>
