<template>
  <div>
    <!-- MyPage View 입니다.
    <br />
    <router-link to="/mypage/ssafy">MyPage Main</router-link> | 
    <router-link to="/mypage/ssafy/myplan/list">MyPlan</router-link> |
    <router-link to="/mypage/ssafy/favoplan">Favorite Plan</router-link> |
    <router-link to="/mypage/ssafy/favoattraction">Favorite Attraction</router-link>
    <br /> -->
    <div class="container">
      <template v-if="userInfo.memberId === memberId">
        <div class="h1-wrap">
          <h1>마이페이지</h1>
        </div>
      </template>

      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { getOtherMemberInfo } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MyPageView",
  data() {
    return {
      memberId: "",
      pageUserInfo: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    $route(to) {
      this.memberId = to.params.memberId;
    },
  },
  created() {
    this.memberId = this.$route.params.memberId;

    getOtherMemberInfo(
      this.memberId,
      ({ data }) => {
        console.log("다른 사람 정보!!", data);
      },
      async (error) => {
        console.log("정보 조회 불가", error);
      }
    );
  },
  methods: {},
};
</script>

<style></style>
