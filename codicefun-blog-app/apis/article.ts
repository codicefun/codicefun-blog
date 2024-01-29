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

export const edit = async (id: number, body: Article) => {
  return await useRequest.put<any>(`/admin/article/${id}`, body)
}
