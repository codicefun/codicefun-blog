import {defineStore} from 'pinia';

interface UserStore {
  username: string
  token: string
}

export const useUserStore = defineStore('user', (): UserStore => {
    return {username: '', token: ''}
  },
  {
    persist: true,
  },
)
