import apis from '~/apis';

const isAuthenticated = async () => {
  const userStore = useUserStore();
  const token = userStore.token

  if (token === '' || token === undefined) {
    return false
  }

  const { error } = await apis.auth.validate(token)

  if (error.value) {
    ElMessage({ showClose: true, message: error.value.message, type: 'error' })
    return false
  }

  return true
}

export default defineNuxtRouteMiddleware(async (to) => {
  if (await isAuthenticated()) {
    if (to.path === '/admin/login') {
      ElMessage({ showClose: true, message: 'You have login', type: 'warning' })
      return abortNavigation()
    }
    return true
  }

  if (to.path === '/admin/login') {
    return true
  }

  return navigateTo('/admin/login')
})
