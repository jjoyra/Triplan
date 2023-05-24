const reviewStore = {
  namespaced: true,
  state: {
    sortkey: 'create_date',
    pgno: 1,
    reviewSearchWord: '',
  },
  getters: {

  },
  mutations: {
    SET_SORTKEY(state, sortkey) {
      state.sortkey = sortkey;
    },
    SET_PGNO(state, pgno) {
      state.pgno = pgno;
    },
    SET_REVIEW_SEARCH_WORD(state, reviewSearchWord) {
      state.reviewSearchWord = reviewSearchWord;
    }
  },
  actions: {
    setSortkey({ commit }, sortkey) {
      commit("SET_SORTKEY", sortkey);
    },
    setPgno({ commit }, pgno) {
      commit("SET_PGNO", pgno);
    },
    setReviewSearchWord({ commit }, reviewSearchWord) {
      commit("SET_REVIEW_SEARCH_WORD", reviewSearchWord);
    },
  },
};

export default reviewStore;
