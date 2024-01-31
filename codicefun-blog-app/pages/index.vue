<script lang="ts" setup>
import moment from 'moment';
import apis from '~/apis';
import type { Article, Page } from '~/types';

const articleList = ref<Page<Article>>({} as Page<Article>)
const total = ref(0)
const current = ref(0)
const size = ref(0)

const getArticleList = async () => {
  try {
    const { data } = await apis.article.getList()
    articleList.value = data
    total.value = data.total
    current.value = data.current
    size.value = data.size
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const handleCurrentChange = async (val: number) => {
  try {
    const { data } = await apis.article.getList(val)
    articleList.value = data
    current.value = data.current
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

await getArticleList()
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-card v-for="article in articleList.record" :key="article" class="box-card" shadow="hover">
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
          <el-row class="card-footer">
            <el-col :span="3">
              type: {{ article.typename }}
            </el-col>
            <el-col :span="4">
              viewed: {{ article.viewed }}
            </el-col>
            <el-col :span="4">
              liked: {{ article.liked }}
            </el-col>
            <el-col :span="5">
              create time: {{ moment(article.createTime).format('YYYY-MM-DD') }}
            </el-col>
          </el-row>
        </template>
      </el-card>
      <div class="pagination">
        <el-pagination
            v-model:current-page="current"
            v-model:page-size="size"
            :total="total"
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
  //justify-content: right;
}

.pagination {
  display: flex;
  justify-content: right;
}
</style>
