<template>
  <aside class="menu">
    <p class="menu-label">
      General
    </p>
    <ul class="menu-list">
      <li><router-link to="/hr/attendance">Attendence</router-link></li>
      <li><router-link to="/hr/shift">Shift List</router-link></li>
      <li><router-link to="/hr/profile">Profile</router-link></li>
    </ul>
  </aside>
</template>

<script>
export default {
  name:"HRSidebar"
}
</script>