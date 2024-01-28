import type { Type } from '~/types';

export const getList = async () => {
  return await useRequest.get<Type[]>('/type/all')
}
