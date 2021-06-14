<template>
  <div class="rows">
    <div class="row is-full tile is-parent">
      <article class="tile is-child notification is-danger">
        <p class="title">User Profile</p>
        <p class="subtitle">View and Edit User Info</p>
        <div class="navbar-end">
          <div class="navbar-item">
          </div>
        </div>
        <div class="content">

        </div>
      </article>
    </div>
    <div class="row is-full tile is-parent">
      <div class="tile is-child box">
        <div>
          User Id:{{userInfoObject.id}}
        </div>
        <div>
          Email:{{userInfoObject.email}}
        </div>
        <div>
          Password:**************
          <button class="button is-danger is-light" @click="isChangePasswordModalActive=true">Change Password</button>
        </div>
      </div>
    </div>

  </div>


  <div v-if="isChangePasswordModalActive" class="modal is-active">
    <div class="modal-background" @click="isChangePasswordModalActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <div class="field">
            <h3>Change Your Password</h3>

            <p class="control has-icons-left has-icons-right">
              <input class="input" placeholder="Old Password" v-model="changePasswordForm.oldPassword" type="password" required>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="New Password" v-model="changePasswordForm.newPassword" type="password" required>
            </p>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Confirm New Password" v-model="changePasswordForm.confirmNewPassword" type="password" required>
            </p>
          </div>


          <div class="field">
            <p class="control">
              <button class="button is-success" @click="changePasswordAction()">
                Update
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>
    <button aria-label="close" class="modal-close is-large" @click="isChangePasswordModalActive=false"></button>
  </div>
</template>

<script>
import {changePassword, getUserInformation} from "@/app/shared/services/userInformationServices";

export default{
  name:"ShiftList",
  mounted() {
    this.userInfo();
  },
  data(){
    return{
      userInfoObject:{},
      changePasswordForm:{
        user:this.userInfoObject,
        oldPassword:null,
        newPassword:null,
        confirmNewPassword:null,
      },
      isChangePasswordModalActive:false
    }
  },
  methods:{
    userInfo(){
      const resp=getUserInformation();
      resp.then((data)=>{
        console.log(data);
        this.userInfoObject=data.data;
      })
    },
    changePasswordAction(){
      this.changePasswordForm.user=this.userInfoObject;
      const resp=changePassword(this.changePasswordForm);
      resp.then((data)=>{
        console.log(data);
      });
    }
  }
}

</script>