<script setup lang="ts">
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import type { Article, Tag, Type } from '~/types';
import apis from '~/apis';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const route = useRoute()
const router = useRouter()
const formData = ref<Article>({} as Article)
const typeList = ref<Type[]>([] as Type[])
const tagList = ref([] as Tag[])

formData.value.content = '# Test content'

const getArticle = async () => {
  try {
    const resp = await apis.article.getById(Number(route.params.id))
    formData.value = resp.data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const getTypeList = async () => {
  try {
    const resp = await apis.type.getAll()
    typeList.value = resp.data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const getTagList = async () => {
  try {
    const resp = await apis.tag.getAll()
    tagList.value = resp.data
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
  try {
    await apis.article.update(formData.value.id, formData.value)
    await router.push('/admin/article')
    ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

await getArticle()
await getTypeList()
await getTagList()
</script>

<template>
  <el-form
      :model="formData"
      label-position="top"
  >
    <el-form-item label="Title">
      <el-input v-model="formData.title"/>
    </el-form-item>
    <el-form-item label="Description">
      <el-input v-model="formData.description" :autosize="{minRows: 3}" type="textarea"/>
    </el-form-item>
    <el-form-item label="Content">
      <md-editor v-model="formData.content"/>
    </el-form-item>
    <el-form-item label="Type">
      <el-select v-model="formData.typename"
                 :reserve-keyword="false"
                 allow-create
                 filterable
                 default-first-option
                 style="width: 240px">
        <el-option v-for="item in typeList" :key="item.id" :value="item.name"/>
      </el-select>
    </el-form-item>
    <el-form-item label="Tags">
      <el-select v-model="formData.tagNameList"
                 :reserve-keyword="false"
                 allow-create
                 filterable
                 multiple
                 default-first-option
                 style="width: 400px">
        <el-option v-for="item in tagList" :key="item.id" :value="item.name"/>
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
