import { apiInstance } from "./http";

const api = apiInstance();

async function registPlan(param, success, fail) {
  console.log(param);
  await api.post(`/plan`, param).then(success).catch(fail);
}

async function getUserPlanList(memberId, success, fail) {
  await api.get(`/plan/list/${memberId}`).then(success).catch(fail);
}

async function getPlanDetail(planId, success, fail) {
  await api.get(`/plan/${planId}`).then(success).catch(fail);
}

async function deletePlan(planId, success, fail) {
  await api.delete(`/plan/${planId}`).then(success).catch(fail);
}

export { registPlan, getUserPlanList, getPlanDetail, deletePlan };
