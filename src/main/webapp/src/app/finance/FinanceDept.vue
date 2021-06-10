<template>
  <div class="container">
    <navbar navbar-title="Finance Department"></navbar>
    <div class="columns">
      <div class="column is-one-fifth">
        <FinanceDepartmentSidebar></FinanceDepartmentSidebar>
      </div>
      <div class="column is-four-fifths">
        <router-view></router-view>
      </div>


  </div>
  </div>
</template>

<script>
import Navbar from "@/app/shared/components/Navbar";
import FinanceDepartmentSidebar from "@/app/finance/shared/components/FInanceDepartmentSidebar";
export default {
  name: "FinanceDept",
  components: {FinanceDepartmentSidebar, Navbar},
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
    },}
}
</script>

<style scoped>

</style>