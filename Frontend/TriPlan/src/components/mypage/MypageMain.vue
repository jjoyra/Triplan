<template>
  <div>
    <template v-if="userInfo && userInfo.memberId === memberId">
      <div class="h1-wrap">
        <h1>마이페이지</h1>
      </div>
    </template>

    <div>
      <template v-if="pageUserInfo">
        <user-info-item
          :pageUserInfo="pageUserInfo"
          :isMyInfo="userInfo && userInfo.memberId === memberId ? true : false"
          v-on:get-page-user-info="getPageUserInfo"
        ></user-info-item>

        <div id="myplan-wrap">
          <ranking-list
            title="마이 플랜"
            :isMore="true"
            :link="`/mypage/${memberId}/myplan`"
            :bg="false"
            :isPublic="pageUserInfo.openMyplan === 1"
            :isMyInfo="userInfo && userInfo.memberId === memberId ? true : false"
          ></ranking-list>
        </div>

        <div id="favo-plan-wrap">
          <ranking-list
            title="관심 플랜"
            :isMore="true"
            :link="`/mypage/${memberId}/favoplan`"
            :bg="false"
            :isPublic="pageUserInfo.openFavoPlan === 1"
            :isMyInfo="userInfo && userInfo.memberId === memberId ? true : false"
          ></ranking-list>
        </div>

        <div id="favo-attraction-wrap">
          <ranking-list
            title="관심 여행지"
            :isMore="true"
            :link="`/mypage/${memberId}/favoattraction`"
            :bg="false"
            :isPublic="pageUserInfo.openFavoAttraction === 1"
            :isMyInfo="userInfo && userInfo.memberId === memberId ? true : false"
          ></ranking-list>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { getOtherMemberInfo } from "@/api/member";
import { mapState, mapActions } from "vuex";
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
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  watch: {
    $route(to) {
      this.memberId = to.params.memberId;
      this.getPageUserInfo();
    },
  },
  created() {
    console.log("userinfo", this.userInfo);
    this.memberId = this.$route.params.memberId;
    this.getPageUserInfo();
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo"]),
    async loginUserInfo() {
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
      }
    },
    async getPageUserInfo() {
      console.log("getPageUserInfo");
      if (this.userInfo && this.userInfo.memberId === this.memberId) {
        // 로그인 사용자 O === 현재 페이지 사용자
        console.log("if");
        await this.loginUserInfo();
        console.log("내 페이지임", this.pageUserInfo);
        this.pageUserInfo = this.userInfo;
      } else {
        console.log("else", this.memberId);
        getOtherMemberInfo(
          this.memberId,
          ({ data }) => {
            if (data.message === "success") {
              console.log("남 페이지임", data.userInfo);
              this.pageUserInfo = data.userInfo;
            }
          },
          async (error) => {
            console.log("회원 정보 조회 불가", error);
          }
        );
      }
    },
  },
};
</script>

<style scoped></style>
