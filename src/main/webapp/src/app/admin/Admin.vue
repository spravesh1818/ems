<template>
  <div class="container">
    <navbar navbar-title="Admin Dashboard"></navbar>
    <div class="columns">
      <div class="column is-one-fifth">
        <SidebarComponent></SidebarComponent>
      </div>
      <div class="column is-four-fifths">
        <router-view></router-view>
      </div>

    </div>


    <div v-if="isLogoutModalActive" class="modal is-active">
      <div class="modal-background" @click="isLogoutModalActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">

            <p>Are you sure you want to logout?</p>


            <div class="field">
              <p class="control">
                <button class="button is-danger m-3" @click="logout">
                  Logout
                </button>
                <button class="button is-primary m-3" @click="isLogoutModalActive=false">
                  Cancel
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <button aria-label="close" class="modal-close is-large" @click="isLogoutModalActive=false"></button>
    </div>
  </div>
</template>

<script>



import SidebarComponent from "@/app/admin/shared/components/AdminSidebarComponent";
import Navbar from "@/app/shared/components/Navbar";
export default {
  name: "Admin",
  components: {Navbar, SidebarComponent},
}
</script>

<style scoped>

</style>