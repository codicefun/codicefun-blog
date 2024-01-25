<script setup lang="ts">
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import type { Article } from '~/types';
import apis from '~/apis';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const formData = ref<Article>({} as Article)
const route = useRoute()
const router = useRouter()

formData.value.content = '# Test content'

const { data, error } = await apis.article.getById(route.params.id as string)

if (error.value) {
  ElMessage({ showClose: true, message: error.value.message, type: 'error' })
} else {
  formData.value = data.value?.data as Article
}

const cancel = async () => {
  try {
    await ElMessageBox.confirm(
        'Are you sure?',
        'Warning',
        {
          confirmButtonText: 'YES',
          cancelButtonText: 'NO',
          type: 'warning',
        }
    )
    await router.push('/admin/article')
    ElMessage({ showClose: true, message: 'Cancel edit', type: 'info' })
  } catch (e) {
    ElMessage({ showClose: true, message: 'Continue edit', type: 'info' })
  }
}
</script>

<template>
  <el-form
      :model="formData"
      label-position="top"
  >
    <el-form-item label="Title">
      <el-input v-model="formData.title"/>
    </el-form-item>
    <el-form-item label="Content">
      <md-editor v-model="formData.content"/>
    </el-form-item>
    <el-form-item label="Type">
      <el-input v-model="formData.typeName"/>
    </el-form-item>
  </el-form>
  <div class="form-button">
    <el-button type="warning" @click="cancel">Cancel</el-button>
    <el-button type="success">Submit</el-button>
  </div>
</template>

<style scoped>
.form-button {
  display: flex;
  justify-content: right;
}
</style>
