<script setup lang="ts">
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import type { Article, Type } from '~/types';
import apis from '~/apis';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const route = useRoute()
const router = useRouter()
const formData = ref<Article>({} as Article)
const typeList = ref<Type[]>([] as Type[])

formData.value.content = '# Test content'

const getArticle = async () => {
  try {
    const resp = await apis.article.getById(route.params.id as string)
    formData.value = resp.data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const getTypeList = async () => {
  try {
    const resp = await apis.type.getList()
    typeList.value = resp.data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
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

const submit = async () => {
  console.log(formData.value)
}

await getArticle()
await getTypeList()
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
      <el-select v-model="formData.typename"
                 allow-create
                 filterable
                 style="width: 240px">
        <el-option v-for="item in typeList" :key="item.id" :value="item.name"/>
      </el-select>
    </el-form-item>
    <el-form-item label="Tags">
      <el-select v-model="formData.tagNameList"
                 allow-create
                 filterable
                 multiple
                 style="width: 400px">

      </el-select>
    </el-form-item>
  </el-form>
  <div class="form-button">
    <el-button type="warning" @click="cancel">Cancel</el-button>
    <el-button type="success" @click="submit">Submit</el-button>
  </div>
</template>

<style scoped>
.form-button {
  display: flex;
  justify-content: right;
}
</style>
