<script lang="ts" setup>
import apis from '~/apis';
import type { Article } from '~/types';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const router = useRouter();
const tableData = ref<Article[]>()
const current = ref(0)
const size = ref(0)
const total = ref(0)

const getArticleList = async () => {
  try {
    const { data } = await apis.article.getList();
    tableData.value = data.record
    current.value = data.current
    size.value = data.size
    total.value = data.total
  } catch (e: any) {
    console.log(e)
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const handleCurrentChange = async (val: number) => {
  try {
    const { data } = await apis.article.getList(val);
    tableData.value = data.record
    current.value = data.current
    size.value = data.size
    total.value = data.total
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const add = async () => {
  await router.push('/admin/article/add')
}

const edit = async (id: number) => {
  await router.push(`/admin/article/${id}/edit`)
}

const remove = async (id: number) => {
  try {
    await apis.article.remove(id)
    ElMessage({ showClose: true, message: 'delete success', type: 'success' })
    await getArticleList()
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

await getArticleList()
</script>

<template>
  <el-button type="primary" @click="add">New Article</el-button>
  <el-table :data="tableData">
    <el-table-column label="ID" prop="id" width="100"/>
    <el-table-column prop="title" label="Title" width="600"/>
    <el-table-column label="type" prop="typename" width="100"/>
    <el-table-column label="Operations" width="200">
      <template #default="{row}">
        <el-button type="primary" @click="edit(row.id)">Edit</el-button>
        <el-button type="danger" @click="remove(row.id)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:current-page="current"
      v-model:page-size="size"
      :total="total"
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
  />
</template>

<style scoped>
.el-pagination {
  margin-top: 10px;
}
</style>
