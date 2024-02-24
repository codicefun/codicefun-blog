import type { Page, Type } from '~/types';

export const getAll = async () => {
  return useRequest.get<Type[]>('/api/type/all')
}

export const getByEquals = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Type>>('/api/type/page', {
    params: { current, size },
  })
}

export const add = async (body: Type) => {
  return useRequest.post<any>('/api/admin/type', body)
}

export const update = async (id: number, body: Type) => {
  return useRequest.put<any>(`/api/admin/type/${id}`, body)
}

export const remove = async (id: number) => {
  return useRequest.delete<any>(`/api/admin/type/${id}`)
}
