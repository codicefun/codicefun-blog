<script lang="ts" setup>
definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const userStore = useUserStore();
</script>

<template>
  <h1>Welcome! {{ userStore.username }}.</h1>
</template>

<style scoped>

</style>
