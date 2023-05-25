<template>
  <div id="wrap">
    <div class="side-wrap">
      <attraction-search-bar></attraction-search-bar>
      <attraction-list
        @clicked-attraction="openOverlay"
        @add-plan-attraction="addPlanAttraction"
      ></attraction-list>
    </div>
    <div class="side-wrap write-plan">
      <b-form @submit="onPlanSubmit">
        <b-row>
          <b-col
            ><b-form-datepicker
              v-model="form.startDate"
              placeholder="연도 . 월 . 일"
            ></b-form-datepicker
          ></b-col>
          <b-col
            ><b-form-datepicker
              v-model="form.endDate"
              placeholder="연도 . 월 . 일"
            ></b-form-datepicker
          ></b-col>
        </b-row>
        <div>
          <b-form-input v-model="form.title" placeholder="플랜 이름을 작성해주세요."></b-form-input>
          <b-button type="submit">등록</b-button>
        </div>
        <div class="plan-list">
          <b-card
            v-for="(attraction, index) in planAttractions"
            :key="index"
            :img-src="attraction.firstImage"
            img-alt="Card image"
            img-left
            class="mb-3"
          >
            <b-card-text>
              {{ attraction.title }}
            </b-card-text>
          </b-card>
        </div>
      </b-form>
    </div>
    <kakao-map :attractions="attractions" :peekList="peekList"></kakao-map>
  </div>
</template>

<script>
import KakaoMap from "@/components/attraction/KakaoMap.vue";
import AttractionSearchBar from "@/components/attraction/AttractionSearchBar.vue";
import AttractionList from "@/components/attraction/AttractionList.vue";
import { mapState } from "vuex";
import { registPlan } from "@/api/plan";

const attractionStore = "attractionStore";
const memberStore = "memberStore";

export default {
  name: "MyPlanWrite",
  data() {
    return {
      form: {
        title: "",
        startDate: "",
        endDate: "",
      },
      members: {
        owner: "",
        member: [],
      },
      peekList: null,
      planAttractions: [],
    };
  },
  computed: {
    ...mapState(attractionStore, ["attractions"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  components: {
    KakaoMap,
    AttractionSearchBar,
    AttractionList,
  },
  methods: {
    onPlanSubmit(event) {
      event.preventDefault();
      let thumbnailUrl = "";
      for (let i = 0; i < this.planAttractions.length; i++) {
        if (this.planAttractions[i].firstImage != "") {
          thumbnailUrl = this.planAttractions[i].firstImage;
          break;
        }
      }

      let courseList = [];

      for (let i = 0; i < this.planAttractions.length; i++) {
        console.log(this.planAttractions[i]);
        let course = {
          contentId: this.planAttractions[i].contentId,
          order: i + 1,
        };

        courseList.push(course);
      }

      this.members.owner = this.userInfo.memberId;

      let param = {
        title: this.form.title,
        startDate: this.form.startDate,
        endDate: this.form.endDate,
        thumbnailUrl: thumbnailUrl,
        members: this.members,
        courseList: courseList,
      };
      alert(JSON.stringify(param));
      registPlan(
        param,
        ({ data }) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    openOverlay(contentid) {
      this.peekList = contentid;
    },
    addPlanAttraction(planAttractionsInfo) {
      this.planAttractions = planAttractionsInfo.attractions;
    },
  },
};
</script>

<style scoped>
#wrap {
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

.side-wrap {
  background-color: #fff;
  z-index: 21;
  width: 25%;
  position: absolute;
  padding: 1rem;
  height: calc(100vh - 60px);
}

.list-wrap {
  height: 100%;
}

.write-plan {
  right: 0%;
  height: 100%;
  overflow: auto;
}

.plan-list {
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

.card {
  /* width: 70%; */
}

img {
  width: 220px;
  height: 50%;
}

#map {
  height: calc(100vh - 60px);
}
</style>
