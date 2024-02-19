import type { SearchParameters } from 'ofetch';
import type { Article, Page } from '~/types';

export const getList = async (current = 1, size = 5, terms?: SearchParameters) => {
  return await useRequest.get<Page<Article>>('/article', {
    params: {
      current, size, ...terms
    }
  });
}

export const getById = async (id: string) => {
  return await useRequest.get<Article>(`/article/${id}`)
}

export const add = async (body: Article) => {
  return await useRequest.post<any>(`/admin/article`, body)
}

export const edit = async (id: number, body: Article) => {
  return await useRequest.put<any>(`/admin/article/${id}`, body)
}

export const remove = async (id: number) => {
  return await useRequest.delete(`/admin/article/${id}`)
}
