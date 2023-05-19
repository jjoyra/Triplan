<template>
  <div>
    <div v-html="outHtml"></div>
    <input type="text" v-model="text" @change="parseEmoji(text)" />
  </div>
</template>
<script>
export default {
  name: "MedalEmoji",
  data() {
    return {
      text: "\u2764\uFE0F",
      outHtml: null,
    };
  },
  methods: {
    parseEmoji: function (string) {
      this.outHtml = this.$twemoji.parse(string, function (emoji, options) {
        return options.base + emoji + options.extension;
      });
    },
  },
};
</script>
