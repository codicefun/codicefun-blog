<script lang="ts" setup>
import moment from 'moment';
import apis from '~/apis';
import type { Ref } from 'vue';
import type { Article, Page } from '~/types';

const { data: resp, error } = await apis.article.getList()

if (error.value) {
  ElMessage({
    showClose: true,
    message: error.value.message,
    type: 'error'
  })
}

const data: Ref<Page<Article> | undefined> = ref(resp.value?.data)
const current = ref(data.value?.current)
const size = ref(data.value?.size)

const handleCurrentChange = async (val: number) => {
  const { data: resp, error } = await apis.article.getList(val)

  if (error.value) {
    ElMessage({
      showClose: true,
      message: error.value.message,
      type: 'error'
    })
  }
  data.value = resp.value?.data
  current.value = data.value?.current
}
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-card v-for="article in data?.record" :key="article" class="box-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <span>{{ article.title }}</span>
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
              分类：{{ article.typename }}
            </el-col>
            <el-col :span="4">
              浏览人数：{{ article.viewed }}
            </el-col>
            <el-col :span="4">
              点赞人数：{{ article.liked }}
            </el-col>
            <el-col :span="5">
              发布时间：{{ moment(article.createTime).format('YYYY-MM-DD') }}
            </el-col>
          </el-row>
        </template>
      </el-card>
      <div class="pagination">
        <el-pagination
            v-model:current-page="current"
            v-model:page-size="size"
            :total="data?.total"
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
