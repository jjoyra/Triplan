<template>
  <div id="map"></div>
</template>

<script>
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  /*global kakao*/
  name: "KaKaoMap",
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
      peek: null,
      content: null,
      overlay: null,
    };
  },
  props: {
    attractions: [],
  },
  computed: {
    ...mapState(attractionStore, ["attraction"]),
  },
  watch: {
    attractions() {
      this.positions = [];
      if (this.attractions.length) {
        this.attractions.forEach((attraction) => {
          let obj = {};
          obj.title = attraction.title;
          obj.addr1 = attraction.addr1;
          obj.firstImage = attraction.firstImage;
          obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
          this.positions.push(obj);
        });
      }
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

      //제목, 주소, 사진

      this.overlay = new kakao.maps.CustomOverlay({
        position: null,
      });
    },

    //지정한 위치에 마커 불러오기
    loadMarker() {
      this.closeOverlay();
      this.deleteMarker();

      this.markers = [];

      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
        });

        kakao.maps.event.addListener(marker, "click", () => {
          this.peek = position;
          this.content = `<div class="wrap">
                <div class="info">
                    <div class="title">
                        ${this.peek.title}
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${this.peek.firstImage}" width="73" height="70" onerror="this.style.display='none'">
                          </div>
                        <div class="desc">
                            <div class="ellipsis">${this.peek.addr1}</div>
                      </div>
                    </div>'
                </div>
            </div>`;

          this.overlay.setContent(this.content);
          this.overlay.setPosition(marker.getPosition());
          this.overlay.setMap(this.map);
        });

        this.markers.push(marker);
      });

      // 지도 이동
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    closeOverlay() {
      this.overlay.setMap(null);
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

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}

@media (max-width: 950px) {
  #map {
    height: 453px;
  }
}
</style>
