<template>
<div class="rows">
  <div class="row is-full tile is-parent">
    <article class="tile is-child notification is-danger">
      <p class="title">Attendance Center</p>
      <p class="subtitle">All the employee attendance information is displayed here</p>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <button class="button is-primary" @click="isAddAttendenceActive=true">
              <strong>Add Attendance For Employees</strong>
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
          <th>Date</th>
          <th>Time Arrived</th>
          <th>Time Left</th>
          <th>Total Hours</th>
          <th>Employee email</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(attendanceInfo,index) in attendanceList" v-bind:key="index">
          <th>{{attendanceInfo.id}}</th>
          <td>{{attendanceInfo.date}}</td>
          <td>{{attendanceInfo.timeArrived}}</td>
          <td>{{attendanceInfo.timeLeft}}</td>
          <td>{{attendanceInfo.totalHours}}</td>
          <td>{{attendanceInfo.employee_id}}</td>
          <td><button class="button is-danger" @click="deletePrepare(attendanceInfo.id)">Delete</button>
            <button class="button is-info is-light mr-2" @click="editAttendanceInfoSelect(index)">Edit</button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>

  <div v-if="isAddAttendenceActive" class="modal is-active">
    <div class="modal-background" @click="isAddAttendenceActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <form @submit="addAttendanceData">
            <div class="field">
              <h3>Add a Attendance Information</h3>

              <p class="control has-icons-left has-icons-right">
                <input class="input" placeholder="mm/dd/yyyy" v-model="addAttendanceInfo.date" type="text" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Time Arrived" v-model="addAttendanceInfo.timeArrived" type="number" required>
              </p>
              <div class="select">
              <select v-model="addAttendanceInfo.timeArrivedSuffix">
                <option value="" disabled selected>Select An Option</option>
                <option>AM</option>
                <option>PM</option>
              </select>
              </div>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Time Left" v-model="addAttendanceInfo.timeLeft" type="number" required>
              </p>
              <div class="select">
              <select v-model="addAttendanceInfo.timeLeftSuffix">
                <option value="" disabled selected>Select An Option</option>
                <option>AM</option>
                <option>PM</option>
              </select>
              </div>

            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input"  placeholder="Total Hours" type="text" v-model="addAttendanceInfo.totalHours" required>
              </p>
            </div>

            <div class="field">
              <div class="select">
                <select v-model="addAttendanceInfo.employee_id">
                  <option value="" disabled selected>Select An Option</option>
                  <option v-for="employee in employee_list" v-bind:key="employee.id" :value="employee.id">
                    {{employee.firstName}} {{employee.lastName}}({{employee.email}}
                  </option>
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
    <button aria-label="close" class="modal-close is-large" @click="isAddSalarySlipActive=false"></button>
  </div>

  <div v-if="isEditModalActive" class="modal is-active">
    <div class="modal-background" @click="isEditModalActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <div class="field">
            <h3>Add a Attendance Information</h3>

            <p class="control has-icons-left has-icons-right">
              <input class="input" placeholder="mm/dd/yyyy" v-model="editAttendanceInfo.date" type="text" required>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Time Arrived" v-model="editAttendanceInfo.timeArrived" type="number" required>
            </p>
            <div class="select">
              <select v-model="editAttendanceInfo.timeArrivedSuffix">
                <option value="" disabled selected>Select An Option</option>
                <option>AM</option>
                <option>PM</option>
              </select>
            </div>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Time Left" v-model="editAttendanceInfo.timeLeft" type="number" required>
            </p>
            <div class="select">
              <select v-model="editAttendanceInfo.timeLeftSuffix">
                <option value="" disabled selected>Select An Option</option>
                <option>AM</option>
                <option>PM</option>
              </select>
            </div>

          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input"  placeholder="Total Hours" type="text" v-model="editAttendanceInfo.totalHours" required>
            </p>
          </div>

          <div class="field">
            <div class="select">
              <select v-model="editAttendanceInfo.employee_id">
                <option value="" disabled selected>Select An Option</option>
                <option v-for="employee in employee_list" v-bind:key="employee.id" :value="employee.id">
                  {{employee.firstName}} {{employee.lastName}}({{employee.email}}
                </option>
              </select>
            </div>
          </div>

          <div class="field">
            <p class="control">
              <button type="submit" class="button is-success"  @click="editAttendanceData">
                Save
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

</div>
</template>

<script>
import {getAllEmployees} from "@/app/admin/shared/services/employeeServices/employeeCrudService";
import {
  addAttendance,
  attendanceEdit,
  deleteAttendance,
  getAllAttendances
} from "@/app/hr/shared/services/hrDeptServices";
export default{
  name:"AttendanceList",
  mounted() {
    this.employeeListPopulate();
    this.attendanceListPopulate();
  },
  data(){
    return{
      attendanceList:[],
      isAddAttendenceActive:false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      employee_list:[],
      delete_id:{},
      addAttendanceInfo:{
        date:null,
        timeArrived:null,
        timeArrivedSuffix:'AM',
        timeLeft:null,
        timeLeftSuffix:'PM',
        totalHours:null,
        absent:false,
        employee_id:null
      },
      editAttendanceInfo:{
        date:null,
        timeArrived:null,
        timeArrivedSuffix:'AM',
        timeLeft:null,
        timeLeftSuffix:'PM',
        totalHours:null,
        absent:false,
        employee_id:null
      }
    }
  },
  methods:{
    addAttendanceData(event){
      event.preventDefault();
        console.log("Adding attendance data");
        const resp=addAttendance(this.addAttendanceInfo);
        resp.then(res=>{
          console.table(res);
          console.log(resp);
        });

    },
    employeeListPopulate(){
      const resp=getAllEmployees();
      resp.then(res=>{
        console.table(res.data.data[0]);
        console.log(res.data.data[0][0].id)
        this.employee_list=res.data.data[0];
      });
    },
    attendanceListPopulate(){
      const resp=getAllAttendances();
      resp.then(res=>{
        console.table(res.data.data[0]);
        console.log(res.data.data[0][0].id)
        this.attendanceList=res.data.data[0];
      });
    },
    deletePrepare(id){
      this.deleteId=id;
      this.isDeleteModalActive=true;
    },
    deleteItem(){
      deleteAttendance(this.deleteId);
      this.isDeleteModalActive=false;
      location.reload();
    },
    editAttendanceInfoSelect(index){
      console.log(this.attendanceList[index]);
      this.editAttendanceInfo=this.attendanceList[index];
      this.isEditModalActive=true;
    },
    editAttendanceData(){
      console.log("Editing Attendence Info");
      attendanceEdit(this.editAttendanceInfo).then(res=>console.log(res));
    }

  }
}

</script>