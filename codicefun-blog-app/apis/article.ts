import type { SearchParameters } from 'ofetch';

export interface Page<T> {
  total: number
  current: number
  size: number
  record: T[]
}

export interface Article {
  id: number
  title: string
  content: string
  description: string
  picture: string
  viewed: number
  liked: number
  createTime: Date
  updateTime: Date

  username: string
  typeName: string
}

export const getList = async (current = 1, size = 5, terms?: SearchParameters) => {
  return await useRequest.get<Page<Article>>('/article', {
    params: {
      current, size, ...terms
    }
  });
}

export const getById = async (id: number) => {
  return await useRequest.get<Article>(`/article/${id}`)
}
