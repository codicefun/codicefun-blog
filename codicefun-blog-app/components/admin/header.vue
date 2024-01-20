<script lang="ts" setup>

</script>

<template>
  <el-header>
    <el-menu
        :default-active="'1'"
        mode="horizontal">
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">用户</el-menu-item>
      <el-menu-item index="3">博客</el-menu-item>
      <el-menu-item index="4">分类</el-menu-item>
      <el-menu-item index="5">标签</el-menu-item>
      <el-menu-item index="6">评论</el-menu-item>
    </el-menu>
  </el-header>
</template>

<style scoped>
.el-header {
  display: flex;
  justify-content: center;
  padding: 0;
}

.el-menu {
  width: 1400px;
}
</style>
