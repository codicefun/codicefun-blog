import type { RequestOption, Response } from "~/types";

const request = async <T>(url: string, options: RequestOption) => {
  return $fetch<Response<T>>(url, {
    method: 'GET',
    baseURL: 'http://localhost:8080',

    onRequest({ options, request }) {
      if ((request as string).startsWith('/api/admin')) {
        const userStore = useUserStore()
        options.headers = { ...options.headers, token: userStore.token }
      }
    },

    onResponse({ response }) {
      if (response.status !== 200 || response._data.code != 200) {
        ElMessage({ showClose: true, message: response._data.message, type: 'error' })
        return Promise.reject(response._data)
      }
    },

    ...options,
  })
}

export const useRequest = {
  post: async <T>(url: string, body?: any, options?: RequestOption) => {
    return request<T>(url, { method: 'POST', body, ...options })
  },

  get: async <T>(url: string, options?: RequestOption) => {
    return request<T>(url, { method: 'GET', ...options })
  },

  put: async <T>(url: string, body?: any, options?: RequestOption) => {
    return request<T>(url, { method: 'PUT', body, ...options })
  },

  delete: async <T>(url: string, body?: any, options?: RequestOption) => {
    return request<T>(url, { method: 'DELETE', body, ...options })
  },
}
