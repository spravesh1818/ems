<template>
  <section class="section">
    <div class="container">
      <h1 class="title">
        Welcome to Employee Management System
      </h1>
      <p class="subtitle">
        Please <strong>Login</strong> to continue
      </p>
    </div>
  </section>
  <section>
    <div class="container">
    <div class="card column is-half is-offset-one-quarter">
      <header class="card-header">
        <p class="card-header-title">
          Login
        </p>

      </header>
      <div class="card-content">
        <div class="content">
          <form id="login" @submit="submitForm">
          <div class="field">
            <p class="control has-icons-left has-icons-right">
              <input class="input" v-model="form.username" type="text" placeholder="Username" required>
              <span class="icon is-small is-left">
      <i class="fas fa-envelope"></i>
    </span>
              <span class="icon is-small is-right">
      <i class="fas fa-check"></i>
    </span>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" type="password" v-model="form.password" placeholder="Password" required>
              <span class="icon is-small is-left">
      <i class="fas fa-lock"></i>
    </span>
            </p>
          </div>
          <div class="field">
            <p class="control">
              <button class="button is-success" :disabled='isLoginButtonDisabled'>
                Login
              </button>
            </p>
          </div>
          </form>
        </div>
      </div>

    </div>
    </div>

  </section>

</template>

<script>
import {fetchUsers} from "@/app/shared/services/authentication/authenticationService";



export default {
  name: "LandingPage",
  data(){
    return {
      isLoginButtonDisabled:false,
      form:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    submitForm(event){
      event.preventDefault();
      this.isLoginButtonDisabled=true;
      fetchUsers(this.form.username,this.form.password).then(res=>{
          this.isLoginButtonDisabled=false;
          //got the token
          //now save it in the localstorage
          //and also the role
        localStorage.setItem( 'token', res.token);
        console.log(res);
        localStorage.setItem('role',res.userDetails.authority);

        if(res.userDetails.authority==="ROLE_ADMIN"){
          this.$router.push("/admin");
        }else{
          this.$router.push("/employee");
        }

      }).catch(err=>{
        console.log(err);
        this.isLoginButtonDisabled=false;
      })
    }
  }
}

</script>

<style scoped>

</style>