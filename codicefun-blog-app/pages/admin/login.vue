<script lang="ts" setup>
import apis from '~/apis';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

interface UserForm {
  username: string
  password: string
}

const router = useRouter();
const formData = ref<UserForm>({} as UserForm)
const userStore = useUserStore();

const login = async () => {
  const { data } = await apis.auth.login(formData.value.username, formData.value.password);
  ElMessage({ showClose: true, message: 'Login success', type: 'success' })
  userStore.username = formData.value.username
  userStore.token = data.token
  await router.push('/admin')
}
</script>

<template>
  <div class="login-container">
    <h2>Admin Login</h2>
    <div>
      <el-form
          :model="formData"
          label-position="left"
          label-width="60"
      >
        <el-form-item label="username">
          <el-input v-model="formData.username"/>
        </el-form-item>
        <el-form-item label="password">
          <el-input v-model="formData.password" type="password"/>
        </el-form-item>
      </el-form>
      <div class="login-button">
        <el-button type="warning" @click="formData = {} as UserForm">reset</el-button>
        <el-button type="success" @click="login">login</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-top: 100px;
}

.el-form {
  width: 300px;
}

.login-button {
  display: flex;
  justify-content: right;
}
</style>
