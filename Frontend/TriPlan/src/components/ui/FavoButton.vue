<template>
  <div class="favo-icon-wrap">
    <b-icon icon="heart-fill" v-if="checked" @click="toggle" class="true"></b-icon>
    <b-icon icon="heart" v-else @click="toggle" class="false"></b-icon>
  </div>
</template>

<script>
export default {
  name: 'FavoButton',
  data() {
    return {
      checked: false,
    };
  },
  created() {
    // 값 불러와서 checked에 넣어줘야 함
  },
  methods: {
    toggle() {
      this.checked = !this.checked;
    },
  },
};
</script>

<style scoped>
.favo-icon-wrap {
  position: absolute;
  top: 0;
  right: 0;
}

.b-icon {
  width: 100%;
  height: 25px;
}

.false {
  color: #8d9193;
}

.true {
  color: #51abf3;
}

@media (max-width: 750px) {
  .favo-icon-wrap {
    top: 0;
    right: 0;
  }
}
</style>
