# 🏝TriPlan

<br />

<div align="center">

<img width='250px' alt="TriPlan_logo" src="https://user-images.githubusercontent.com/77854486/232034326-2d2db07e-ef10-43b3-a912-8132054903a1.png"/>

</div>

<br />

## ✈️ 서비스 소개

전국 여행지 조회 기능을 바탕으로 여행 계획을 짜고, 다른 사용자들과 공유할 수 있는 여행 계획 서비스입니다.

## ✨ Member

<div align="center">

| [<img src = "https://avatars.githubusercontent.com/u/77854486?v=4" width = 100>](https://github.com/Im-hass) | [<img src = "https://avatars.githubusercontent.com/u/90020798?v=4" width = 100>](https://github.com/jjoyra) |
| :----------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: |
|                                                    임희선                                                    |                                                   조희라                                                    |
|                                                    FE, BE                                                    |                                                   FE, BE                                                    |

</div>

## ✈️ 기술 스택

- Vue.js, Bootstrap
- SpringBoot, MyBatis
- MySQL

## ✈️ 주요 기능

### 여행지 찾기

- 한국관광정보의 국문관광정보서비스 API를 활용하여 지역별 여행지 정보를 제공합니다.
- 키워드, 여행지 유형, 지역 등 다양한 필터에 맞는 여행지를 검색할 수 있습니다.
- `KakaoMap API`를 사용하여 지도 위에서 여행지 위치를 보여줍니다.
- 추천수 기준 인기 여행지 TOP3 목록을 보여줍니다.

### 플랜

- 원하는 여행지들을 선택하여 여행 계획을 생성하고, 다른 사용자들과 공유할 수 있습니다.
- 계획한 여행 코스에 대해 이동 시간을 계산하여 보여줍니다.
- 여행을 다녀와서 여행 계획에 대한 후기를 남길 수 있습니다.

## 🏝 기능별 화면

|                                                                                                        |                                                                                                             |
| :----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: |
| ![메인페이지](https://github.com/jjoyra/Triplan/assets/90020798/afafc962-3e76-42f2-91c7-4645606fa96b)  | ![인기 여행지 TOP3](https://github.com/jjoyra/Triplan/assets/90020798/2841cd0a-7f9a-4161-b3de-5d4950e24aad) |
|                                              메인 페이지                                               |                                              인기 여행지 TOP3                                               |
| ![여행지 찾기](https://github.com/jjoyra/Triplan/assets/90020798/ccb35f92-6a87-4e32-9b67-73117ce1d123) |    ![플랜 생성](https://github.com/jjoyra/Triplan/assets/90020798/2951a26d-f086-45a6-93f1-8748186fa667)     |
|                                              여행지 찾기                                               |                                                  플랜 생성                                                  |
|  ![플랜 코스](https://github.com/jjoyra/Triplan/assets/90020798/a3b74485-d6d7-4a90-87f9-1bed0b38738e)  |  ![플랜 코스 MAP](https://github.com/jjoyra/Triplan/assets/90020798/196b65ca-5ddf-4ead-b7b2-7d4500df171a)   |
|                                               플랜 코스                                                |                                                플랜 코스 MAP                                                |
| ![마이페이지](https://github.com/jjoyra/Triplan/assets/90020798/6a5572b3-4f6b-4c07-a8a9-765bd6501223)  |     ![공지사항](https://github.com/jjoyra/Triplan/assets/90020798/0339c22c-98d2-4fdc-b548-579a0f013d70)     |
|                                               마이페이지                                               |                                                  공지사항                                                   |

<br />

## ✈️ 프로젝트 구조

<details> 
<summary>frontend</summary>

```bash
├─public
├─package-lock.json # 프로젝트 의존성 관리
├─package.json # 프로젝트 의존성 관리
└─src
  ├─App.vue # 시작 컴포넌트
  ├─api
  │  └─http.js # axios 기본 설정
  ├─assets # 이미지 파일
  ├─components
  │  ├─attraction # 관광지(여행지)
  │  ├─board # 글 Input
  │  ├─common # 헤더, 푸터 등
  │  ├─community # 커뮤니티
  │  ├─mypage # 마이페이지
  │  │  ├─favoattraction # 좋아하는 관광지(여행지)
  │  │  ├─favoplan # 좋아하는 여행계획
  │  │  └─myplan # 나의 여행계획
  │  ├─notice # 공지사항
  │  ├─review # 여행계획 리뷰
  │  └─ui # UI
  ├─plugins # vue axios, bootstrap 설정
  ├─router # 라우팅
  ├─store # Vuex
  └─views # 페이지 컴포넌트
    ├─MainView.vue # 메인 컴포넌트
    └─...
```

</details>
