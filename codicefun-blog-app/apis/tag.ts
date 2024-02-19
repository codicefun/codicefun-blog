import type { Page, Tag } from '~/types';

export const getAll = async () => {
  return await useRequest.get<Tag[]>('/tag/all')
}

export const getByEquals = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Tag>>('/tag/page', {
    params: { current, size }
  })
}

export const add = async (body: Tag) => {
  return await useRequest.post<any>('/admin/tag', body)
}

export const update = async (id: number, body: Tag) => {
  return await useRequest.put<any>(`/admin/tag/${id}`, body)
}

export const remove = async (id: number) => {
  return await useRequest.delete<any>(`/admin/tag/${id}`)
}
