<template>
  <div id="map"></div>
</template>

<script>
/*global kakao*/
export default {
  name: "KaKaoMap",
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
    };
  },
  props: {
    attractions: [],
  },
  watch: {
    attractions() {
      this.positions = [];
      this.attractions.forEach((attraction) => {
        let obj = {};
        obj.title = attraction.title;
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        this.positions.push(obj);
      });
      this.loadMarker();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    },

    //지정한 위치에 마커 불러오기
    loadMarker() {
      this.deleteMarker();

      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
          title: position.title,
        });
        this.markers.push(marker);
      });

      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },

    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }
    },
  },
};
</script>

<style scope>
#map {
  width: 100%;
  height: 560px;
  background: #fafafa;
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
