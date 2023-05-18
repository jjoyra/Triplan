<template>
  <div>
    <b-form-checkbox-group v-model="selected" buttons>
      <b-form-checkbox
        v-for="contentType in contentTypes"
        :key="contentType.value"
        :value="contentType.value"
        button-variant="primary"
      >
        {{ contentType.text }}
      </b-form-checkbox>
    </b-form-checkbox-group>
    <!-- <b-button class="btn btn-primary" v-if="checked" v-model="checked">{{ btnInfo.text }}</b-button>
    <b-button class="btn false" v-else v-model="checked">{{ btnInfo.text }}</b-button> -->
    <!-- <b-button>{{button.name}}</b-button> -->
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
  created() {},
  methods: {},
};
</script>

<style scoped>
.btn {
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
