import apis from '~/apis';

const isAuthenticated = async () => {
  try {
    const userStore = useUserStore();
    const token = userStore.token

    if (token === '' || token === undefined) {
      return false
    }
    await apis.auth.validate(token)
    return true
  } catch (e: any) {
    return false
  }
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

  ElMessage({ showClose: true, message: 'You must login', type: 'warning' })
  return navigateTo('/admin/login')
})
