<template>
  <div class="select-btn">
    <b-form-checkbox button-variant="primary" v-model="allSelected" button>전체</b-form-checkbox>
    <b-form-checkbox
      v-model="selected"
      v-for="(contentType, index) in contentTypes"
      :key="index"
      :value="contentType.value"
      button-variant="primary"
      @change="changeContentType"
      button
    >
      {{ contentType.text }}
    </b-form-checkbox>
  </div>
</template>

<script>
export default {
  name: 'SelectButton',
  props: {
    contentTypes: [],
  },
  data() {
    return {
      selected: [],
    };
  },
  computed: {
    allSelected: {
      get() {
        // console.log(this.selected.length, this.contentTypes.length);
        return this.selected.length === this.contentTypes.length;
      },
      set(e) {
        // console.log(e);
        if (e) {
          const selected = [];
          this.contentTypes.forEach((contentType) => {
            selected.push(contentType.value);
          });
          this.selected = selected;
        } else if (this.selected.length === this.contentTypes.length) {
          this.selected = [];
        }
        this.changeContentType();
      },
    },
  },
  created() {},
  methods: {
    changeContentType() {
      const params = {
        contentTypeList: this.selected.join(','),
      };
      this.$emit('select-content-type', params);
    },
  },
};
</script>

<style scope>
.select-btn {
  padding: 20px 0px;
  gap: 15px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}

.btn-group-toggle > .btn,
.btn-group-toggle > .btn-group > .btn {
  margin-bottom: 0;
  border-radius: 50px;
  padding: 0.5em 1.2em;
  border: 0.3px solid transparent;
  background: #ffffff;
  color: #343a40;
  border-color: #8d9193;
}

.btn-primary:not(:disabled):not(.disabled).active,
.btn-primary:not(:disabled):not(.disabled):active,
.show > .btn-primary.dropdown-toggle {
  color: #ffffff;
  background-color: #51abf3;
  border-color: #51abf3;
}

.col {
  padding: 0px;
}

.row {
  padding: 15px;
  gap: 10px;
}

@media (max-width: 950px) {
  .btn-group,
  .btn-group-vertical {
    gap: 12px;
  }
}
</style>
