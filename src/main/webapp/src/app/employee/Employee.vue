<template>
  <div class="container">
    <Navbar navbar-title="Employee Dashboard"></Navbar>
    <div class="columns">
      <div class="column is-one-fifth">
        <EmployeeSidebar></EmployeeSidebar>
      </div>
      <div class="column is-four-fifths">
        <router-view></router-view>
      </div>
  </div>
  </div>
</template>

<script>
import EmployeeSidebar from "@/app/employee/shared/components/EmployeeSidebar";
import Navbar from "@/app/shared/components/Navbar";
export default {
  name: "Employee",
  components: {Navbar, EmployeeSidebar},
}
</script>

<style scoped>

</style>