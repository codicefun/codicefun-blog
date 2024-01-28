<script lang="ts" setup>
import apis from '~/apis';
import type { Article } from '~/types';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const router = useRouter();

const tableData = ref<Article[]>()
const current = ref<number>()
const size = ref<number>()
const total = ref<number>()

const { data, error } = await apis.article.getList();

if (error.value) {
  ElMessage({ showClose: true, message: error.value.message, type: 'error' })
}

const resp = data.value?.data
tableData.value = resp?.record
current.value = resp?.current
size.value = resp?.size
total.value = resp?.total

const handleCurrentChange = async (val: number) => {
  const { data, error } = await apis.article.getList(val);

  if (error.value) {
    ElMessage({ showClose: true, message: error.value.message, type: 'error' })
  }

  const resp = data.value?.data
  tableData.value = resp?.record
  current.value = resp?.current
  size.value = resp?.size
  total.value = resp?.total
}

const edit = async (id: number) => {
  await router.push(`/admin/article/${id}/edit`)
}

</script>

<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="ID" prop="id" width="100"/>
    <el-table-column prop="title" label="Title" width="600"/>
    <el-table-column label="type" prop="typename" width="100"/>
    <el-table-column label="Operations" width="200">
      <template #default="{row}">
        <el-button type="primary" @click="edit(row.id)">Edit</el-button>
        <el-button type="danger">Delete</el-button>
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
