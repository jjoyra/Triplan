<template>
  <b-col>
    <b-row>
      <b-col>
        <b-form-select v-model="sidoCode" :options="sidos"></b-form-select>
      </b-col>
      <b-col>
        <b-form-select v-model="gugunCode" :options="guguns" @change="changeGugun"></b-form-select>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const itemStore = "itemStore";

export default {
  name: "SelectSidoGugun",
  components: {},
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(itemStore, ["sidos", "guguns"]),
  },
  watch: {
    sidoCode() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.CLEAR_GUGUN_LIST();
  },
  methods: {
    ...mapActions(itemStore, ["getSido", "getGugun"]),
    ...mapMutations(itemStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    changeGugun() {
      const sidoGugunCode = {
        gugunCode: this.gugunCode,
        sidoCode: this.sidoCode,
      };
      this.$emit("select-gugun", sidoGugunCode);
    },
    handleSearch(val) {
      console.log("여행지 검색", val);
    },
  },
};
</script>

<style scoped></style>
