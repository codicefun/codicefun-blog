import type { SearchParameters } from 'ofetch';

enum API {
  GET_ARTICLE_LIST = '/article'
}

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

export const getArticleList = async (current = 1, size = 5, terms?: SearchParameters) => {
  return await useRequest.get<Pagination<Article>>(API.GET_ARTICLE_LIST, {
    params: {
      current, size, ...terms
    }
  });
}
