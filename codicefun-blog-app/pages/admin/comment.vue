<script setup lang="ts">
import type { Comment } from "~/types";
import apis from "~/apis";
import { MdEditor } from "md-editor-v3";
import 'md-editor-v3/lib/preview.css';
import 'md-editor-v3/lib/style.css';

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const tableData = ref({} as Comment[])
const comment = ref({} as Comment)
const current = ref(0)
const size = ref(0)
const total = ref(0)

const dialogVisible = ref(false)
const dialogTitle = ref('Add')

const getCommentList = async () => {
  const { data } = await apis.comment.getAll();
  tableData.value = data.record
  current.value = data.current
  size.value = data.size
  total.value = data.total
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.comment.getAll(val);
  tableData.value = data.record
  current.value = data.current
  size.value = data.size
  total.value = data.total
}

const add = async () => {
  dialogVisible.value = true
  dialogTitle.value = 'Add'
  comment.value = {} as Comment
}

const edit = async (row: Comment) => {
  dialogVisible.value = true
  dialogTitle.value = 'Edit'
  comment.value = row
}

const remove = async (id: number) => {
  try {
    await ElMessageBox.confirm('Are you sure delete this tag?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning'
    });
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
    await apis.comment.add(comment.value)
    ElMessage({ showClose: true, message: 'Add success', type: 'success' })
    await getCommentList()
  }
  // Edit tag
  else {
    await apis.comment.update(comment.value.id, comment.value)
    ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
  }
}

await getCommentList()
</script>

<template>
  <!--<el-button type="primary" @click="add">New Comment</el-button>-->
  <el-table :data="tableData">
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
      v-model:current-page="current"
      v-model:page-size="size"
      :total="total"
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
        :model="comment"
        label-width="60"
        label-position="left"
    >
      <el-form-item label="nickname">
        <el-input v-model="comment.nickname" style="width: 400px"/>
      </el-form-item>
      <el-form-item label="email">
        <el-input v-model="comment.email" style="width: 400px"/>
      </el-form-item>
      <el-form-item label="content">
        <md-editor v-model="comment.content"
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
