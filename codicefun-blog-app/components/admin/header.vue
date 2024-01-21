<script lang="ts" setup>
const active = ref('1')
</script>

<template>
  <el-header>
    <el-menu
        :default-active="active"
        mode="horizontal"
    >
      <el-menu-item index="1" @click="active = '1'">
        <nuxt-link to="/admin">首页</nuxt-link>
      </el-menu-item>
      <el-menu-item index="2" @click="active = '2'">
        <nuxt-link to="/admin/user">用户</nuxt-link>
      </el-menu-item>
      <el-menu-item index="3" @click="active = '3'">
        <nuxt-link to="/admin/article">博客</nuxt-link>
      </el-menu-item>
      <el-menu-item index="4" @click="active = '4'">
        <nuxt-link to="/admin/type">分类</nuxt-link>
      </el-menu-item>
      <el-menu-item index="5" @click="active = '5'">
        <nuxt-link to="/admin/tag">标签</nuxt-link>
      </el-menu-item>
      <el-menu-item index="6" @click="active = '6'">
        <nuxt-link to="/admin/comment">评论</nuxt-link>
      </el-menu-item>
      <el-menu-item index="7" @click="active = '7'">
        <nuxt-link to="/admin/login">登录</nuxt-link>
      </el-menu-item>
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
