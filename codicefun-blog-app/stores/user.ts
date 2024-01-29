import { defineStore } from 'pinia';

interface UserStore {
  username: string
  token: string
}

export const useUserStore = defineStore('user', {
  state: (): UserStore => {
    return {
      username: '',
      token: ''
    }
  },
  actions: {
    
  },
  persist: true
})
