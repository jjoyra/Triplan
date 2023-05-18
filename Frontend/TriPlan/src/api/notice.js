import { apiInstance } from "./http";

const api = apiInstance();

function registNotice(notice, success, fail) {
  api.post(`/notice`, notice).then(success).catch(fail);
}

function getNoticeList(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function modifyNotice(notice, success, fail) {
  api.put(`/notice`, notice).then(success).catch(fail);
}

function deleteNotice(noticeid, success, fail) {
  api.put(`/notice/${noticeid}`).then(success).catch(fail);
}

function getNoticeDetail(noticeid, success, fail) {
  api.get(`/notice/${noticeid}`).then(success).catch(fail);
}

export { registNotice, getNoticeList, modifyNotice, deleteNotice, getNoticeDetail };
