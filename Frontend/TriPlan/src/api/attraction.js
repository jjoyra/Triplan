import { apiInstance } from "./http";

const api = apiInstance();

function getAttractionList(param, success, fail) {
  api.get(`/attraction`, { params: param }).then(success).catch(fail);
}

function updateAttractionRecommentCnt(contentid, success, fail) {
  api.put(`/attraction/${contentid}`).then(success).catch(fail);
}

function updateRankingVotes(contentid, success, fail) {
  api.put(`/attraction/ranking/${contentid}`).then(success).catch(fail);
}

function getAttractionDetail(contentid, success, fail) {
  api.put(`/attraction/${contentid}`).then(success).catch(fail);
}

function getWorldCupTOP3AttractionList(success, fail) {
  api.put(`/attraction/ranking}`).then(success).catch(fail);
}

export {
  getAttractionList,
  updateAttractionRecommentCnt,
  updateRankingVotes,
  getAttractionDetail,
  getWorldCupTOP3AttractionList,
};
