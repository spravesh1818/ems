<template>
  <div class="container">
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
                <strong>Admin Dashboard</strong>
              </a>
              <a class="button is-light" @click="isLogoutModalActive=true">
                Log out
              </a>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <div class="columns">
      <div class="column is-one-fifths">
        <aside class="menu">
          <p class="menu-label">
            General
          </p>
          <ul class="menu-list">
            <li><a>Add Employees</a></li>
            <li><a>Attendence Sheet</a></li>
            <li><a>Payroll Information</a></li>
          </ul>
        </aside>
      </div>
      <div class="column is-four-fifths">
        <div class="tile is-parent">
          <article class="tile is-child notification is-danger">
            <p class="title">Employees</p>
            <p class="subtitle">All Employees Operations are done here.</p>
            <div class="navbar-end">
              <div class="navbar-item">
                <div class="buttons">
                  <button class="button is-primary" @click="isAddEmployeesActive=true">
                    <strong>Add Employees</strong>
                  </button>
                </div>
              </div>
            </div>
            <div class="content">

            </div>
          </article>
        </div>
      </div>

    </div>
    <div class="columns">
      <div class="column is-one-fifths"></div>
      <div class="column is-four-fifths">
        <div class="tile is-parent">
          <div class="tile is-child box">
            <table class="table is-fullwidth">
              <thead>
              <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in listData" v-bind:key="index">
                <th>{{index+1}}</th>
                <td>{{item.firstName}}</td>
                <td>{{item.lastName}}</td>
                <td>{{item.email}}</td>
                <td>
                  <button class="button is-info is-light mr-2" @click="editEmployee(index)">Edit</button>
                  <button class="button is-danger is-light" @click="deletePrepare(item.id)">Delete</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

    </div>

    <div v-if="isAddEmployeesActive" class="modal is-active">
      <div class="modal-background" @click="isAddEmployeesActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">
            <form @submit="addEmployee">
            <div class="field">
              <h3>Add an employee</h3>

              <p class="control has-icons-left has-icons-right">
                <input class="input" placeholder="First Name" type="text" v-model="employeeAddForm.firstName" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Last Name" type="text" v-model="employeeAddForm.lastName" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Email" type="email" v-model="employeeAddForm.email" required>
              </p>
            </div>


            <div class="field">
              <p class="control">
                <button type="submit" class="button is-success">
                  Save
                </button>
              </p>
            </div>
            </form>
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
                <input class="input" v-model="employeeEditForm.firstName" placeholder="First Name" type="text" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" v-model="employeeEditForm.lastName" placeholder="Last Name" type="text" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" v-model="employeeEditForm.email" placeholder="Email" type="email" required>
              </p>
            </div>


            <div class="field">
              <p class="control">
                <button class="button is-success" @click="editEmployeeData">
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
    <div v-if="isDeleteModalActive" class="modal is-active">
      <div class="modal-background" @click="isDeleteModalActive=false"></div>
      <div class="modal-content">
        <div class="tile is-parent">
          <div class="tile is-child box">

            <p>Are you sure you want to delete this employee information?</p>


            <div class="field">
              <p class="control">
                <button class="button is-danger m-3" @click="deleteItem">
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

import {addAnEmployee, getAllEmployees,deleteEmployee,editEmployee} from "@/app/admin/shared/services/employeeServices/employeeCrudService";

export default {
  name: "Admin",
  created() {
    this.employeeListPopulate();
  },
  data() {
    return {
      deleteId:{},
      listData:[],
      employeeAddForm:{
        firstName:"",
        lastName:"",
        email:"",
      },
      employeeEditForm:{
        id:0,
        firstName:"",
        lastName:"",
        email:"",
      },
      isAddEmployeesActive: false,
      isLogoutModalActive:false,
      isDeleteModalActive:false,
      isEditModalActive:false,
    }
  },
  methods:{
    employeeListPopulate(){
      const resp=getAllEmployees();
      resp.then(res=>console.log(this.listData=res.data.message));
    },
    logout(event){
      event.preventDefault();
      localStorage.removeItem('token');
      localStorage.removeItem('role');
      this.$router.push("/");
    },
    addEmployee(event){
      event.preventDefault();
      addAnEmployee(this.employeeAddForm).then(res=>{
        console.log(res);
        this.isAddEmployeesActive=false;
      });
      location.reload();
    },
    deletePrepare(id){
      this.deleteId=id;
      this.isDeleteModalActive=true;
    },
    editEmployee(index){
      this.employeeEditForm=this.listData[index];
      this.isEditModalActive=true;
    },
    editEmployeeData(){
      editEmployee(this.employeeEditForm).then(res=>{console.log(res);this.isEditModalActive=false;});
    },
    deleteItem(){
      deleteEmployee(this.deleteId);
      this.isDeleteModalActive=false;
      location.reload();
    }
  }
}
</script>

<style scoped>

</style>