<script lang="ts" setup>
const route = useRoute();
const router = useRouter();
</script>

<template>
  <el-header>
    <el-menu
        :default-active="route.name"
        mode="horizontal">
      <el-menu-item index="index" @click="router.push('/')">Home</el-menu-item>
      <el-menu-item index="type" @click="router.push('/type')">Type</el-menu-item>
      <el-menu-item index="tag" @click="router.push('/tag')">Tag</el-menu-item>
      <el-menu-item index="about" @click="router.push('/About')">About</el-menu-item>
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
