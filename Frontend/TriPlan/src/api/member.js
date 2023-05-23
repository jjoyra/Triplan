import { apiInstance } from "./http.js";

const api = apiInstance();

async function login(member, success, fail) {
  await api.post(`/user/login`, JSON.stringify(member)).then(success).catch(fail);
}
async function findById(memberId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/${memberId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(memberid, success, fail) {
  await api.get(`/user/logout/${memberid}`).then(success).catch(fail);
}

function join(member, success, fail) {
  api.post(`/user`, member).then(success).catch(fail);
}

function modifyMember(member, success, fail) {
  api.put(`/user`, member).then(success).catch(fail);
}

function getMember(memberid, success, fail) {
  api.get(`/user/${memberid}`).then(success).catch(fail);
}
async function getOtherMemberInfo(memberId, success, fail) {
  await api.get(`/user/other/${memberId}`).then(success).catch(fail);
}

function deleteMember(memberid, success, fail) {
  api.delete(`/user/${memberid}`).then(success).catch(fail);
}

function findPassword(member, success, fail) {
  api.get(`/user/password`, member).then(success).catch(fail);
}

async function idCheck(memberId, success, fail) {
  await api.get(`/user/idcheck/${memberId}`).then(success).catch(fail);
}

async function follow(params, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/user/follow/${params.followerId}/${params.followeeId}`).then(success).catch(fail);
}

async function unfollow(params, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/user/unfollow/${params.followerId}/${params.followeeId}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, join, modifyMember, getMember, getOtherMemberInfo, deleteMember, findPassword, idCheck, follow, unfollow };
