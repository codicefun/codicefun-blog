<script lang="ts" setup>
definePageMeta({
  layout: 'admin',
  validate: async () => {
    const userStore = useUserStore();
    const router = useRouter();

    if (userStore.token === '') {
      ElMessage({
        showClose: true,
        message: '请先登录',
        type: 'warning'
      })
      await router.push('/admin/login')
      return false
    }
    return true
  }
})
</script>

<template>
  <h1>Admin</h1>
</template>

<style scoped>

</style>
