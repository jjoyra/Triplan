<template>
  <ranking-list :planList="planList" :isPublic="true"></ranking-list>
</template>

<script>
import RankingList from "@/components/ranking/RankingList.vue";
import { getUserPlanList } from "@/api/plan";

export default {
  name: "MyPlanList",
  data() {
    return {
      planList: [],
    };
  },
  components: {
    RankingList,
  },
  created() {
    getUserPlanList(this.$route.params.memberId, ({ data }) => {
      this.planList = data;
      // console.log(data);
    });
  },
};
</script>

<style></style>
