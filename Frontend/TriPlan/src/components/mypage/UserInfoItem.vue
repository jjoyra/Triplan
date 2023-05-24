<template>
  <div id="userInfo-wrap">
    <div class="userInfo-content">
      <img id="profileImg" src="../../assets/300.png" alt="300.png" />
      <div class="header-wrap">
        <div class="info">
          <div class="name">
            {{ pageUserInfo.nickname }}
          </div>
          <div>
            <button class="btn btn-round" v-if="isMyInfo">정보 수정하기</button>
            <template v-else>
              <button
                :class="{ btn: true, 'btn-round-primary': !followee, 'btn-round': followee }"
                @click="handleFollow"
              >
                {{ followee ? "언팔로우" : "팔로우" }}
              </button>
            </template>
          </div>
        </div>
        <div class="follower">
          <div>팔로워 {{ pageUserInfo.follower.length }}</div>
          <div>팔로우 {{ pageUserInfo.followee.length }}</div>
        </div>
      </div>
    </div>
    <div class="comment">{{ pageUserInfo.comment }}</div>
  </div>
</template>

<script>
import { follow, unfollow } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "userInfoItem",
  props: {
    pageUserInfo: Object,
    isMyInfo: Boolean,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      followee: false,
    };
  },
  created() {
    console.log("info", this.pageUserInfo);
    if (!this.isMyInfo) {
      this.followCheck();
    }
  },
  methods: {
    handleFollow() {
      if (!this.isMyInfo && this.userInfo === null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$bvModal.show("modal-login");
        return;
      }

      let params = {
        followerId: this.pageUserInfo.memberId,
        followeeId: this.userInfo.memberId,
      };
      console.log("params", params);
      if (this.followee) {
        console.log("언팔로우");
        unfollow(
          params,
          (res) => {
            this.followee = !this.followee;
            this.$emit("get-page-user-info");
            console.log("성공");
            console.log(res);
          },
          (err) => console.log(err)
        );
      } else {
        console.log("팔로우");
        follow(
          params,
          (res) => {
            this.followee = !this.followee;
            this.$emit("get-page-user-info");
            console.log("성공");
            console.log(res);
          },
          (err) => console.log(err)
        );
      }
    },
    followCheck() {
      if (this.pageUserInfo.follower.length !== 0) {
        for (let f of this.pageUserInfo.follower) {
          if (f === this.userInfo.nickname) {
            console.log("팔로우 맞습니다");
            this.followee = true;
            break;
          }
        }
      }
    },
  },
};
</script>

<style scoped>
#userInfo-wrap {
  padding: 1rem 5rem 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  border-bottom: 0.3px solid #e5e5e5;
  margin-bottom: 2rem;
}
#userInfo-wrap .comment {
  color: #5f5f5f;
  font-size: 1.15rem;
  padding: 1rem 2rem;
}

.userInfo-content {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.userInfo-content .header-wrap {
  width: 100%;
  height: 100%;
  padding: 0 2rem;
  gap: 1.5rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.userInfo-content .header-wrap .info,
.userInfo-content .header-wrap .follower {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.userInfo-content .header-wrap .info {
  justify-content: space-between;
}
.userInfo-content .header-wrap .follower {
  gap: 2rem;
}

.info .name {
  font-size: 1.5rem;
}

#profileImg {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

@media (max-width: 950px) {
  #userInfo-wrap {
    padding: 1rem;
  }
  #userInfo-wrap .comment {
    font-size: 1rem;
    padding: 1rem;
  }

  .userInfo-content .header-wrap {
    padding: 0 1rem;
    gap: 1rem;
  }

  #profileImg {
    width: 80px;
    height: 80px;
    border-radius: 50%;
  }

  .info .name {
    font-size: 1.25rem;
  }
}
</style>
