<script lang="ts" setup>
import apis from '~/apis';
import type { Article, Page } from '~/types';

definePageMeta({
  layout: 'admin',
  middleware: 'auth',
})

const router = useRouter();
const articleTable = ref({} as Page<Article>)

const getArticleList = async () => {
  const { data } = await apis.article.getList();
  articleTable.value = data
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.article.getList(val);
  articleTable.value = data
}

const add = async () => {
  await router.push('/admin/article/add')
}

const edit = async (id: number) => {
  await router.push(`/admin/article/${id}/edit`)
}

const remove = async (id: number) => {
  await apis.article.remove(id)
  ElMessage({ showClose: true, message: 'delete success', type: 'success' })
  await getArticleList()
}

await getArticleList()
</script>

<template>
  <el-button type="primary" @click="add">New Article</el-button>
  <el-table :data="articleTable.record">
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
      v-model:current-page="articleTable.current"
      v-model:page-size="articleTable.size"
      :total="articleTable.total"
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
