<template>
  <div class="container">
  <nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a class="navbar-item">
        EMS
      </a>

      <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
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
              <strong>Hello Admin</strong>
            </a>
            <a class="button is-light">
              Log out
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>
    <div class="columns">
      <div class="column is-one-fifths"><aside class="menu">
        <p class="menu-label">
          General
        </p>
        <ul class="menu-list">
          <li><a>Add Employees</a></li>
        </ul>
      </aside></div>
      <div class="column is-four-fifths">Auto</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Admin"
}
</script>

<style scoped>

</style>