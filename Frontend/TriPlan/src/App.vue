<template>
  <div id="app">
    <the-header></the-header>
    <router-view />
    <template v-if="!isWrite">
      <the-footer></the-footer>
    </template>
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader.vue";
import TheFooter from "@/components/common/TheFooter.vue";

export default {
  data() {
    return {
      isWrite: false,
    };
  },
  components: {
    TheHeader,
    TheFooter,
  },
  watch: {
    $route(to) {
      if (to.path === "/myplan/write") this.isWrite = true;
      else this.isWrite = false;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap");

* {
  margin: 0;
  padding: 0;
  border: 0;
  box-sizing: border-box;
  text-decoration: none;
  list-style: none;
}

#app {
  font-family: "Noto Sans KR", sans-serif;
  color: #383838;
  font-size: 15px;
}

a {
  text-decoration: none;
  color: #383838;
}

.h1-wrap {
  text-align: center;
  margin-bottom: 30px;
}
h1 {
  font-size: 1.8rem;
  position: relative;
  display: inline-block;
}
h1::after {
  content: "";
  position: absolute;
  top: calc(100% + 2px);
  left: 0;
  display: block;
  width: 100%;
  height: 5px;
  background: rgba(81, 171, 243, 0.3);
}

h2 {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

ol,
ul {
  list-style: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  /* border-color: #bbb; */
}

button,
input[type="button"] {
  cursor: pointer;
}

input:focus {
  outline: 0;
}

img {
  display: block;
}

.container {
  margin: 2rem auto;
}

:root {
  --primary: #51abf3;
  --primary-hover: #3e96df;
}

.btn {
  padding: 0.15rem 1rem 0.2rem;
}
.btn-primary {
  background-color: var(--primary);
  border-color: var(--primary);
}
.btn-primary:hover {
  background-color: var(--primary-hover);
  border-color: var(--primary-hover);
}
.btn-primary.focus,
.btn-primary:focus {
  background-color: var(--primary-hover);
  border-color: var(--primary-hover);
}

.btn-secondary:hover {
  color: #fff;
  background-color: #5a6268;
  border-color: #545b62;
}

.btn-round-primary {
  border-radius: 30px;
  background: #51abf3;
  color: #fff;
}
.btn-round-primary:hover {
  background: #3e96df;
  color: #fff;
}
.btn-round {
  background: transparent;
  border: 0.3px solid #5a6268;
  color: #5a6268;
  border-radius: 30px;
}
.btn-round:focus,
.btn-round.focus {
  box-shadow: none;
}

.nav-link.btn-nav {
  color: #343a40;
  padding-left: 0;
}
.nav-link.btn-nav:hover {
  color: #212326;
  background-color: transparent;
}
.nav-link.btn-nav:focus {
  box-shadow: none;
}
.nav-link.btn-nav.btn-secondary:active {
  color: #212326;
  background-color: transparent;
  border-color: transparent;
  box-shadow: none;
}
.nav-link.btn-nav.btn-secondary:active:focus {
  box-shadow: none;
}

.dropdown-item.active,
.dropdown-item:active {
  background-color: #bbb;
}

.pagination {
  justify-content: center;
}

.custom-control-input:checked ~ .custom-control-label::before {
  border-color: var(--primary);
  background-color: var(--primary);
}

.page-item.active .page-link {
  background-color: var(--primary);
  border-color: var(--primary);
}
.page-link {
  color: var(--primary);
}
.page-link:hover {
  color: var(--primary-hover);
  text-decoration: none;
  background-color: #e9ecef;
  border-color: #dee2e6;
}
</style>
