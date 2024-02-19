<script setup lang="ts">
import type { Type } from "~/types";
import apis from "~/apis";

definePageMeta({
  layout: 'admin',
  middleware: 'auth'
})

const tableData = ref<Type[]>()
const type = ref({} as Type)
const current = ref(0)
const size = ref(0)
const total = ref(0)

const dialogVisible = ref(false)
const dialogTitle = ref('Add')

const getTypeList = async () => {
  try {
    const { data } = await apis.type.getByEquals();
    tableData.value = data.record
    current.value = data.current
    size.value = data.size
    total.value = data.total
  } catch (e: any) {
    console.log(e)
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

const handleCurrentChange = async (val: number) => {
  try {
    const { data } = await apis.type.getByEquals(val);
    tableData.value = data.record
    current.value = data.current
    size.value = data.size
    total.value = data.total
  } catch (e: any) {
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

/**
 * Add type
 */
const add = async () => {
  dialogVisible.value = true
  dialogTitle.value = 'Add'
  type.value = {} as Type
}

/**
 * Edit type
 * @param row type
 */
const edit = async (row: Type) => {
  dialogVisible.value = true
  dialogTitle.value = 'Edit'
  type.value = row
}

const remove = async (id: number) => {
  try {
    await ElMessageBox.confirm('Are you sure delete this type?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning'
    });
    await apis.type.remove(id)
    ElMessage({ showClose: true, message: 'delete success', type: 'success' })
    await getTypeList()
  } catch (e: any) {
    if (e instanceof Error) {
      ElMessage({ showClose: true, message: e.message, type: 'error' })
    } else {
      ElMessage({ showClose: true, message: 'delete cancel', type: 'info' })
    }
  }
}

const update = async () => {
  dialogVisible.value = false
  try {
    // Add type
    if (dialogTitle.value === 'Add') {
      await apis.type.add(type.value)
      ElMessage({ showClose: true, message: 'Add success', type: 'success' })
      await getTypeList()
    }
    // Edit type
    else {
      await apis.type.update(type.value.id, type.value)
      ElMessage({ showClose: true, message: 'Edit success', type: 'success' })
    }
  } catch (e: any) {
    console.log(e)
    console.log(typeof e)
    ElMessage({ showClose: true, message: e.message, type: 'error' })
  }
}

await getTypeList()
</script>

<template>
  <el-button type="primary" @click="add">New Type</el-button>
  <el-table :data="tableData">
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
      width="500"
  >
    <el-form
        v-model="type"
    >
      <el-form-item label="name">
        <el-input v-model="type.name"/>
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
