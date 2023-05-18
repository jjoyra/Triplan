<template>
<div>
  <div>
    <div :class="{menuModal: isMenuOpen, closeMenuModal: !isMenuOpen}">
      <div class="side-menu-wrap">
        <router-link to="/"><img id="side-logo" src="../../assets/logo.png" alt="logo.png" /></router-link>
        <div>
          <b-icon icon="Bell"></b-icon>
          <b-nav-item to="/notice" exact exact-active-class="active">공지사항</b-nav-item>
        </div>
        <div>
          <b-icon icon="GeoAlt"></b-icon>
          <b-nav-item to="/attraction" exact exact-active-class="active">관광지 찾기</b-nav-item>
        </div>
        <div>
          <b-icon icon="ChatLeftDots"></b-icon>
          <b-nav-item to="/community" exact exact-active-class="active">커뮤니티</b-nav-item>
        </div>
        <div>
          <b-icon icon="CardText"></b-icon>
          <b-nav-item to="/review" exact exact-active-class="active">플랜리뷰</b-nav-item>
        </div>
        
        <div class="profile-wrap">
          <img id="profileImg" src="../../assets/300.png" alt="300.png" />
          <b-nav-item-dropdown
            id="profile-dropdown"
            text="임하스"
            toggle-class="nav-link-custom"
            right
          >
            <b-dropdown-item>
              <b-nav-item to="/mypage" exact exact-active-class="active">마이페이지</b-nav-item>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item>
              <b-nav-item to="/plan/write" exact exact-active-class="active">플랜 만들기</b-nav-item>
            </b-dropdown-item>
            <b-dropdown-item>
              <b-nav-item to="/review" exact exact-active-class="active">로그아웃</b-nav-item>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </div>
      </div>
    </div>
    <div :class="{overlay: isMenuOpen}" @click="handleMenuClose"></div>
  </div>
  <div class="header-wrap">
    <b-nav pills>
      <div class="logo-search-wrap">
        <div class="logo-menu-wrap">
          <b-icon class="list-icon" icon="List" v-on:click="handleMenu"></b-icon>
          <router-link to="/"><img id="logo" src="../../assets/logo.png" alt="logo.png" /></router-link>
        </div>
        <div :class="{searchWrap: true, searchDisplay: !isMain}">
          <search-input placeholderTxt="여행지를 찾아보세요"></search-input>
        </div>
      </div>

      <div class="menu-wrap">
        <div>
          <b-icon icon="Bell"></b-icon>
          <b-nav-item to="/notice" exact exact-active-class="active">공지사항</b-nav-item>
        </div>
        <div>
          <b-icon icon="GeoAlt"></b-icon>
          <b-nav-item to="/attraction" exact exact-active-class="active">관광지 찾기</b-nav-item>
        </div>
        <div>
          <b-icon icon="ChatLeftDots"></b-icon>
          <b-nav-item to="/community" exact exact-active-class="active">커뮤니티</b-nav-item>
        </div>
        <div>
          <b-icon icon="CardText"></b-icon>
          <b-nav-item to="/review" exact exact-active-class="active">플랜리뷰</b-nav-item>
        </div>
        
        <div class="profile-wrap">
          <img id="profileImg" src="../../assets/300.png" alt="300.png" />
          <b-nav-item-dropdown
            id="profile-dropdown"
            text="임하스"
            toggle-class="nav-link-custom"
            right
          >
            <b-dropdown-item>
              <b-nav-item to="/mypage" exact exact-active-class="active">마이페이지</b-nav-item>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item>
              <b-nav-item to="/plan/write" exact exact-active-class="active">플랜 만들기</b-nav-item>
            </b-dropdown-item>
            <b-dropdown-item>
              <b-nav-item to="/review" exact exact-active-class="active">로그아웃</b-nav-item>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </div>
      </div>
    </b-nav>
  </div>
</div>
</template>

<script>
import SearchInput from '@/components/common/SearchInput.vue';

