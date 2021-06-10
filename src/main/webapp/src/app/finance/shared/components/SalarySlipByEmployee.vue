<template>
  <div class="rows">

    <div class="row is-full tile is-parent">
      <article class="tile is-child notification is-danger">
        <p class="title">Salary Slip By Employee</p>
        <p class="subtitle">All salary slip by employee</p>
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="buttons">
              <button class="button is-primary" @click="isAddEmployeesActive=true">
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
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <th>1</th>
            <td>January</td>
            <td>10,000</td>
            <td>7,000</td>
            <td>3,000</td>
            <td><button>Print</button></td>
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
          <div class="field">
            <h3>Add a salary slip</h3>

            <p class="control has-icons-left has-icons-right">
              <input class="input" placeholder="First Name" type="text" required>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Last Name" type="text" required>
            </p>
          </div>

          <div class="field">
            <p class="control has-icons-left">
              <input class="input" placeholder="Email" type="email" required>
            </p>
          </div>


          <div class="field">
            <p class="control">
              <button class="button is-success">
                Save
              </button>
            </p>
          </div>
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
  <div v-if="isDeleteModalActive" class="modal is-active">
    <div class="modal-background" @click="isDeleteModalActive=false"></div>
    <div class="modal-content">
      <div class="tile is-parent">
        <div class="tile is-child box">

          <p>Are you sure you want to delete this employee information?</p>


          <div class="field">
            <p class="control">
              <button class="button is-danger m-3">
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
export default {
  name: "SalarySlipByEmployee",
  data() {
    return {
      isAddEmployeesActive: false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      edit_data:{
        first_name:"Gopal",
        last_name:"Bharvad",
        email:"gopal.b@gmail.com",
      }
    }
  },
}
</script>

<style scoped>

</style>