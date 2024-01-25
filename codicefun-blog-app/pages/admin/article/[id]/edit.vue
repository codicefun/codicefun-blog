<script setup lang="ts">
import type { Article } from '~/apis/article';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const formData = ref<Article>({} as Article)

formData.value.content = '# Test content'
</script>

<template>
  <el-form
      :model="formData"
      label-position="top"
  >
    <el-form-item label="Title">
      <el-input v-model="formData.id"/>
    </el-form-item>
    <el-form-item label="Content">
      <md-editor v-model="formData.content"/>
    </el-form-item>
    <el-form-item label="Type">
      <el-input v-model="formData.typeName"/>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>
