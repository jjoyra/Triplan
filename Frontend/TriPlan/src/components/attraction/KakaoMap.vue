<template>
  <div id="map"></div>
</template>

<script>
import { getAttractionDetail } from "@/api/attraction";

export default {
  /*global kakao*/
  name: "KaKaoMap",
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
      peekMarker: null,
      content: null,
      overlay: null,
      distanceOverlay: null,
    };
  },
  props: {
    attractions: [],
    peekList: Number,
    courseList: [],
  },
  watch: {
    attractions() {
      this.positions = [];
      if (this.attractions.length != 0) {
        this.attractions.forEach((attraction) => {
          let obj = {};
          obj.title = attraction.title;
          obj.addr1 = attraction.addr1;
          obj.firstImage = attraction.firstImage;
          obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
          this.positions.push(obj);
        });
        this.loadMarker();
      }
    },
    peekList() {
      if (this.attractions.length != 0) {
        let obj = {};
        this.attractions.forEach((attraction) => {
          if (attraction.contentId == this.peekList) {
            obj.title = attraction.title;
            obj.addr1 = attraction.addr1;
            obj.firstImage = attraction.firstImage;
            obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
            this.openOverlay(obj);
          }
        });
      }
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
        center: new window.kakao.maps.LatLng(35.18998133576961, 126.82424707736024), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      this.overlay = new kakao.maps.CustomOverlay({
        position: null,
      });

      kakao.maps.event.addListener(this.map, "click", function (mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;

        var message = "클릭한 위치의 위도는 " + latlng.getLat() + " 이고, ";
        message += "경도는 " + latlng.getLng() + " 입니다";

        console.log(message);
      });

      if (this.courseList) {
        this.markerCourse();
      }
    },
    async markerCourse() {
      for (let i = 0; i < this.courseList.length; i++) {
        await getAttractionDetail(
          this.courseList[i].contentId,
          ({ data }) => {
            // map에 마커 띄우기
            let obj = {
              latlng: new kakao.maps.LatLng(data.attraction.latitude, data.attraction.longitude),
              title: data.attraction.title,
              contentId: data.attraction.contentId,
              firstImage: data.attraction.firstImage,
              addr1: data.attraction.addr1,
              order: this.courseList[i].order,
            };
            this.positions.push(obj);
          },
          (err) => console.log(err)
        );
      }
      this.loadMarker();
    },

    //지정한 위치에 마커 불러오기
    loadMarker() {
      let latlng = [];
      if (this.overlay) this.closeOverlay();
      this.deleteMarker();

      this.markers = [];

      for (let i = 0; i < this.positions.length; i++) {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: this.positions[i].latlng,
        });
        kakao.maps.event.addListener(marker, "click", () => {
          this.peekMarker = this.positions[i];
          this.openOverlay(this.peekMarker);
        });

        latlng.push(this.positions[i].latlng);
        this.markers.push(marker);
      }

      // 지도 이동
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );
      this.map.setBounds(bounds);

      if (this.courseList && this.courseList.length != 0) {
        this.setPath(latlng);
      }
    },
    setPath(latlng) {
      let line = new kakao.maps.Polyline({
        map: this.map, // 선을 표시할 지도입니다
        path: latlng, // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: "#3e96df", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      let distance = Math.round(line.getLength());
      let content = this.getTimeHTML(distance);
      this.showDistance(content, latlng[latlng.length - 1]);
    },
    showDistance(content, position) {
      if (this.distanceOverlay) {
        // 커스텀오버레이가 생성된 상태이면

        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
        this.distanceOverlay.setPosition(position);
        this.distanceOverlay.setContent(content);
      } else {
        // 커스텀 오버레이가 생성되지 않은 상태이면

        // 커스텀 오버레이를 생성하고 지도에 표시합니다
        this.distanceOverlay = new kakao.maps.CustomOverlay({
          map: this.map, // 커스텀오버레이를 표시할 지도입니다
          content: content, // 커스텀오버레이에 표시할 내용입니다
          position: position, // 커스텀오버레이를 표시할 위치입니다.
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3,
        });
      }
    },
    getTimeHTML(distance) {
      // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
      var walkkTime = (distance / 67) | 0;
      var walkHour = "",
        walkMin = "";

      // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
      if (walkkTime > 60) {
        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + "</span>시간 ";
      }
      walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

      // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
      var bycicleTime = (distance / 227) | 0;
      var bycicleHour = "",
        bycicleMin = "";

      // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
      if (bycicleTime > 60) {
        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + "</span>시간 ";
      }
      bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";

      // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
      var content = '<ul class="dotOverlay distanceInfo">';
      content += "    <li>";
      content +=
        '        <span class="label">총거리</span><span class="number">' + distance + "</span>m";
      content += "    </li>";
      content += "    <li>";
      content += '        <span class="label">도보</span>' + walkHour + walkMin;
      content += "    </li>";
      content += "    <li>";
      content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
      content += "    </li>";
      content += "</ul>";

      return content;
    },
    openOverlay(peek) {
      this.content = `<div class="wrap">
                        <div class="overlay">
                          <div class="title">
                            ${peek.title}
                          </div>
                          <div class="body">
                            <div class="img">
                              <img src="${peek.firstImage}" width="73" height="70" onerror="this.style.display='none'">
                            </div>
                            <div class="desc">
                              <div class="ellipsis">${peek.addr1}</div>
                            </div>
                          </div>
                        </div>
                      </div>`;

      this.overlay.setContent(this.content);
      this.overlay.setPosition(peek.latlng);
      this.overlay.setMap(this.map);

      // 지도 이동
      // this.map.setCenter(peek.latlang);
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

<style>
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
.wrap .overlay {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .overlay:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.overlay .title {
  padding: 5px 0 0 10px;
  height: 30px;
  font-size: 18px;
  font-weight: bold;
}
.overlay .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.overlay .close:hover {
  cursor: pointer;
}
.overlay .body {
  position: relative;
  overflow: hidden;
}
.overlay .desc {
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
.overlay .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  color: #888;
  overflow: hidden;
}
.overlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.overlay .link {
  color: #5085bb;
}
.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png");
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: #ee6152;
}
.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}
</style>