export default {
  name: 'TheHeader',
  data() {
    return {
      width: 0,
      isMenuOpen: false,
      isMain: true,
    }
  },
  methods: {
    handleMenu() {
      this.isMenuOpen = !this.isMenuOpen;
    },
    handleMenuClose() {
      this.isMenuOpen = false;
    },
    handleResize() {
      this.width = window.innerWidth;
    }
  },
  components: {
    SearchInput,
  },
  watch: {
    '$route' (to) {
      if (to.fullPath === '/') {
        this.isMain = true;
      } else if (this.$route.fullPath !== '/' && this.width < 950) {
        this.isMain = false;
      } else {
        this.isMain = true;
      }
      this.isMenuOpen = false;
    },
    width (val) {
      if (this.$route.fullPath === '/') {
        this.isMain = true;
      } else if (this.$route.fullPath !== '/' && val > 950) {
        this.isMain = true;
      } else {
        this.isMain = false;
      }
      this.isMenuOpen = false;
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
    this.width = window.innerWidth;
	},
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
}
</script>

<style scope>
.header-wrap {
  padding: 10px 30px;
  margin : 0 auto;
  max-width: 1280px;
}

.nav {
  align-items: center;
}
.nav.nav-pills {
  justify-content: space-between;
}

.nav a {
  font-size: 1rem;
  color: #343A40;
  flex-wrap: wrap;
}

.nav .nav-item {
  display: inline-block;
}
.nav .nav-item .nav-link {
  padding-left: 5px;
}

.logo-search-wrap {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.logo-search-wrap #logo {
  margin-right: 1rem;
  height: calc(1.5em + .75rem - 2px);
}
.logo-search-wrap .logo-menu-wrap .list-icon {
  display: none;
}

.menu-wrap {
  display: flex;
  flex-wrap: wrap;
}

.profile-wrap {
  display: flex;
  flex-direction: row;
}
.profile-wrap #profileImg {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.nav-pills .nav-link.active, .nav-pills .show>.nav-link {
  background: transparent;
  color: #343A40;
}

.closeMenuModal .side-menu-wrap,
.menuModal .side-menu-wrap {
  display: none;
}
.menuModal {
  width: 0;
}

.searchWrap {
  width: fit-content;
}
.searchDisplay {
  display: none;
}

@media (max-width: 950px) {
  .menuModal .side-menu-wrap {
    display: block;
  }

  .menuModal .side-menu-wrap #side-logo {
    margin: 0 auto;
    height: calc(1.5em + .75rem - 2px);
    display: block;
  }

  .closeMenuModal .side-menu-wrap {
    display: none;
  }

  .side-menu-wrap div {
    position: relative;
    display: flex;
    flex-direction: row;
  }
  .side-menu-wrap div svg {
    position: absolute;
    top: 50%;
    left: 1rem;
    transform: translateY(-50%);
  }
  .side-menu-wrap div li {
    padding-left: 1.25rem;
  }


  .closeMenuModal,
  .menuModal,
  .overlay {
    width: 100%;
    height: 100vh;
    position: fixed;
    left: 0;
    top: 0;
  }

  .closeMenuModal,
  .menuModal {
    width: 70%;
    background-color: #FFF;
    z-index: 12;
    transition: left 0.5s;
  }

  .menuModal {
    left: 0;
    padding: 1rem;
  }
  .closeMenuModal {
    left: -70%;
  }
  
  .overlay {
    opacity: 0.5;
    background-color: #000;
    z-index: 11;
    transition: all 0.5s ease-in-out;
  }

  .header-wrap {
    padding: 6px;
  }

  .logo-search-wrap {
    position: relative;
    width: 100%;
    margin: 0 auto;
    padding: 0 10px;
    flex-direction: column;
    align-items: center;
  }

  .logo-search-wrap #logo {
    margin: 4px 0 14px 0;
  }

  .logo-search-wrap .logo-menu-wrap .list-icon {
    cursor: pointer;
    display: inline-block;
    position: absolute;
    top: calc(20% - 2px);
    left: 1rem;
    transform: translateY(-25%);
    font-size: 1.5rem;
  }

  .searchWrap {
    width: 100%;
  }

  .logo-search-wrap .form-inline {
    width: 100%;
  }

  #input-1 {
    width: 100%;
    padding-left: 10%;
    padding-bottom: 0.4rem;
  }
  #input-1::placeholder {
    position: absolute;
    top: 50%;
    left: 10%;
  }

  .menu-wrap {
    display: none;
  }
  
}

</style>