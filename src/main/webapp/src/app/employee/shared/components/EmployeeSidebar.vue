<template>
  <aside class="menu">
    <p class="menu-label">
      General
    </p>
    <ul class="menu-list">
      <li><router-link to="/employee/attendance-list">View My Attendence</router-link></li>
      <li><router-link to="/employee/salary-slip">View My Salary Slips</router-link></li>
      <li><router-link to="/employee/shifts">View My Shifts</router-link></li>
      <li><router-link to="/employee/leave">Request A Leave</router-link></li>
      <li><router-link to="/employee/profile">My Profile</router-link></li>
    </ul>
  </aside>
</template>

<script>
export default {
  name:"EmployeeSidebar"
}
</script>