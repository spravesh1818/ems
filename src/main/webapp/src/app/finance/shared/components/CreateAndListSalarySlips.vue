<template>
  <div class="rows">

      <div class="row is-full tile is-parent">
        <article class="tile is-child notification is-danger">
          <p class="title">Finance Department</p>
          <p class="subtitle">All Finance Related Information Displayed Here</p>
          <div class="navbar-end">
            <div class="navbar-item">
              <div class="buttons">
                <button class="button is-primary" @click="isAddSalarySlipActive=true">
                  <strong>Create A Salary Slip</strong>
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
            <th>Month</th>
            <th>Gross Salary</th>
            <th>Net Salary</th>
            <th>Deductions</th>
            <th>Employee Name</th>
            <th>Employee Email</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(salary_slip,index) in salary_slips" v-bind:key="index">
            <th>1</th>
            <td>{{salary_slip.date}}</td>
            <td>{{salary_slip.grossAmount}}</td>
            <td>{{salary_slip.netAmount}}</td>
            <td>{{salary_slip.deductions}}</td>
            <td>{{salary_slip.employee_email}}</td>
            <td>{{salary_slip.employee_name}}</td>
            <td><button class="button is-danger" @click="deletePrepare(salary_slip.id)">Delete</button>
              <button class="button is-info is-light mr-2" @click="editSalarySlip(index)">Edit</button></td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

  </div>



  <div v-if="isAddSalarySlipActive" class="modal is-active">
    <div class="modal-background" @click="isAddSalarySlipActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <form @submit="addSalarySlip">
          <div class="field">
            <h3>Add a salary slip</h3>

            <p class="control has-icons-left has-icons-right">
              <input class="input" placeholder="Gross Amount" v-model="addSalarySlipForm.grossAmount" type="number" required>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Net Amount" v-model="addSalarySlipForm.netAmount" type="number" required>
            </p>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="deductions" v-model="addSalarySlipForm.deductions" type="number" required>
            </p>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="mm/dd/yyyy" type="text" v-model="addSalarySlipForm.date" required>
            </p>
          </div>

          <div class="field">
              <div class="select">
              <select v-model="addSalarySlipForm.employee_id">
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
              <h3>Edit a salary slip</h3>

              <p class="control has-icons-left has-icons-right">
                <input class="input" placeholder="Gross Amount" v-model="salarySlipEditForm.grossAmount" type="number" required>
              </p>
            </div>
            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="Net Amount" v-model="salarySlipEditForm.netAmount" type="number" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="deductions" v-model="salarySlipEditForm.deductions" type="number" required>
              </p>
            </div>

            <div class="field">
              <p class="control has-icons-left">
                <input class="input" placeholder="mm/dd/yyyy" type="text" v-model="salarySlipEditForm.date" required>
              </p>
            </div>

            <div class="field">
              <div class="select">
                <select v-model="salarySlipEditForm.employee_id">
                  <option value="" disabled selected>Select An Option</option>
                  <option v-for="employee in employee_list" v-bind:key="employee.id" :value="employee.id">
                    {{employee.firstName}} {{employee.lastName}}({{employee.email}}
                  </option>
                </select>
              </div>
            </div>

            <div class="field">
              <p class="control">
                <button type="submit" class="button is-success" @click="editSalaryData">
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
import {getAllEmployees} from "@/app/admin/shared/services/employeeServices/employeeCrudService";
import {
  addSalarySlip,
  deleteSalarySlip,
  getAllSalarySlips,
  salarySlipEdit
} from "@/app/finance/shared/services/financeDeptServices";

export default {
  name: "CreateAndListSalarySlip",
  mounted() {
    this.employeeListPopulate();
    this.salarySlipPopulate();

  },
  data() {
    return {
      addSalarySlipForm:{
        grossAmount:null,
        netAmount:null,
        deductions:null,
        grandTotal:null,
        date:"",
        employee_id:null
      },
      employee_list:[],
      salary_slips:[],
      isAddSalarySlipActive: false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      deleteId:{},
      salarySlipEditForm:{
        grossAmount:null,
        netAmount:null,
        deductions:null,
        grandTotal:null,
        date:"",
        employee_id:null
      }
    }
  },
  methods:{
    salarySlipPopulate(){
      const resp=getAllSalarySlips();
      resp.then(res=>{
        console.table(res.data.data[0]);
        this.salary_slips=res.data.data[0];
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
    addSalarySlip(event){
      event.preventDefault();
      this.addSalarySlipForm.grandTotal=this.addSalarySlipForm.grossAmount-this.addSalarySlipForm.netAmount-this.addSalarySlipForm.deductions;
      console.log(this.addSalarySlipForm);
      const resp=addSalarySlip(this.addSalarySlipForm);
      resp.then(res=>{
        console.log(res);
      });
    },
    deletePrepare(id){
      this.deleteId=id;
      this.isDeleteModalActive=true;
    },
    deleteItem(){
      deleteSalarySlip(this.deleteId);
      this.isDeleteModalActive=false;
      location.reload();
    },
    editSalarySlip(index){
      this.salarySlipEditForm=this.salary_slips[index];
      this.isEditModalActive=true;
    },
    editSalaryData(){
      salarySlipEdit(this.salarySlipEditForm).then(res=>{console.log(res);this.isEditModalActive=false;});
    },
  }
}
</script>

<style scoped>

</style>