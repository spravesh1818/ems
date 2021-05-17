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
              <tr>
                <th>1</th>
                <td>Gopal</td>
                <td>Bharvad</td>
                <td>gopal.b@gmail.com</td>
                <td>
                  <button class="button is-info is-light mr-2" @click="isEditModalActive=true">Edit</button>
                  <button class="button is-danger is-light" @click="isDeleteModalActive=true">Delete</button>
                </td>
              </tr>

              <tr>
                <th>2</th>
                <td>Mitun</td>
                <td>Patel</td>
                <td>patel_mithun@gmail.com</td>
                <td>
                  <button class="button is-info is-light mr-2" @click="isEditModalActive=true">Edit</button>
                  <button class="button is-danger is-light" @click="isDeleteModalActive=true">Delete</button>
                </td>
              </tr>



              <tr>
                <th>3</th>
                <td>Mariyamma</td>
                <td>Sadaf</td>
                <td>mariyamma.s@gmail.com</td>
                <td>
                  <button class="button is-info is-light mr-2" @click="isEditModalActive=true">Edit</button>
                  <button class="button is-danger is-light" @click="isDeleteModalActive=true">Delete</button>
                </td>
              </tr>


              <tr>
                <th>4</th>
                <td>Sravan Goud</td>
                <td>Bandi</td>
                <td>sgb@gmail.com</td>
                <td>
                  <button class="button is-info is-light mr-2" @click="isEditModalActive=true">Edit</button>
                  <button class="button is-danger is-light" @click="isDeleteModalActive=true">Delete</button>
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
            <div class="field">
              <h3>Add an employee</h3>

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

  </div>
</template>

<script>
export default {
  name: "Admin",
  data() {
    return {
      isAddEmployeesActive: false,
      isLogoutModalActive:false,
      isDeleteModalActive:false,
      isEditModalActive:false,
      edit_data:{
        first_name:"Gopal",
        last_name:"Bharvad",
        email:"gopal.b@gmail.com",
      }
    }
  }
}
</script>

<style scoped>

</style>