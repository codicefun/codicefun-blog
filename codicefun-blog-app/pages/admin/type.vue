<script setup lang="ts">
import type { Page, Type } from "~/types";
import apis from "~/apis";

definePageMeta({
  layout: 'admin',
  middleware: 'auth',
})

const typeTable = ref({} as Page<Type>)
const typeForm = ref({} as Type)
const dialogVisible = ref(false)
const dialogTitle = ref('Add')

const getTypeList = async () => {
  const { data } = await apis.type.getByEquals();
  typeTable.value = data
}

const handleCurrentChange = async (val: number) => {
  const { data } = await apis.type.getByEquals(val);
  typeTable.value = data
}

/**
 * Add type
 */
const add = async () => {
  dialogVisible.value = true
  dialogTitle.value = 'Add'
  typeForm.value = {} as Type
}

/**
 * Edit type
 * @param row type
 */
const edit = async (row: Type) => {
  dialogVisible.value = true
  dialogTitle.value = 'Edit'
  typeForm.value = row
}

const remove = async (id: number) => {
  try {
    await ElMessageBox.confirm('Are you sure delete this type?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    });
    await apis.type.remove(id)
    ElMessage({ showClose: true, message: 'delete success', type: 'success' })
    await getTypeList()
  } catch (e: any) {
    if (!(e instanceof Error)) {
      ElMessage({ showClose: true, message: 'delete cancel', type: 'info' })
    }
  }
}

const update = async () => {
  dialogVisible.value = false
  // Add type
  if (dialogTitle.value === 'Add') {
    await apis.type.add(typeForm.value)
    ElMessage({ showClose: true, message: 'Add success', type: 'success' })
    await getTypeList()
  }
  // Edit type
  else {
    await apis.type.update(typeForm.value.id, typeForm.value)
    ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
  }
}

await getTypeList()
</script>

<template>
  <el-button type="primary" @click="add">New Type</el-button>
  <el-table :data="typeTable.record">
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
      v-model:current-page="typeTable.current"
      v-model:page-size="typeTable.size"
      :total="typeTable.total"
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
        v-model="typeForm"
    >
      <el-form-item label="name">
        <el-input v-model="typeForm.name"/>
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
