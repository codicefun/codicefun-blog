enum API {
  LOGIN = '/auth/login',
  VALIDATE = '/auth/validate'
}

export interface Token {
  token: string
}

export const login = async (username: string, password: string) => {
  return await useRequest.post<Token>(API.LOGIN, { username, password })
}

export const validate = async (token: string) => {
  return await useRequest.post<any>(API.VALIDATE, { token })
}
