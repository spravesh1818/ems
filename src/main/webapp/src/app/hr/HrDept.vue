<template>
  <div class="container">
    <Navbar navbar-title="HR Dashboard"></Navbar>

    <div class="columns">
      <div class="column is-one-fifth">
        <HRSidebar></HRSidebar>
      </div>
      <div class="column is-four-fifths">
        <router-view></router-view>
      </div>

      <div class="column is-four-fifths">

      </div>

    </div>
    <div class="columns">
      <div class="column is-one-fifths"></div>
      <div class="column is-four-fifths">

      </div>

    </div>

    <div v-if="isAddEmployeesActive" class="modal is-active">
      <div class="modal-background" @click="isAddEmployeesActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">
            <div class="field">
              <h3>Add an employee</h3>

              <p class="control has-icons-left has-icons-right">
                <input class="input" placeholder="First Name" type="text" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Last Name" type="text" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Email" type="email" required>
              </p>
            </div>


            <div class="field">
              <p class="control">
                <button class="button is-success">
                  Save
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <button aria-label="close" class="modal-close is-large" @click="isAddEmployeesActive=false"></button>
    </div>
    <div v-if="isEditModalActive" class="modal is-active">
      <div class="modal-background" @click="isEditModalActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">
            <h3>Edit an employee information</h3>

            <div class="field">
              <p class="control has-icons-left has-icons-right">
                <input class="input" v-model="edit_data.first_name" placeholder="First Name" type="text" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" v-model="edit_data.last_name" placeholder="Last Name" type="text" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" v-model="edit_data.email" placeholder="Email" type="email" required>
              </p>
            </div>


            <div class="field">
              <p class="control">
                <button class="button is-success">
                  Update
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <button aria-label="close" class="modal-close is-large" @click="isEditModalActive=false"></button>
    </div>
    <div v-if="isLogoutModalActive" class="modal is-active">
      <div class="modal-background" @click="isLogoutModalActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">

            <p>Are you sure you want to logout?</p>


            <div class="field">
              <p class="control">
                <button class="button is-danger m-3">
                  Logout
                </button>
                <button class="button is-primary m-3">
                  Cancel
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <button aria-label="close" class="modal-close is-large" @click="isLogoutModalActive=false"></button>
    </div>
    <div v-if="isDeleteModalActive" class="modal is-active">
      <div class="modal-background" @click="isDeleteModalActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">

            <p>Are you sure you want to delete this employee information?</p>


            <div class="field">
              <p class="control">
                <button class="button is-danger m-3">
                  Delete
                </button>
                <button class="button is-primary m-3" @click="isDeleteModalActive=false">
                  Cancel
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <button aria-label="close" class="modal-close is-large" @click="isDeleteModalActive=false"></button>
    </div>

  </div>
</template>

<script>
import Navbar from "@/app/shared/components/Navbar";
import HRSidebar from "@/app/hr/shared/components/HRSidebar";
export default {
  name: "HrDept",
  components: {HRSidebar, Navbar},
  data() {
    return {
      isAddEmployeesActive: false,
      isLogoutModalActive:false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      edit_data:{
        first_name:"Gopal",
        last_name:"Bharvad",
        email:"gopal.b@gmail.com",
      }
    }
  }
}
</script>

<style scoped>

</style>