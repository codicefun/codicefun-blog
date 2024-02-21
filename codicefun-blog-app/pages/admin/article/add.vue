<script lang="ts" setup>
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import type { Article, Tag, Type } from '~/types';
import apis from '~/apis';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const router = useRouter()
const formData = ref<Article>({} as Article)
const typeList = ref<Type[]>([] as Type[])
const tagList = ref([] as Tag[])

const getTypeList = async () => {
  const resp = await apis.type.getAll()
  typeList.value = resp.data
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
    ElMessage({ showClose: true, message: 'Cancel add', type: 'info' })
  } catch {
    ElMessage({ showClose: true, message: 'Continue add', type: 'info' })
  }
}

const submit = async () => {
  await apis.article.add(formData.value)
  await router.push('/admin/article')
  ElMessage({ showClose: true, message: 'Add success', type: 'success' })
}

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
                 default-first-option
                 filterable
                 style="width: 240px">
        <el-option v-for="item in typeList" :key="item.id" :value="item.name"/>
      </el-select>
    </el-form-item>
    <el-form-item label="Tags">
      <el-select v-model="formData.tagNameList"
                 :reserve-keyword="false"
                 allow-create
                 default-first-option
                 filterable
                 multiple
                 style="width: 400px">
        <el-option v-for="item in tagList" :key="item.id" :value="item.name"/>
      </el-select>
    </el-form-item>
    <el-form-item label="Picture">
      <el-input v-model="formData.picture" style="width: 400px"/>
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
