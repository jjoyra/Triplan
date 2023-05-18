<template>
  <b-nav-form @submit="onSubmit" id="search-form">
    <b-form-input 
      id="input-1"
      v-model="form.search"
      type="text"
      aria-label="Input"
      :placeholder="placeholderTxt"
      class="w-70"
    ></b-form-input>
    <b-icon id="search-icon" icon="search"></b-icon>
  </b-nav-form>
</template>

<script>
import { BIcon } from 'bootstrap-vue'

export default {
  name: 'SearchInput',
  props: {
    placeholderTxt: String,
  },
  data() {
    return {
      form: {
        search: '',
      },
    }
  },
  components: {
    BIcon,
  },
  methods: {
    onSubmit(event) {
        event.preventDefault();
        this.$emit('handle-search', this.form.search);
    },
  },
}
</script>

<style scope>
#form-inline {
  justify-content: center;
}

#search-form {
  position: relative;
}

#search-icon {
  position: absolute;
  color: #BBBBBB;
  font-size: 1rem;
  top: 50%;
  left: 5%;
  transform: translateY(-50%);
}

#input-1 {
  position: relative;
  color: #343A40;
  font-size: 0.8rem;
  background: #F6F6F6;
  border-radius: 3px;
  border: none;
  padding-left: 18%;
  padding-bottom: 4%;
  display: inline-block;
}
#input-1::placeholder {
  position: absolute;
  color: #BBBBBB;
  font-size: 0.8rem;
  top: 45%;
  left: 15%;
  transform: translateY(-50%);
}

@media (max-width: 950px) {
  .form-inline {
    width: 100%;
  }
}
</style>