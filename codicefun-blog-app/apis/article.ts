import type { SearchParameters } from 'ofetch';

export interface Pagination<T> {
  total: number
  current: number
  size: number
  list: T[]
}

export interface Article {
  id: number
  title: string
  content: string
  description: string
  picture: string
  userId: number
  typeId: number
  viewed: number
  liked: number
  createTime: Date
  updateTime: Date
}

export const getList = async (current = 1, size = 5, terms?: SearchParameters) => {
  return await useRequest.get<Pagination<Article>>('/article', {
    params: {
      current, size, ...terms
    }
  });
}

export const getById = async (id: number) => {
  return await useRequest.get<Article>(`/article/${id}`)
}
