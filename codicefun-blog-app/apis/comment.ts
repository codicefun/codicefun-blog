import type { Comment, Page } from "~/types";

export const getByArticleId = async (articleId: number, current: number, size: number) => {
  return await useRequest.get<Page<Comment>>(`/comment/article/${articleId}`, {
    params: { current, size }
  })
}

export const getAll = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Comment>>('/comment', {
    params: { current, size }
  })
}

export const add = async (body: Comment) => {
  return await useRequest.post<any>('/comment', body)
}

export const update = async (id: number, body: Comment) => {
  return await useRequest.put<any>(`/admin/comment/${id}`, body)
}

export const remove = async (id: number) => {
  return await useRequest.delete(`/admin/comment/${id}`)
}
