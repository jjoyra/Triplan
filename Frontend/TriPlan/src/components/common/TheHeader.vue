<template>
  <div>
    <div>
      <div :class="{ menuModal: isMenuOpen, closeMenuModal: !isMenuOpen }">
        <div class="side-menu-wrap">
          <router-link to="/"
            ><img id="side-logo" src="../../assets/logo.png" alt="logo.png"
          /></router-link>
          <div class="profile-wrap" v-if="userInfo" @click="movePage('/mypage')">
            <div class="profileImg-wrap">
              <img id="profileImg" src="../../assets/300.png" alt="300.png" />
              <span>{{ userInfo.nickname }}</span>
            </div>
            <div>
              <span>MY</span>
              <b-icon class="rightArrow" icon="ChevronRight"></b-icon>
            </div>
          </div>
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

          <div v-if="userInfo">플랜 만들기 로그아웃</div>
          <template v-else>
            <b-button
              variant="primary"
              v-b-modal.modal-login
              @click="
                () => {
                  isMenuOpen = false;
                }
              "
              >로그인</b-button
            >
            <b-button
              variant="dark"
              v-b-modal.modal-registe
              @click="
                () => {
                  isMenuOpen = false;
                }
              "
              >회원가입</b-button
            >
          </template>
        </div>
      </div>
      <div :class="{ overlay: isMenuOpen }" @click="handleMenuClose"></div>
    </div>
    <div class="header-wrap">
      <b-nav pills>
        <div class="logo-search-wrap">
          <div class="logo-menu-wrap">
            <b-icon class="list-icon" icon="List" v-on:click="handleMenu"></b-icon>
            <router-link to="/"
              ><img id="logo" src="../../assets/logo.png" alt="logo.png"
            /></router-link>
          </div>
          <div :class="{ searchWrap: true, searchDisplay: !isMain }">
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

          <div class="profile-wrap" v-if="userInfo">
            <img id="profileImg" src="../../assets/300.png" alt="300.png" />
            <b-nav-item-dropdown
              id="profile-dropdown"
              :text="userInfo.nickname"
              toggle-class="nav-link-custom"
              right
            >
              <b-dropdown-item>
                <b-nav-item to="/mypage" exact exact-active-class="active">마이페이지</b-nav-item>
              </b-dropdown-item>
              <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item>
                <b-nav-item to="/plan/write" exact exact-active-class="active"
                  >플랜 만들기</b-nav-item
                >
              </b-dropdown-item>
              <b-dropdown-item>
                <b-nav-item @click="handleLogout" exact exact-active-class="active"
                  >로그아웃</b-nav-item
                >
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </div>
          <template v-else>
            <b-button
              class="nav-link btn-nav"
              v-b-modal.modal-login
              @click="
                () => {
                  isMenuOpen = false;
                }
              "
              >로그인</b-button
            >
            <b-button
              class="nav-link btn-nav"
              v-b-modal.modal-registe
              @click="
                () => {
                  isMenuOpen = false;
                }
              "
              >회원가입</b-button
            >
          </template>
        </div>
      </b-nav>
    </div>

    <b-modal id="modal-login" hide-footer centered title="로그인" no-stacking>
      <b-form @submit.stop.prevent="onLoginSubmit">
        <b-form-group label="아이디" label-for="memberId" label-cols-sm="3" label-align-sm="left">
          <b-form-input
            type="text"
            v-model="memberId"
            id="memberId"
            name="memberId"
            placeholder="아이디를 입력해주세요"
            :state="null"
            trim
          ></b-form-input>
        </b-form-group>

        <b-form-group label="비밀번호" label-for="password" label-cols-sm="3" label-align-sm="left">
          <b-form-input
            type="password"
            v-model="password"
            id="password"
            name="password"
            placeholder="비밀번호를 입력해주세요"
            :state="null"
            trim
          ></b-form-input>
        </b-form-group>

        <b-form-group class="msg">{{ message }}</b-form-group>

        <div class="btn-modal-wrap">
          <b-button type="submit" variant="primary" class="btn">로그인</b-button>
          <b-button class="btn" @click="modalClose('modal-login')">닫기</b-button>
        </div>
      </b-form>
    </b-modal>

    <b-modal id="modal-registe" hide-footer centered title="회원가입" no-stacking>
      <b-form @submit.stop.prevent="onSignUpSubmit">
        <b-form-group
          v-for="(item, index) in signFormList"
          :key="index"
          :label="item.label"
          :label-for="item.labelFor"
          label-cols-sm="3"
          label-align-sm="left"
        >
          <b-form-input
            :type="item.type"
            v-model="item.value"
            :id="item.id"
            :name="item.name"
            :placeholder="item.placeholder"
            :state="inputState(index, item.min, item.max)"
            :aria-describedby="item.ariaDescribedby"
            trim
          ></b-form-input>
          <b-form-invalid-feedback :id="item.ariaDescribedby">
            {{ item.feedback }}
          </b-form-invalid-feedback>
        </b-form-group>

        <b-form-group class="msg">{{ message }}</b-form-group>

        <div class="btn-modal-wrap">
          <b-button type="submit" variant="primary" class="btn">회원가입</b-button>
          <b-button class="btn" @click="modalClose('modal-registe')">닫기</b-button>
        </div>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const CONFIRM_INPUT_IDX = 4;

