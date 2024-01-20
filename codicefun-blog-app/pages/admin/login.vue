<script lang="ts" setup>
import apis from '~/apis';

definePageMeta({
  layout: 'admin'
})

interface UserForm {
  username: string
  password: string
}

const router = useRouter();
const formData = ref<UserForm>({} as UserForm)
const userStore = useUserStore();

const login = async () => {
  const { data, error } = await apis.auth.login(formData.value.username, formData.value.password);

  if (error.value) {
    ElMessage({ showClose: true, message: error.value.message, type: 'error' })
  } else {
    ElMessage({ showClose: true, message: 'Login success', type: 'success' })
    userStore.token = data.value?.data.token;
    await router.push('/admin')
  }
}
</script>

<template>
  <div class="login-container">
    <h2>管理员登录</h2>
    <div>
      <el-form
          :model="formData"
          label-position="left"
          label-width="60"
      >
        <el-form-item label="用户名">
          <el-input v-model="formData.username"/>
        </el-form-item>
        <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码">
          <el-input v-model="formData.password" type="password"/>
        </el-form-item>
      </el-form>
      <div class="login-button">
        <el-button type="warning" @click="formData = {} as UserForm">重置</el-button>
        <el-button type="success" @click="login">登录</el-button>
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
