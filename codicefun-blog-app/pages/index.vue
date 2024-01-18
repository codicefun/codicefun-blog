<script lang="ts" setup>
const data = ref({})

const getUserList = async () => {
  data.value = await useFetch('/user', { baseURL: 'http://localhost:8080/api' })
  console.log(data.value)
}
</script>

<template>
  <h1>Welcome to the homepage</h1>
  <button @click="getUserList">Click</button>
  <p>{{ data }}</p>
</template>

<style scoped>

</style>
