import {
  getAttractionList,
  getAttractionDetail,
  // getWorldCupTOP3AttractionList,
} from "@/api/attraction";

const attractionStore = {
  namespaced: true,
  state: {
    attractions: [],
    attraction: null,
  },
  getters: {},
  mutations: {
    CLEAR_ATTRACTION_LIST(state) {
      state.attractions = [];
      state.attraction = null;
    },
    SET_ATTRACTION_LIST(state, data) {
      state.attractions = data.attractions;
    },
    SET_DETAIL_ATTRACTION(state, data) {
      state.attraction = data.attraction;
      console.log(state.attraction);
    },
  },
  actions: {
    getAttractionList: ({ commit }, params) => {
      getAttractionList(
        params,
        ({ data }) => {
          commit("SET_ATTRACTION_LIST", data);
        },
        (error) => {
          console.error(error);
        }
      );
    },
    detailAttraction: ({ commit }, attractionId) => {
      getAttractionDetail(
        attractionId,
        ({ data }) => {
          commit("SET_DETAIL_ATTRACTION", data);
        },
        (error) => {
          console.error(error);
        }
      );
    },
  },
};

export default attractionStore;
