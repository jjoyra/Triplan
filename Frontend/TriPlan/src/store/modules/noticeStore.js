// import { getNoticeList } from "@/api/notice.js";

const noticeStore = {
  namespaced: true,
  state: {
    notices: [],
    notice: null,
    sortkey: null,
    pgno: null,
    noticeSearchWord: null,
  },
  getters: {

  },
  mutations: {
    CLEAR_SORTKEY(state) {
      state.sortkey = null;
    },
    CLEAR_PGNO(state) {
      state.pgno = null;
    },
    CLEAR_NOTICE_SEARCH_WORD(state) {
      state.noticeSearchWord = null;
    },
    // SET_NOTICES_LIST(state, notices) {
    //   state.notices = notices;
    //   state.notice = null;
    // },
    // SET_DETAIL_NOTICE(state, notice) {
    //   state.notice = notice;
    // },
    SET_SORTKEY(state, sortkey) {
      state.sortkey = sortkey;
    },
    SET_PGNO(state, pgno) {
      state.pgno = pgno;
    },
    SET_NOTICE_SEARCH_WORD(state, noticeSearchWord) {
      state.noticeSearchWord = noticeSearchWord;
    },
  },
  actions: {
    // getNotices({ commit }, params) {
    //   getNoticeList(
    //     params,
    //     ({ data }) => {
    //       commit('SET_NOTICES_LIST', data.notices);
    //     },
    //     (err) => {
    //       console.log(err);
    //     },
    //   );
    // },
    // getNoticeDetail({ commit }, notice) {
    //   commit("SET_DETAIL_NOTICE", notice)
    // }
  },
};

export default noticeStore;
