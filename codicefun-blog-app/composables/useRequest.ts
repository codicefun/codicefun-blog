import type { UseFetchOptions } from '#app';
import type { Ref } from 'vue';

export interface ResponseOptions<T> {
  code: number
  message: string
  data: T
}

export type UrlType = string | Request | Ref<string | Request> | (() => string | Request)

export type RequestOption<T> = UseFetchOptions<ResponseOptions<T>>

const request = <T>(url: UrlType, options: RequestOption<T>) => {
  return useFetch<ResponseOptions<T>>(url, {
    baseURL: 'http://localhost:8080/api',

    onRequest() {

    },

    onResponse({ response }): Promise<void> | void {
      if (response.status !== 200) {
        console.log(response)
      }
    },

    ...options
  })
}

export const useRequest = {
  post: <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return request<T>(url, { method: 'POST', body, ...options })
  },

  get: <T>(url: UrlType, options?: RequestOption<T>) => {
    return request<T>(url, { method: 'GET', ...options })
  },

  put: <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return request<T>(url, { method: 'PUT', body, ...options })
  },

  delete: <T>(url: UrlType, body?: any, options?: RequestOption<T>) => {
    return request<T>(url, { method: 'DELETE', body, ...options })
  },
}
