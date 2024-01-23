enum API {
  GET_USER_LIST = '/user'
}

interface User {
  id: number
  username: string
  password: string
  nickname: string
  email: string
  avatar: string
  role: string
  createTime: Date
  updateTime: Date
}

export const getUserList = async (current = 1, size = 5) => {
  return await useRequest.get(API.GET_USER_LIST, { params: { current, size } })
}
