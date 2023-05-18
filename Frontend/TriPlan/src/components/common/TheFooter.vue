<template>
  <div>
    <hr />
    <div class="col mt-4 mb-2">
      <div class="link">
        <b-icon icon="envelope-fill" class="icon"></b-icon>
        <span>triplan@gmail.com</span>
      </div>
      <div class="link">
        <b-icon icon="github" class="icon"></b-icon>
        <a href="https://github.com/Im-hass">Im-hass</a>
      </div>
      <div class="link">
        <b-icon icon="github" class="icon"></b-icon>
        <a href="https://github.com/jjoyra">jjoyra</a>
      </div>
    </div>
    <div class="col mb-4 word-break">
      <span class="text">이용약관</span>
      <span class="text" style="color: #51abf3">개인정보처리방침</span>
      <span class="text">Copyright &copy; TRIPLAN All Rights Reserved</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheFooter",
};
</script>

<style scoped>
* {
  color: #8d9193;
}

div {
  justify-content: center;
}

.col {
  display: flex;
}

.icon {
  size: 14px;
  margin-right: 4px;
}

.link {
  margin-right: 20px;
}

.link:last-child {
  margin-right: 0px;
}

.text::after {
  content: "|";
  padding: 0px 10px;
  color: #8d9193;
}

.text:last-child::after {
  content: "";
  padding: 0px;
}

.word-break {
  word-break: keep-all;
}
</style>
