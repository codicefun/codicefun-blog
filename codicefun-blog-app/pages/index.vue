<script lang="ts" setup>
const data = ref({})

const getUserList = async () => {
  data.value = useRequest.get('/user')
}
</script>

<template>
  <h1>Welcome to the homepage</h1>
  <button @click="getUserList">Click</button>
  <p>{{ data }}</p>
</template>

<style scoped>

</style>
