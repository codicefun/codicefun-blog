import type { Token } from '~/types';

export const login = async (username: string, password: string) => {
  return useRequest.post<Token>('/api/auth/login', { username, password })
}

export const validate = async (token: string) => {
  return useRequest.post<any>('/api/auth/validate', { token })
}
