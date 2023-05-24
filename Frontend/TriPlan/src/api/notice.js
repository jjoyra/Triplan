import { apiInstance } from "./http";

const api = apiInstance();

async function registNotice(notice, success, fail) {
  await api.post(`/notice`, notice).then(success).catch(fail);
}

async function getNoticeList(param, success, fail) {
  await api.get(`/notice`, { params: param }).then(success).catch(fail);
}

async function modifyNotice(notice, success, fail) {
  await api.put(`/notice`, notice).then(success).catch(fail);
}

async function deleteNotice(noticeid, success, fail) {
  await api.put(`/notice/${noticeid}`).then(success).catch(fail);
}

async function getNoticeDetail(noticeid, success, fail) {
  await api.get(`/notice/${noticeid}`).then(success).catch(fail);
}

async function getTotalNoticeCount(param, success, fail) {
  api.get(`/notice/count`, { params: param }).then(success).catch(fail);
}

export { registNotice, getNoticeList, modifyNotice, deleteNotice, getNoticeDetail, getTotalNoticeCount };
