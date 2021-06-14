<template>
  <div class="rows">
    <div class="row is-full tile is-parent">
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

    <div class="row is-full tile is-parent">
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
                <div class="select">
                <select v-model="employeeAddForm.role" >
                  <option value="" disabled selected>Select An Option</option>
                  <option value="employee">Employee</option>
                  <option value="hr">HR</option>
                  <option value="finance">Finance</option>
                </select>
                </div>
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
</template>

<script>
import {
  addAnEmployee, deleteEmployee,
  editEmployee,
  getAllEmployees
} from "@/app/admin/shared/services/employeeServices/employeeCrudService";

export default {
  name:"UserListComponent",
  mounted() {
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
        role:""
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
      resp.then(res=>{
        console.table(res.data.data[0]);
        this.listData=res.data.data[0];
      });
    },
    logout(event){
      event.preventDefault();
      localStorage.removeItem('token');
      localStorage.removeItem('role');
      this.$router.push("/");
    },
    addEmployee(event){
      event.preventDefault();
      console.log(this.employeeAddForm);
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