<template>
  <div>
    <b-container fluid>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <!-- Text slides with image -->
        <b-carousel-slide
          height="480"
          caption="신안 증도"
          text="한국의 나트랑이라고 소개하고 싶은 신안의 증도. 세계에서 가장 품질이 좋다는 천일염의 고장입니다."
          img-src="https://i.ibb.co/n3dD3Qm/fa8573ce-ede1-44d2-9496-ae0548c04490.jpg"
          alt="65076bb1-ff62-45e9-9e66-a7a381d076a8"
        ></b-carousel-slide>

        <!-- Slides with custom text -->
        <b-carousel-slide
          height="480"
          caption="신두리 해안사구"
          img-src="https://i.ibb.co/D1MF9nB/65076bb1-ff62-45e9-9e66-a7a381d076a8.jpg"
          alt="65076bb1-ff62-45e9-9e66-a7a381d076a8"
        >
        </b-carousel-slide>

        <!-- Slides with image only -->
        <b-carousel-slide
          height="480"
          img-src="https://i.ibb.co/n3dD3Qm/fa8573ce-ede1-44d2-9496-ae0548c04490.jpg"
        ></b-carousel-slide>

        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="https://i.ibb.co/D1MF9nB/65076bb1-ff62-45e9-9e66-a7a381d076a8.jpg"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </b-container>
    <b-container> Main View 입니다. </b-container>
  </div>
</template>

<script>
export default {
  name: "MainView",
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
  },
};
</script>

<style scoped>
.container-fluid {
  padding: 0;
}
</style>
