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
              <p class="control has-text-danger">
                {{loginError}}
              </p>
            </div>
          <div class="field">
            <p class="control has-icons-left has-icons-right">
              <input class="input" v-model="form.username" type="text" @change='clearErrors' placeholder="Username" required>
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
import AsyncLocalStorage from '@createnextapp/async-local-storage'



export default {
  name: "LandingPage",
  mounted() {
    this.attemptAutologin();
  },
  data(){
    return {
      isLoginButtonDisabled:false,
      loginError:'',
      form:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    clearErrors(event){
      console.log(event);
      console.log("Event occured")
      this.loginError="";
    },
    attemptAutologin(){
      const token=localStorage.getItem("token");
      const role=localStorage.getItem("role");
      console.log(localStorage.getItem("token"));
      console.log(localStorage.getItem("role"));
      if(token && role){
        if(role==="ROLE_ADMIN"){
          this.$router.push("/admin");
        }else if(role==="ROLE_EMPLOYEE"){
          this.$router.push("/employee");
        }else if(role==="ROLE_FINANCE"){
          this.$router.push("/finance");
        }else{
          this.$router.push("/hr");
        }
      }
    },
    async submitForm(event){
      event.preventDefault();
      this.isLoginButtonDisabled=true;
      fetchUsers(this.form.username,this.form.password).then(res=>{
        console.log(res);
        this.isLoginButtonDisabled=false;

          //got the token
          //now save it in the localstorage
          //and also the role
        localStorage.setItem( 'token', res.token);
        localStorage.setItem('role',res.role);
          this.$store.commit('updateToken',res.token);
          if(res.role==="ROLE_ADMIN"){
            this.$router.push("/admin");
          }else if(res.role==="ROLE_EMPLOYEE"){
            this.$router.push("/employee");
          }else if(res.role==="ROLE_FINANCE"){
            this.$router.push("/finance");
          }else{
            this.$router.push("/hr");
          }



      }).catch(err=>{
        console.log("Login error");

        console.log(err);
        this.isLoginButtonDisabled=false;
        this.loginError="Username or password incorrect.Please try Again";
      });

      await AsyncLocalStorage.setItem('@token', localStorage.getItem('token'));
    }
  },
  watch:{
    'form.username':function(){
      console.log(this.$store.state);
      this.loginError="";
    },
    'form.password':function (){
      this.loginError="";
    }
  }
}

</script>

<style scoped>

</style>