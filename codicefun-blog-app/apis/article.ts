import type { SearchParameters } from 'ofetch';
import type { Article, Page } from '~/types';

export const getList = async (current = 1, size = 5, terms?: SearchParameters) => {
  return useRequest.get<Page<Article>>('/api/article', {
    params: {
      current, size, ...terms,
    },
  })
}

export const getById = async (id: number) => {
  return useRequest.get<Article>(`/api/article/${id}`)
}

export const add = async (body: Article) => {
  return useRequest.post(`/api/admin/article`, body)
}

export const update = async (id: number, body: Article) => {
  return useRequest.put(`/api/admin/article/${id}`, body)
}

export const increaseViewed = async (id: number) => {
  return useRequest.put(`/api/article/${id}/viewed/increase`)
}

export const remove = async (id: number) => {
  return useRequest.delete(`/api/admin/article/${id}`)
}
