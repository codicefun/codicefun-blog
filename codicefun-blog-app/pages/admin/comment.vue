<script setup lang="ts">
import type { Comment, Page } from "~/types";
import apis from "~/apis";
import { MdEditor } from "md-editor-v3";
import 'md-editor-v3/lib/preview.css';
import 'md-editor-v3/lib/style.css';

definePageMeta({
  layout: 'admin',
  middleware: 'auth',
})

const commentTable = ref({} as Page<Comment>)
const commentForm = ref({} as Comment)
const dialogVisible = ref(false)
const dialogTitle = ref('Add')

const getCommentList = async () => {
  const { data } = await apis.comment.getAll();
  commentTable.value = data
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.comment.getAll(val);
  commentTable.value = data
}

const add = async () => {
  dialogVisible.value = true
  dialogTitle.value = 'Add'
  commentForm.value = {} as Comment
}

const edit = async (row: Comment) => {
  dialogVisible.value = true
  dialogTitle.value = 'Edit'
  commentForm.value = row
}

const remove = async (id: number) => {
  try {
    await ElMessageBox.confirm('Are you sure delete this tag?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    })
    await apis.comment.remove(id)
    ElMessage({ showClose: true, message: 'delete success', type: 'success' })
    await getCommentList()
  } catch (e: any) {
    if (!(e instanceof Error)) {
      ElMessage({ showClose: true, message: 'delete cancel', type: 'info' })
    }
  }
}

const update = async () => {
  dialogVisible.value = false
  // Add tag
  if (dialogTitle.value === 'Add') {
    await apis.comment.add(commentForm.value)
    ElMessage({ showClose: true, message: 'Add success', type: 'success' })
    await getCommentList()
  }
  // Edit tag
  else {
    await apis.comment.update(commentForm.value.id, commentForm.value)
    ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
  }
}

await getCommentList()
</script>

<template>
  <!--<el-button type="primary" @click="add">New Comment</el-button>-->
  <el-table :data="commentTable.record">
    <el-table-column label="ID" prop="id" width="100"/>
    <el-table-column label="Nickname" prop="nickname" width="200"/>
    <el-table-column label="Email" prop="email" width="200"/>
    <el-table-column label="Content" prop="content" width="600"/>
    <el-table-column label="Operations" width="200">
      <template #default="{row}">
        <el-button type="primary" @click="edit(row)">Edit</el-button>
        <el-button type="danger" @click="remove(row.id)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:current-page="commentTable.current"
      v-model:page-size="commentTable.size"
      :total="commentTable.total"
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
  />
  <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="1100"
  >
    <el-form
        :model="commentForm"
        label-width="60"
        label-position="left"
    >
      <el-form-item label="nickname">
        <el-input v-model="commentForm.nickname" style="width: 400px"/>
      </el-form-item>
      <el-form-item label="email">
        <el-input v-model="commentForm.email" style="width: 400px"/>
      </el-form-item>
      <el-form-item label="content">
        <md-editor v-model="commentForm.content"
                   :preview="false"
                   language="en-US"
                   show-code-row-number
                   style="height: 200px"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="update">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.el-pagination {
  margin-top: 10px;
}
</style>
