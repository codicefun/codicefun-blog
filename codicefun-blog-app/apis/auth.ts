enum API {
  LOGIN = '/auth/login'
}

export interface Token {
  token: string
}

export const login = async (username: string, password: string) => {
  return await useRequest.post<Token>(API.LOGIN, { username, password })
}
