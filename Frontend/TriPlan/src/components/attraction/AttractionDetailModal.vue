<template>
  <div>
    <b-modal id="modal-detail" :title="attraction.title"> </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionDetailModal",
  data() {
    return {};
  },
  computed: {
    ...mapState(attractionStore, ["attraction"]),
  },
  methods: {
    // ...mapMutations(attractionStore, []),
  },
};
</script>

<style></style>
