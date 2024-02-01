export interface Page<T> {
  total: number
  current: number
  size: number
  record: T[]
}

export interface Token {
  token: string
}

export interface User {
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

export interface Type {
  id: number,
  name: string
}

export interface Tag {
  id: number
  name: string
}

export interface Article {
  id: number
  title: string
  content: string
  description: string
  picture: string
  viewed: number
  liked: number
  createTime: Date
  updateTime: Date

  username: string
  typename: string
  tagNameList: string[]
}