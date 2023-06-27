import { apiInstance } from "./http";

const api = apiInstance();

async function getAttractionList(param, success, fail) {
  await api.get(`/attraction`, { params: param }).then(success).catch(fail);
}

function updateAttractionRecommentCnt(contentid, success, fail) {
  api.put(`/attraction/${contentid}`).then(success).catch(fail);
}

function updateRankingVotes(contentid, success, fail) {
  api.put(`/attraction/ranking/${contentid}`).then(success).catch(fail);
}

async function getAttractionDetail(contentid, success, fail) {
  await api.get(`/attraction/${contentid}`).then(success).catch(fail);
}

function getWorldCupTOP3AttractionList(success, fail) {
  api.get(`/attraction/ranking`).then(success).catch(fail);
}

async function getRecommendTop3AttractionList(success, fail) {
  await api.get(`/attraction/recommend`).then(success).catch(fail);
}

export {
  getAttractionList,
  updateAttractionRecommentCnt,
  updateRankingVotes,
  getAttractionDetail,
  getWorldCupTOP3AttractionList,
  getRecommendTop3AttractionList,
};
