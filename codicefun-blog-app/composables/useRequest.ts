import type { UseFetchOptions } from '#app';
import type { Ref } from 'vue';

export interface ResponseOptions<T> {
  code: number
  message: string
  data: T
}

export type UrlType = string | Request | Ref<string | Request> | (() => string | Request)

export type RequestOption<T> = UseFetchOptions<ResponseOptions<T>>

const request = async <T>(url: UrlType, options: RequestOption<T>) => {
  return useFetch<ResponseOptions<T>>(url, {
    baseURL: 'http://localhost:8080/api',

    onRequest() {

    },

    onResponse({ response }) {
      if (response.status !== 200) {
        return Promise.reject(response._data)
      }
      return response._data.data
    },

    ...options
  })
}

export const useRequest = {
  post: async <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return await request<T>(url, { method: 'POST', body, ...options })
  },

  get: async <T>(url: UrlType, options?: RequestOption<T>) => {
    return await request<T>(url, { method: 'GET', ...options })
  },

  put: async <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return await request<T>(url, { method: 'PUT', body, ...options })
  },

  delete: async <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return await request<T>(url, { method: 'DELETE', body, ...options })
  },
}
