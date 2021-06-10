<template>
  <aside class="menu">
    <p class="menu-label">
      General
    </p>
    <ul class="menu-list">
      <li><router-link to="/admin/employee-list">Add Employees</router-link></li>
      <li><router-link to="/admin/test">Test</router-link></li>
      <li><a>Attendence Sheet</a></li>
      <li><a>Payroll Information</a></li>
    </ul>
  </aside>
</template>

<script>
export default {
  name:"SidebarComponent"
}
</script>