<template>
  <div>
    <template v-if="userInfo.memberId === memberId">
      <div class="h1-wrap">
        <h1>마이페이지</h1>
      </div>
    </template>

    <div>
      <template v-if="pageUserInfo === null">
        <user-info-item :pageUserInfo="userInfo" :isMyInfo="`true`"></user-info-item>
      </template>
      <template v-else>
        <user-info-item :pageUserInfo="pageUserInfo" :isMyInfo="`false`"></user-info-item>
      </template>

      <div id="myplan-wrap">
        <ranking-list
          title="마이 플랜"
          :isMore="true"
          :link="`/mypage/${memberId}/myplan`"
        ></ranking-list>
      </div>

      <div id="favo-plan-wrap">
        <ranking-list
          title="관심 플랜"
          :isMore="true"
          :link="`/mypage/${memberId}/favoplan`"
        ></ranking-list>
      </div>

      <div id="favo-attraction-wrap">
        <ranking-list
          title="관심 여행지"
          :isMore="true"
          :link="`/mypage/${memberId}/favoattraction`"
        ></ranking-list>
      </div>
    </div>
  </div>
</template>

<script>
import { getOtherMemberInfo } from "@/api/member";
import { mapState } from "vuex";
import RankingList from "../ranking/RankingList.vue";
import UserInfoItem from "./UserInfoItem.vue";

const memberStore = "memberStore";
export default {
  name: "MypageMain",
  data() {
    return {
      memberId: "",
      pageUserInfo: null,
    };
  },
  components: {
    RankingList,
    UserInfoItem,
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

    if (this.userInfo.memberId !== this.memberId) {
      getOtherMemberInfo(
        this.memberId,
        ({ data }) => {
          if (data.message === "success") {
            this.pageUserInfo = data.userInfo;
          }
        },
        async (error) => {
          console.log("회원 정보 조회 불가", error);
        }
      );
    } else {
      console.log("내 페이지임");
      this.pageUserInfo = this.userInfo;
    }
  },
};
</script>

<style scoped></style>
