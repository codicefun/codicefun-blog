import type { Comment, Page } from "~/types";

export const getByArticleId = async (articleId: number, current = 1, size = 5) => {
  return useRequest.get<Page<Comment>>(`/api/comment/article/${articleId}`, {
    params: { current, size },
  })
}

export const getAll = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Comment>>('/api/comment', {
    params: { current, size },
  })
}

export const add = async (body: Comment) => {
  return useRequest.post<any>('/api/comment', body)
}

export const update = async (id: number, body: Comment) => {
  return useRequest.put<any>(`/api/admin/comment/${id}`, body)
}

export const remove = async (id: number) => {
  return useRequest.delete(`/api/admin/comment/${id}`)
}
