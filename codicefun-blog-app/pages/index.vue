<script lang="ts" setup>
import moment from 'moment';
import apis from '~/apis';
import type { Article, Page } from '~/types'

const tableData = ref({} as Page<Article>)

const { data, error } = await useAsyncData(() => apis.article.getList())
if (!error.value) {
  tableData.value = data.value?.data as Page<Article>
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.article.getList(val)
  tableData.value = data
}
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <!-- Article card list -->
      <el-card v-for="article in tableData.record" :key="article" class="box-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <nuxt-link :to="`/article/${article.id}`">{{ article.title }}</nuxt-link>
          </div>
        </template>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-image :src="article.picture" fit="fill" style=""/>
          </el-col>
          <el-col :span="16">
            {{ article.description }}
          </el-col>
        </el-row>
        <template #footer>
          <div class="article-meta">
            <span>Author: Admin</span>
            <span>Type: {{ article.typename }}</span>
            <span>Viewed: {{ article.viewed }}</span>
            <span>Liked: {{ article.liked }}</span>
            <span>Create Time: {{ moment(article.createTime).format('YYYY-MM-DD') }}</span>
          </div>
          <div class="article-tag">
            <span>Tag: </span>
            <el-tag v-for="tag in article.tagNameList" :key="tag">
              {{ tag }}
            </el-tag>
          </div>
        </template>
      </el-card>
      <div class="pagination">
        <el-pagination
            :total="tableData.total"
            v-model:current-page="tableData.current"
            v-model:page-size="tableData.size"
            background
            layout="prev, pager, next"
            @current-change="handleCurrentChange"
        />
      </div>
    </el-col>
    <el-col :span="8">
      <el-card class="box-card" shadow="hover">Right</el-card>
    </el-col>
  </el-row>
</template>

<style scoped>
.box-card {
  margin: 10px 0;
}

.card-header {
  font-size: 20px;
  font-weight: bold;
}

.card-footer {
  display: flex;
}

.pagination {
  display: flex;
  justify-content: right;
}

.article-meta {
  display: flex;
  justify-content: space-between;
}

.article-tag {
  margin-top: 10px;
}

.el-tag {
  margin: 0 5px;
}
</style>
