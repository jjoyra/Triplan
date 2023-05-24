import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import noticeStore from "@/store/modules/noticeStore";
import attractionStore from "@/store/modules/attractionStore";
import memberStore from "@/store/modules/memberStore";
import reviewStore from "@/store/modules/reviewStore";
import itemStore from "./modules/item/itemStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    noticeStore,
    attractionStore,
    memberStore,
    itemStore,
    reviewStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage, // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
    }),
  ],
});
