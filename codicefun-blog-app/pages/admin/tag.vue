<script setup lang="ts">
import type { Page, Tag } from "~/types";
import apis from "~/apis";

definePageMeta({
  layout: 'admin',
  middleware: 'auth',
})

const tagTable = ref({} as Page<Tag>)
const tagForm = ref({} as Tag)
const dialogVisible = ref(false)
const dialogTitle = ref('Add')

const getTagList = async () => {
  const { data } = await apis.tag.getByEquals();
  tagTable.value = data
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.tag.getByEquals(val);
  tagTable.value = data
}

const add = async () => {
  dialogVisible.value = true
  dialogTitle.value = 'Add'
  tagForm.value = {} as Tag
}

const edit = async (row: Tag) => {
  dialogVisible.value = true
  dialogTitle.value = 'Edit'
  tagForm.value = row
}

const remove = async (id: number) => {
  try {
    await ElMessageBox.confirm('Are you sure delete this tag?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    });
    await apis.tag.remove(id)
    ElMessage({ showClose: true, message: 'delete success', type: 'success' })
    await getTagList()
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
    await apis.tag.add(tagForm.value)
    ElMessage({ showClose: true, message: 'Add success', type: 'success' })
    await getTagList()
  }
  // Edit tag
  else {
    await apis.type.update(tagForm.value.id, tagForm.value)
    ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
  }
}

await getTagList()
</script>

<template>
  <el-button type="primary" @click="add">New Tag</el-button>
  <el-table :data="tagTable.record">
    <el-table-column label="ID" prop="id" width="100"/>
    <el-table-column label="Name" prop="name" width="600"/>
    <el-table-column label="Operations" width="200">
      <template #default="{row}">
        <el-button type="primary" @click="edit(row)">Edit</el-button>
        <el-button type="danger" @click="remove(row.id)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:current-page="tagTable.current"
      v-model:page-size="tagTable.size"
      :total="tagTable.total"
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
  />
  <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="500"
  >
    <el-form
        :model="tagForm"
    >
      <el-form-item label="name">
        <el-input v-model="tagForm.name"/>
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
