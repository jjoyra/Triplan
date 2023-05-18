import { apiInstance } from "./http";

const api = apiInstance();

function getSidoCode(success, fail) {
  api.get(`/attraction/sido`).then(success).catch(fail);
}

function getGugunCode(param, success, fail) {
  api.get(`/attraction/gugun`, { params: param }).then(success).catch(fail);
}

export { getSidoCode, getGugunCode };
