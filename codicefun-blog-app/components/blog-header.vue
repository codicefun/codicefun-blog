<script lang="ts" setup>

</script>

<template>
  <el-header>
    <el-menu
        :default-active="'1'"
        mode="horizontal">
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">前端</el-menu-item>
      <el-menu-item index="3">后端</el-menu-item>
      <el-menu-item index="4">闲聊</el-menu-item>
      <el-menu-item index="5">关于本站</el-menu-item>
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
