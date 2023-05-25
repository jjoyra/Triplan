<template>
  <li v-if="coruseDetail">
    <b-card
      :img-src="
        coruseDetail.imgUrl.length !== 0 ? coruseDetail.imgUrl : 'https://placekitten.com/300/300'
      "
      :img-alt="coruseDetail.title"
      img-right
    >
      <b-card-text>
        <div>
          <div class="title">{{ coruseDetail.title }}</div>
          <div class="comment">
            {{ comment }}
          </div>
        </div>
      </b-card-text>
    </b-card>
  </li>
</template>

<script>
import { getAttractionDetail } from "@/api/attraction";

export default {
  name: "CourseListItem",
  props: {
    course: Object,
    comment: String,
  },
  data() {
    return {
      coruseDetail: null,
    };
  },
  created() {
    getAttractionDetail(
      this.course.contentId,
      ({ data }) => {
        // map에 마커 띄우기
        let course = {
          latitude: data.attraction.latitude,
          longitude: data.attraction.longitude,
          title: data.attraction.title,
          contentId: data.attraction.contentId,
          contentTypeId: data.attraction.contentTypeId,
          imgUrl: data.attraction.firstImage,
        };
        this.coruseDetail = course;
      },
      (err) => console.log(err)
    );
  },
};
</script>

<style scoped>
li {
  position: relative;
}
li::before {
  content: "";
  position: absolute;
  width: 15px;
  height: 15px;
  /* border: 3px solid rgba(81, 171, 243, 0.3); */
  border: 3px solid #6bbcff;
  border-radius: 50%;
  top: 50%;
  left: -1.8rem;
  transform: translateY(-50%);
}
li::after {
  content: "";
  position: absolute;
  width: 6px;
  height: 80%;
  top: calc(50% + 9px);
  left: -1.5rem;
  background: linear-gradient(
    to top,
    rgba(107, 188, 255, 0) 0,
    rgba(107, 188, 255, 0.05) 10%,
    rgba(107, 188, 255, 0.1) 25%,
    rgba(107, 188, 255, 0.15) 50%,
    rgba(107, 188, 255, 0.2) 75%,
    rgba(107, 188, 255, 0.3) 100%
  );
}
li:last-child::after {
  height: 4.6rem;
}

.card-body {
  padding: 1rem;
}
.card img {
  width: 30%;
}
.card-text .title {
  font-size: 1.5rem;
  font-weight: bold;
}
.card-text .comment {
  padding: 0.5rem;
}

@media (max-width: 750px) {
  .card img {
    width: 10rem;
    height: 10rem;
  }
  .card-text .title {
    font-size: 1.2rem;
    font-weight: bold;
  }
  .card-text .comment {
    padding: 0.25rem;
  }
}
</style>
