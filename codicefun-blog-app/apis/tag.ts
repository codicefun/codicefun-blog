import type { Page, Tag } from '~/types';

export const getAll = async () => {
  return useRequest.get<Tag[]>('/api/tag/all')
}

export const getByEquals = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Tag>>('/api/tag/page', {
    params: { current, size },
  })
}

export const add = async (body: Tag) => {
  return useRequest.post<any>('/api/admin/tag', body)
}

export const update = async (id: number, body: Tag) => {
  return useRequest.put<any>(`/api/admin/tag/${id}`, body)
}

export const remove = async (id: number) => {
  return useRequest.delete<any>(`/api/admin/tag/${id}`)
}
