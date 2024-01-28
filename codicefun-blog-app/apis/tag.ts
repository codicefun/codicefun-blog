import type { Tag } from '~/types';

export const getAll = async () => {
  return await useRequest.get<Tag[]>('/tag/all')
}
