<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <router-link to="/notice/list" class="btn btn-primary">목록</router-link>
      </b-col>
      <!-- <b-col class="text-right" v-if="userInfo.userid === article.userid"> -->
      <b-col class="text-right">
        <b-button variant="outline-success" size="sm" @click="moveModifyNotice" class="mr-2 h-100">수정</b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteNotice" class="h-100">삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notice.noticeId}. ${notice.title}</h3><div><h6>작성자 : ${notice.memberId} 조회수 : ${notice.hit}</div><div>${notice.createDate}</h6></div>`"
          class="mb-2"
          no-body
        >
          <b-card-body class="text-left">
            <div v-text="notice.content"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
// import { getArticle } from "@/api/board";
// import { mapState } from "vuex";

// const memberStore = "memberStore";
import http from '@/api/http';

export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    // ...mapState(memberStore, ["userInfo"]),
    // message() {
    //   if (this.article.content) return this.article.content.split("\n").join("<br>");
    //   return "";
    // },
  },
  created() {
    // let param = this.$route.params.articleno;
    // getArticle(
    //   param,
    //   ({ data }) => {
    //     this.article = data;
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
    let noticeId = this.$route.params.noticeId;
    http.get(`notice/${noticeId}`).then(res => {
      console.log(res);
      this.notice = res.data.notice;
    })
  },
  methods: {
    moveModifyNotice() {
      this.$router.replace({
        name: "noticemodify",
        params: { noticeId: this.notice.noticeId },
      });
    },
    deleteNotice() {
      if (confirm("정말 삭제하시겠습니까?")) {
        http.delete(`notice/${this.notice.noticeId}`).then(() => {
        this.$router.replace('/notice');
        })
      }
    },
    moveList() {
    //   this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
