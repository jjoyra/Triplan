<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <!-- <b-form-group id="userid-group" label="작성자:" label-for="userid" description="작성자를 입력하세요.">
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="article.userid"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group> -->

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
// import { writeArticle, modifyNotice, getArticle } from "@/api/board";
import http from '@/api/http';

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
      http.get(`notice/${noticeId}`).then(res => {
        this.notice = res.data.notice;
      })
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.type === "register" ? this.registerNotice() : this.modifyNotice();
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
      http.post('notice', param).then(() => {
        alert('등록 완료');
        this.$router.push('/notice').catch(err=> {
          console.log('이동 실패', err);
        });
      }).catch(err=> {
        console.log('등록 실패', err);
      });
    //   writeArticle(
    //     param,
    //     ({ data }) => {
    //       let msg = "등록 처리시 문제가 발생했습니다.";
    //       if (data === "success") {
    //         msg = "등록이 완료되었습니다.";
    //       }
    //       alert(msg);
    //       this.moveList();
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    },
    modifyNotice() {
      let param = {
        noticeId: this.notice.noticeId,
        memberId: 'ssafy',
        title: this.notice.title,
        content: this.notice.content,
        mustRead: this.notice.mustRead,
      };
      http.put('notice', param).then(() => {
        alert('수정 완료');
        this.$router.push(`/notice/detail/${this.notice.noticeId}`).catch(err=> {
          console.log('이동 실패', err);
        });
      }).catch(err=> {
        console.log('수정 실패', err);
      });
    //   let param = {
    //     articleno: this.article.articleno,
    //     userid: this.article.userid,
    //     subject: this.article.subject,
    //     content: this.article.content,
    //   };
    //   modifyNotice(
    //     param,
    //     ({ data }) => {
    //       let msg = "수정 처리시 문제가 발생했습니다.";
    //       if (data === "success") {
    //         msg = "수정이 완료되었습니다.";
    //       }
    //       alert(msg);
    //       // 현재 route를 /list로 변경.
    //       this.moveList();
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    },
  },
};
</script>

<style></style>
