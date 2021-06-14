<template>
  <div class="rows">

    <div class="row is-full tile is-parent">
      <article class="tile is-child notification is-danger">
        <p class="title">Salary Slip By Employee</p>
        <p class="subtitle">Select An Employee To View His/Her Salary Slip</p>
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="buttons">
            </div>
          </div>
        </div>
        <div class="content">

        </div>
      </article>
    </div>

    <div class="row is-full tile is-parent">
      <div class="tile is-child box field">
        <div class="select">
          <select v-model="employeeSelected" @change="employeeDataChanged($event)">
            <option value="" disabled selected>Select An Option</option>
            <option v-for="employee in employee_list" v-bind:key="employee.id" :value="employee.id">
              {{employee.firstName}} {{employee.lastName}}({{employee.email}}
            </option>
          </select>
        </div>
      </div>
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
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(salary_slip,index) in salarySlipInfo" v-bind:key="index">
            <th>1</th>
            <td>{{salary_slip.date}}</td>
            <td>{{salary_slip.grossAmount}}</td>
            <td>{{salary_slip.netAmount}}</td>
            <td>{{salary_slip.deductions}}</td>
            <td><button>Print</button></td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

  </div>

</template>

<script>
import {getAllEmployees} from "@/app/admin/shared/services/employeeServices/employeeCrudService";
import {getAllSalarySlipsOfAnEmployee} from "@/app/finance/shared/services/financeDeptServices";

export default {
  name: "SalarySlipByEmployee",
  mounted() {
    this.employeeListPopulate();
  },
  data() {
    return {
      employeeSelected:'',
      employee_list:[],
      salarySlipInfo:[],
      isAddEmployeesActive: false,
      isDeleteModalActive:false,
      isEditModalActive:false,
    }
  },
  methods:{
    employeeListPopulate(){
      const resp=getAllEmployees();
      resp.then(res=>{
        console.table(res.data.data[0]);
        console.log(res.data.data[0][0].id)
        this.employee_list=res.data.data[0];
      });
    },
    employeeDataChanged(event){
      console.log(event.target.value);
      const resp=getAllSalarySlipsOfAnEmployee(event.target.value);
      resp.then(res=>{
        console.table(res.data.data[0]);
        console.log(res.data.data[0]);
        this.salarySlipInfo=res.data.data[0];
      })

    }
  }
}
</script>

<style scoped>

</style>