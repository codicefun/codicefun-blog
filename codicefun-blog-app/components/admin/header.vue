<script lang="ts" setup>
const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const logout = () => {
  userStore.token = ''
  router.push('/')
}
</script>

<template>
  <el-header>
    <el-menu
        :default-active="route.name"
        mode="horizontal"
    >
      <el-menu-item index="admin" @click="router.push('/admin')">Admin</el-menu-item>
      <el-menu-item index="admin-user" @click="router.push('/admin/user')">User</el-menu-item>
      <el-menu-item index="admin-article" @click="router.push('/admin/article')">Article</el-menu-item>
      <el-menu-item index="admin-type" @click="router.push('/admin/type')">Type</el-menu-item>
      <el-menu-item index="admin-tag" @click="router.push('/admin/tag')">Tag</el-menu-item>
      <el-menu-item index="admin-comment" @click="router.push('/admin/comment')">Comment</el-menu-item>
      <el-menu-item index="admin-login" @click="router.push('/admin/login')">Login</el-menu-item>
      <el-menu-item index="admin-logout" @click="logout">Logout</el-menu-item>
      <el-menu-item index="index" @click="router.push('/')">Back to Blog</el-menu-item>
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
