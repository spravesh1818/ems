<template>
  <aside class="menu">
    <p class="menu-label">
      General
    </p>
    <ul class="menu-list">
      <li><router-link to="/finance/salary-slip">Salary Slips</router-link></li>
      <li><router-link to="/finance/salary-slip-by-employee">Salary Slip By Employees</router-link></li>
    </ul>
  </aside>
</template>

<script>
export default {
  name:"FinanceDepartmentSidebar"
}
</script>