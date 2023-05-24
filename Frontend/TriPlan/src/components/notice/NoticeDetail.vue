<template>
  <b-container class="mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <router-link to="/notice/list" class="btn btn-primary">목록</router-link>
      </b-col>
      <b-col class="text-right" v-if="userInfo ? userInfo.role === 0 : ''">
        <b-button variant="success" size="sm" @click="moveModifyNotice" class="mr-2 h-100"
          >수정</b-button
        >
        <b-button variant="danger" size="sm" @click="deleteNotice" class="h-100">삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card :title="notice.title">
          <b-card-text>
            <div class="card-text-wrap">
              <div class="info">
                <router-link :to="`/mypage/${notice.memberId}`">
                  <b-icon id="content-icon" icon="person"></b-icon> {{ notice.memberId }}
                </router-link>
                <span> <b-icon id="content-icon" icon="eye"></b-icon> {{ notice.hit }} </span>
                <span>
                  <b-icon id="content-icon" icon="calendar-date"></b-icon>
                  {{ notice.createDate | dateFormat }}
                </span>
              </div>
              <div class="content">
                {{ notice.content }}
              </div>
            </div>
          </b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const noticeStore = "noticeStore";
const memberStore = "memberStore";

import moment from "moment";
import { getNoticeDetail, deleteNotice } from "@/api/notice";

export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(noticeStore, ["pgno"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    console.log("pgno", this.pgno);
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
          () => this.$router.replace("/notice"),
          (err) => console.log(err)
        );
      }
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.card-text-wrap {
  display: flex;
  flex-direction: column;
}
.info {
  display: flex;
  justify-content: flex-end;
  gap: 20px;
  flex-wrap: wrap;
  margin-bottom: 1rem;
  color: #a1a1a1;
}

.info a {
  color: #a1a1a1;
}

.info:after {
  display: none;
}
</style>