import SearchInput from "@/components/common/SearchInput.vue";

export default {
  mixins: [validationMixin],
  name: "TheHeader",
  data() {
    return {
      width: 0,
      isMenuOpen: false,
      isMain: true,
      memberId: "",
      password: "",
      message: "",
      signMemberId: "",
      signPassword: "",
      signPasswordConfrim: "",
      signName: "",
      signNickname: "",
      signFormList: [
        {
          label: "이름",
          labelFor: "signName",
          type: "text",
          value: "",
          id: "signName",
          name: "name",
          placeholder: "이름을 입력해주세요",
          ariaDescribedby: "input-signName-feedback",
          feedback: "이름은 2자 이상 6자 이하입니다.",
          min: 2,
          max: 6,
        },
        {
          label: "닉네임",
          labelFor: "signNickname",
          type: "text",
          value: "",
          id: "signNickname",
          name: "nickname",
          placeholder: "닉네임을 입력해주세요",
          ariaDescribedby: "input-signNickname-feedback",
          feedback: "닉네임는 2자 이상 6자 이하입니다.",
          min: 2,
          max: 6,
        },
        {
          label: "아이디",
          labelFor: "signMemberId",
          type: "text",
          value: "",
          id: "signMemberId",
          name: "memberId",
          placeholder: "아이디를 입력해주세요",
          ariaDescribedby: "input-signMemberId-feedback",
          feedback: "아이디는 4자 이상 10자 이하입니다.",
          min: 4,
          max: 10,
        },
        {
          label: "비밀번호",
          labelFor: "signPassword",
          type: "password",
          value: "",
          id: "signPassword",
          name: "password",
          placeholder: "비밀번호를 입력해주세요",
          ariaDescribedby: "input-signPassword-feedback",
          feedback: "비밀번호는 4자 이상 10자 이하입니다.",
          min: 4,
          max: 10,
        },
        {
          label: "비밀번호 확인",
          labelFor: "signPasswordConfrim",
          type: "text",
          value: "",
          id: "signPasswordConfrim",
          // name: "signPasswordConfrim",
          placeholder: "비밀번호를 다시 입력해주세요",
          ariaDescribedby: "input-signPasswordConfrim-feedback",
          feedback: "비밀번호가 일치하지 않습니다.",
          min: 4,
          max: 10,
        },
      ],
    };
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userLogout"]),
    async onLoginSubmit() {
      let params = {
        memberId: this.memberId,
        password: this.password,
      };
      console.log("로그인", params);

      await this.userConfirm(params);

      this.memberId = "";
      this.password = "";

      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.message = "";
        this.$bvModal.hide("modal-login");
        alert(`${this.userInfo.nickname} 님 환영합니다!`);
      } else {
        console.log("로그인 실패");
        this.message = "아이디와 비밀번호를 확인해주세요!";
      }
    },
    async onSignUpSubmit() {
      console.log("v", this);
      console.log("v", this.$v);
      console.log("v", this.v$);
      console.log("회원가입");
    },
    handleLogout() {
      console.log("로그아웃");
      console.log(this.userInfo.memberId);
      this.userLogout(this.userInfo.memberId);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      alert("로그아웃 되셨습니다.");
      if (this.$route.path != "/") this.$router.push("/");
    },
    modalClose(id) {
      this.$bvModal.hide(id);
    },
    handleMenu() {
      this.isMenuOpen = !this.isMenuOpen;
    },
    handleMenuClose() {
      this.isMenuOpen = false;
    },
    handleResize() {
      this.width = window.innerWidth;
    },
    movePage(link) {
      if (this.$route.path != link) this.$router.push(link);
      else this.handleMenuClose();
    },
    inputState(idx, min, max) {
      if (idx === CONFIRM_INPUT_IDX) return this.passwordConfirmState();
      if (this.signFormList[idx].value.length === 0) return null;
      if (
        this.signFormList[idx].value.length >= min &&
        this.signFormList[idx].value.length <= max
      ) {
        return true;
      }
      return false;
    },
    passwordConfirmState() {
      if (this.signFormList[CONFIRM_INPUT_IDX].value.length === 0) return null;
      if (
        this.signFormList[CONFIRM_INPUT_IDX - 1].value ===
        this.signFormList[CONFIRM_INPUT_IDX].value
      ) {
        return true;
      }
      return false;
    },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  components: {
    SearchInput,
  },
  watch: {
    $route(to) {
      if (to.fullPath === "/") {
        this.isMain = true;
      } else if (this.$route.fullPath !== "/" && this.width < 950) {
        this.isMain = false;
      } else {
        this.isMain = true;
      }
      this.handleMenuClose();
    },
    width(val) {
      if (this.$route.fullPath === "/") {
        this.isMain = true;
      } else if (this.$route.fullPath !== "/" && val > 950) {
        this.isMain = true;
      } else {
        this.isMain = false;
      }
      this.handleMenuClose();
    },
  },
  mounted() {
    window.addEventListener("resize", this.handleResize);
    this.width = window.innerWidth;
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
  },
};
</script>

