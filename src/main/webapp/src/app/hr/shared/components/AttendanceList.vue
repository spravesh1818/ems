<template>
<div class="rows">
  <div class="row is-full tile is-parent">
    <article class="tile is-child notification is-danger">
      <p class="title">My Attendence</p>
      <p class="subtitle">All the employee attendence information is displayed here</p>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <button class="button is-primary" @click="isAddEmployeesActive=true">
              <strong>Send Correction Request</strong>
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
          <th>Login Time</th>
          <th>Logout Time</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th>1</th>
          <td>2021/04/10</td>
          <td>10:00 AM</td>
          <td>04:10 PM</td>
        </tr>

        <tr>
          <th>2</th>
          <td>2021/04/11</td>
          <td>10:00 AM</td>
          <td>05:00 PM</td>
        </tr>

        <tr>
          <th>3</th>
          <td>2021/04/13</td>
          <td>10:00 AM</td>
          <td>05:10 PM</td>
        </tr>





        </tbody>
      </table>
    </div>
  </div>

</div>
</template>

<script>
export default{
  name:"AttendanceList"
}

</script>