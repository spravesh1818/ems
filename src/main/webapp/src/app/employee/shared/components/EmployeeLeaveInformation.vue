<template>
  <div class="rows">
    <div class="row is-full tile is-parent">
      <article class="tile is-child notification is-danger">
        <p class="title">View All Your Leaves</p>
        <p class="subtitle">Apply or revoke a leave</p>
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="buttons">
              <button class="button is-primary" @click="isAddModalActive=true">
                <strong>Request Leave</strong>
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
            <th>Start Date</th>
            <th>End Date</th>
            <th>Type</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(leave,index) in leaveRequests" v-bind:key="index">
            <th>1</th>
            <td>{{leave.startDate}}</td>
            <td>{{leave.endDate}}</td>
            <td>{{leave.type}}</td>
            <td>{{leave.reason}}</td>
            <td>{{leave.status}}</td>
            <td><button v-if="leave.status==='PENDING'" class="button is-danger" @click="deletePrepare(leave.id)">Delete</button>

            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div v-if="isAddModalActive" class="modal is-active">
    <div class="modal-background" @click="isAddModalActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <div class="field">
            <h3>Request Leave</h3>

            <p class="control has-icons-left has-icons-right">
              <input class="input" v-model="addLeaveForm.startDate" placeholder="Start Date(mm/dd/yyyy)" type="text" required>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" v-model="addLeaveForm.endDate" placeholder="End Date(mm/dd/yyyy)" type="text" required>
            </p>
          </div>

          <div class="field">
            <div class="select">
              <select v-model="addLeaveForm.type">
                <option value="" disabled selected>Select Leave Type</option>
                <option value="SICK_LEAVE">Sick Leave</option>
                <option value="ANNUAL_LEAVE">Annual Leave</option>
                <option value="CASUAL_LEAVE">Casual Leave</option>
              </select>
            </div>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" v-model="addLeaveForm.reason" placeholder="Reason" type="text" required>
            </p>
          </div>


          <div class="field">
            <p class="control">
              <button class="button is-success" @click="addLeaveRequest">
                Save
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>
    <button aria-label="close" class="modal-close is-large" @click="isAddModalActive=false"></button>
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

          <p>Are you sure you want to delete this leave request?</p>


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
  addLeaveRequest,
  deleteEmployeeLeave,
  getAllLeavesOfAnEmployee
} from "@/app/employee/shared/services/employeeManipulationService";

export default {
  name:"EmployeeAttendenceList",
  mounted() {
    this.getAllSalarySlips();
  },
  data() {
    return {
      deleteId:{},
      leaveRequests:[],
      isAddModalActive: false,
      isLogoutModalActive:false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      addLeaveForm:{
        startDate:null,
        endDate:null,
        reason:null,
        type:null,
        employee_id:null
      },
    }
  },
  methods:{
    addLeaveRequest(){
      const resp=addLeaveRequest(this.addLeaveForm);
      resp.then(data=>console.log(data));
    },
    getAllSalarySlips(){
      const resp=getAllLeavesOfAnEmployee();
      resp.then(data=>this.leaveRequests=data.data.data[0]);
    },
    deletePrepare(id){
      this.deleteId=id;
      this.isDeleteModalActive=true;
    },
    deleteItem(){
      const resp=deleteEmployeeLeave(this.deleteId);
      resp.then(res=>console.log(res));
      this.isDeleteModalActive=false;
      location.reload();
    },
  }
}
</script>