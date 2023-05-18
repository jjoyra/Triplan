<template>
  <div id="app">
    <the-header></the-header>
    <router-view />
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader.vue";
import TheFooter from "@/components/common/TheFooter.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
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
  content: '';
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

.btn {
  padding: .15rem 1rem .2rem;
}
.btn-primary {
  background-color: #51ABF3;
  border-color: #51ABF3;
}
.btn-primary:hover {
  background-color: #3e96df;
  border-color: #3e96df;
}
.btn-primary.focus, .btn-primary:focus {
  background-color: #3e96df;
  border-color: #3e96df;
}
</style>
