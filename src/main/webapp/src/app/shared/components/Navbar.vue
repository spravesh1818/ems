<template>
  <nav aria-label="main navigation" class="navbar" role="navigation">
    <div class="navbar-brand">
      <a class="navbar-item">
        EMS
      </a>

      <a aria-expanded="false" aria-label="menu" class="navbar-burger" data-target="navbarBasicExample" role="button">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-primary">
              <strong>{{navbarTitle}}</strong>
            </a>
            <a class="button is-light" @click="isLogoutModalActive=true">
              Log out
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>

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
</template>

<script>
export default {
  name:"Navbar",
  props:['navbarTitle'],
  data() {
    return {
      isLogoutModalActive:false,
    }
  },
  methods:{
    logout(event){
      event.preventDefault();
      localStorage.removeItem('token');
      localStorage.removeItem('role');
      this.$router.push("/");
    },
  }
}

</script>