<script lang="ts" setup>
import apis from '~/apis';
import type { Article, Comment, Page } from '~/types';
import { MdCatalog, MdEditor, MdPreview } from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';
import 'md-editor-v3/lib/style.css';
import moment from 'moment';

const route = useRoute();
const article = ref({} as Article)
const comment = ref({} as Comment)
const commentList = ref({} as Page<Comment>)
const commentText = ref('You can comment')

const getArticle = async () => {
  try {
    const { data } = await apis.article.getById(route.params.id as string)
    article.value = data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const getComment = async (current = 1) => {
  try {
    const { data } = await apis.comment.getByArticleId(article.value.id, current, 10)
    commentList.value = data
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const addComment = async () => {
  comment.value.articleId = article.value.id
  await apis.comment.add(comment.value)
  ElMessage({ showClose: true, message: 'comment success', type: 'success' })
  commentText.value = 'You can comment'
  // refresh comment list
  await getComment()
}

await getArticle()
await getComment()

let scrollElement: string | HTMLElement | undefined = undefined
onMounted(() => {
  scrollElement = document.documentElement
})
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-card shadow="hover">
        <template #header>
          <h1>{{ article.title }}</h1>
        </template>
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
      </el-card>
      <el-card class="article-content" shadow="hover">
        <md-preview :model-value="article.content"
                    code-theme="atom"
                    editor-id="preview"
                    preview-theme="default"
                    show-code-row-number
        />
      </el-card>
      <!-- Comment list -->
      <el-card shadow="hover">
        <template #header>
          Comment List
        </template>
        <div v-for="comment in commentList.record" :key="comment.id">
          {{ comment.content }}
          <hr>
        </div>
        <template #footer>
          <!-- TODO: language Hydration node mismatch -->
          <el-form
              :model="comment"
              label-position="left"
              label-width="60px"
          >
            <el-form-item label="nickname">
              <el-input v-model="comment.nickname"/>
            </el-form-item>
            <el-form-item label="email">
              <el-input v-model="comment.email"/>
            </el-form-item>
            <el-form-item label="content">
              <md-editor v-model="comment.content"
                         :preview="false"
                         editor-id="comment"
                         language="en-US"
                         show-code-row-number
                         style="height: 200px"
              />
            </el-form-item>
          </el-form>
          <div style="display: flex; justify-content: right; margin-top: 10px;">
            <el-button type="primary" @click="addComment">Comment</el-button>
          </div>
        </template>
      </el-card>
    </el-col>
    <el-col :span="8" class="right">
      <el-affix target=".right">
        <el-card shadow="hover">
          <client-only>
            <md-catalog :scroll-element="scrollElement"
                        editor-id="preview"
                        theme="dark"
            />
          </client-only>
        </el-card>
      </el-affix>
    </el-col>
  </el-row>
</template>

<style scoped>
.el-card {
  margin: 10px 0;
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

<style>
#preview-preview-wrapper {
  padding: 0;
}

#preview-preview-wrapper h1 {
  margin-top: 0;
}
</style>
