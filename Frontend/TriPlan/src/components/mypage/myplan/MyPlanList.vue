<template>
  <b-container>
    <ranking-list :planList="planList" :isPublic="true"></ranking-list>
  </b-container>
</template>

<script>
import RankingList from "@/components/ranking/RankingList.vue";
import { getUserPlanList } from "@/api/plan";
import { mapState } from "vuex";

const memberStore = "memberStore";

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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.planList = getUserPlanList(
      this.userInfo.memberId,
      ({ data }) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