<style scope>
.header-wrap {
  padding: 10px 30px;
  margin: 0 auto;
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
  color: #343a40;
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
  height: calc(1.5em + 0.75rem - 2px);
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
  gap: 5px;
}
.profile-wrap #profileImg {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.nav-pills .nav-link.active,
.nav-pills .show > .nav-link {
  background: transparent;
  color: #343a40;
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

.msg {
  color: #f35151;
}

.btn-modal-wrap {
  display: flex;
  justify-content: flex-end;
  gap: 20px;
}

.modal-title {
  font-weight: bold;
}

@media (max-width: 950px) {
  .menuModal .side-menu-wrap {
    display: flex;
    flex-direction: column;
    gap: 20px;
  }

  .menuModal .side-menu-wrap #side-logo {
    margin: 0 auto;
    height: calc(1.5em + 0.75rem - 2px);
    display: flex;
  }

  .closeMenuModal .side-menu-wrap {
    display: none;
  }

  .side-menu-wrap div {
    cursor: pointer;
    position: relative;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }

  .profile-wrap {
    padding-right: 1rem;
  }
  .profile-wrap .profileImg-wrap {
    gap: 1rem;
  }
  .profile-wrap .profileImg-wrap #profileImg {
    width: 50px;
    height: 50px;
  }

  .side-menu-wrap div svg.rightArrow {
    position: absolute;
    top: 49%;
    left: 1.5rem;
    transform: translateY(-50%);
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
    background-color: #fff;
    z-index: 21;
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
    z-index: 20;
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
