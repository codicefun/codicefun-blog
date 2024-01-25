enum API {
  GET_USER_LIST = '/user'
}

export const getUserList = async (current = 1, size = 5) => {
  return await useRequest.get(API.GET_USER_LIST, { params: { current, size } })
}
