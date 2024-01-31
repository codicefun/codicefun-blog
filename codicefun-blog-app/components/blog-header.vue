<script lang="ts" setup>

</script>

<template>
  <el-header>
    <el-menu
        :default-active="'1'"
        mode="horizontal">
      <el-menu-item index="1">
        <nuxt-link to="/">Home</nuxt-link>
      </el-menu-item>
      <el-menu-item index="2">Type</el-menu-item>
      <el-menu-item index="3">Tag</el-menu-item>
      <el-menu-item index="4">About</el-menu-item>
    </el-menu>
  </el-header>
</template>

<style scoped>
.el-header {
  display: flex;
  justify-content: center;
  padding: 0;
  //background-color: skyblue;
}

.el-menu {
  width: 1400px;
}
</style>
