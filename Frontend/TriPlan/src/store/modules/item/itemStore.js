import { getSidoCode, getGugunCode } from "@/api/item/item";

const itemStore = {
  namespaced: true,
  state: {
    sidos: [{ text: "시도", value: null }],
    guguns: [{ text: "구군", value: null }],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ text: "시도", value: null }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ text: "구군", value: null }];
    },
    SET_SIDO_LIST(state, data) {
      data.sidos.forEach((sido) => {
        state.sidos.push({ text: sido.sidoName, value: sido.sidoCode });
      });
    },
    SET_GUGUN_LIST(state, data) {
      data.guguns.forEach((gugun) => {
        state.guguns.push({ text: gugun.gugunName, value: gugun.gugunCode });
      });
    },
  },
  actions: {
    getSido: ({ commit }) => {
      getSidoCode(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.error(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const param = { sidoCode };
      getGugunCode(
        param,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.error(error);
        }
      );
    },
  },
};

export default itemStore;
