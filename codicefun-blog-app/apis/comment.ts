import type { Comment, Page } from "~/types";

export const getByArticleId = async (articleId: number, current: number, size: number) => {
    return await useRequest.get<Page<Comment>>(`/comment/article/${articleId}`, {
        params: { current, size }
    })
}

export const add = async (body: Comment) => {
    return await useRequest.post<any>('/comment', body)
}
