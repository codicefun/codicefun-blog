import type { Page, Type } from '~/types';

export const getAll = async () => {
  return await useRequest.get<Type[]>('/type/all')
}

export const getByEquals = async (current = 1, size = 5) => {
  return await useRequest.get<Page<Type>>('/type/page', {
    params: { current, size }
  })
}

export const add = async (body: Type) => {
  return await useRequest.post<any>('/admin/type', body)
}

export const update = async (id: number, body: Type) => {
  return await useRequest.put<any>(`/admin/type/${id}`, body)
}

export const remove = async (id: number) => {
  return await useRequest.delete<any>(`/admin/type/${id}`)
}
