import { registPlan, getUserPlanList, getPlanDetail, deletePlan } from "@/api/plan";

const planStore = {
  namespaced: true,
  state: {},
  mutations: {},
  actions: {
    // registPlan: (params) => {
    //   registPlan(
    //     params,
    //     ({ data }) => {
    //       console.log(data);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },
  },
};

export default planStore;
