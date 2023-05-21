<template>
  <div>
    <!-- <b-form-checkbox value="-1" button-variant="primary" v-model="allSelected" button
      >전체</b-form-checkbox
    > -->
    <b-form-checkbox-group v-model="selected" @change="changeContentType" buttons>
      <!-- <b-form-checkbox value="-1" button-variant="primary" v-model="allSelected" button
        >전체</b-form-checkbox
      > -->
      <b-form-checkbox
        v-for="contentType in contentTypes"
        :key="contentType.value"
        :value="contentType.value"
        button-variant="primary"
      >
        {{ contentType.text }}
      </b-form-checkbox>
    </b-form-checkbox-group>
    <span>{{ selected }}</span>
  </div>
</template>

<script>
export default {
  name: "SelectButton",
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
        return this.selected.length === this.contentTypes.length;
      },
      set(e) {
        if (e) {
          const selected = [];
          this.contentTypes.forEach((contentType) => {
            selected.push(contentType.value);
          });
          this.selected = selected;
        } else {
          this.selected = [];
        }
      },
    },
  },
  created() {},
  methods: {
    temp() {
      const selected = [];
      this.contentTypes.forEach((contentType) => {
        selected.push(contentType.value);
      });

      this.selected = selected;
    },
    toggleAll(flag) {
      console.log("toggle");
      if (flag) {
        this.temp();
      }
    },
    changeContentType() {
      let flag = false;
      // console.log(this.selected);
      for (let select of this.selected) {
        console.log(select);
        if (select === 0) flag = true;
      }

      if (flag) {
        console.log("전체 선택");
        this.toggleAll(true);
      } else {
        console.log("아님");
        this.toggleAll(false);
      }

      // const params = {
      //   contentTypeList: this.selected.join(","),
      // };
      // this.$emit("select-content-type", params);
    },
  },
};
</script>

<style scoped>
.btn {
  flex-shrink: 0;
  border-radius: 50px;
  padding: 0.5em 1.2em;
  border: 0.3px solid transparent;
}

.btn-group,
.btn-group-vertical {
  gap: 18px;
  position: relative;
  display: inline-flex;
  vertical-align: middle;
  flex-wrap: wrap;
}

.btn-group > .btn-group > .btn,
.btn-group > .btn {
  border-radius: 50px;
  flex: none;
}

.btn-group > .btn-group:not(:last-child) > .btn,
.btn-group > .btn:not(:last-child):not(.dropdown-toggle) {
  border-radius: 50px;
}

.btn-primary {
  background: #ffffff;
  color: #343a40;
  border-color: #8d9193;
}

.btn-primary,
.btn-primary:hover {
  background: #ffffff;
  color: #343a40;
  border-color: #8d9193;
}

.btn-primary.focus,
.btn-primary:focus {
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

@media (max-width: 950px) {
  .btn-group,
  .btn-group-vertical {
    gap: 12px;
  }
}
</style>
