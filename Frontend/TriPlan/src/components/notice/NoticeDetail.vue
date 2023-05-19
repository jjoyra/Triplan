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
          :header-html="`<h3>${notice.title}</h3>`"
          class="mb-2"
          no-body
        >
          <b-card-header>
            <div>
              <h6>작성자 : {{notice.memberId}} 조회수 : {{notice.hit}}, {{notice.createDate | dateFormat}}</h6>
            </div>
          </b-card-header>
          <b-card-body class="text-left">
            <div v-text="notice.content"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import { getNoticeDetail, deleteNotice } from '@/api/notice';

export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {},
    };
  },
  created() {
    let noticeId = this.$route.params.noticeId;
    getNoticeDetail(
      noticeId,
      ({ data }) => {
        this.notice = data.notice;
      },
      (err) => {
        console.log(err);
      }
    );
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
        deleteNotice(
          this.notice.noticeId,
          () => this.$router.replace('/notice'),
          (err) => console.log(err)
        )
      }
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style></style>
