<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group label="필독여부" label-for="mustRead">
          <b-form-checkbox
            id="checkbox-1"
            v-model="notice.mustRead"
            name="checkbox-1"
            unchecked-value="not_accepted"
          >
            필독
          </b-form-checkbox>
        </b-form-group>

        <b-form-group id="title-group" label="제목" label-for="title">
          <b-form-input
            id="title"
            v-model="notice.title"
            type="text"
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
        <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { registNotice, modifyNotice, getNoticeDetail } from "@/api/notice";
import { mapState, mapActions } from "vuex";
const noticeStore = "noticeStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      notice: {
        noticeId: 0,
        userid: "",
        title: "",
        content: "",
        mustRead: false,
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let noticeId = this.$route.params.noticeId;
      getNoticeDetail(
        noticeId,
        (res) => {
          this.notice = res.data.notice;
        },
        (err) => console.log(err)
      )
    }
  },
  computed: {
    ...mapState(noticeStore, ["pgno"]),
  },
  methods: {
    ...mapActions(noticeStore, ["setPgno"]),
    onSubmit(event) {
      event.preventDefault();
      this.type === "register" ? this.registerNotice() : this.handleModifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.mustRead = true;
      this.notice.title = "";
      this.notice.content = "";
    },
    registerNotice() {
      let param = {
        memberId: 'ssafy',
        title: this.notice.title,
        content: this.notice.content,
        mustRead: this.notice.mustRead,
      }
      registNotice(
        param,
        () => {
          alert('등록 완료');
          this.setPgno(1);
          this.$router.push('/notice').catch(err=> {
            console.log('이동 실패', err);
          });
        },
        err => console.log('등록 실패', err)
      )
    },
    handleModifyNotice() {
      let param = {
        noticeId: this.notice.noticeId,
        memberId: 'ssafy',
        title: this.notice.title,
        content: this.notice.content,
        mustRead: this.notice.mustRead,
      };
      modifyNotice(
        param,
        () => {
          alert('수정 완료');
          this.$router.push(`/notice/detail/${this.notice.noticeId}`).catch(err=> {
            console.log('이동 실패', err);
          });
        },
        err => console.log('수정 실패', err)
      )
    },
  },
};
</script>

<style></style>
