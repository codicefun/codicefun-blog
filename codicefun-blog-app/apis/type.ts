import type { Type } from '~/types';

export const getAll = async () => {
  return await useRequest.get<Type[]>('/type/all')
}
