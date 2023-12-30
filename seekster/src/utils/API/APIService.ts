import {
    CRAWL_BASE_URL,
    INDEXER_BASE_URL,
    SEARCH_QUERY,
    CREATE_WEBSITE,
} from "../constants/URL_CONSTANT"

export interface ApiResponse<T> {
    success: boolean
    data: T
}

export const crawlFetch = async <T>(
    url: string,
    options?: RequestInit
): Promise<ApiResponse<T | { error: string }>> => {
    const response = await fetch(`${CRAWL_BASE_URL}${url}`, options)

    if (!response.ok) {
        return {
            success: false,
            data: { error: `HTTP error! Status: ${response.status}` },
        } as ApiResponse<T | { error: string }>
    }

    const responseData = await response.json()

    return {
        success: true,
        data: responseData,
    }
}

export const indexerFetch = async <T>(
    url: string,
    options?: RequestInit
): Promise<ApiResponse<T | { error: string }>> => {
    const response = await fetch(`${INDEXER_BASE_URL}${url}`, options)

    if (!response.ok) {
        return {
            success: false,
            data: { error: `HTTP error! Status: ${response.status}` },
        }
    }

    const responseData = await response.json()

    return {
        success: true,
        data: responseData.data,
    }
}

export const searchQuery = async (
    query: any
): Promise<ApiResponse<any>> => {
    const result = await indexerFetch(SEARCH_QUERY, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(query),
    })
    console.log("API", JSON.stringify(query))
    if (result.data) {
        result.success = true
    } else {
        result.success = false
        result.data = { error: "Custom error message for search query failure" }
    }

    return result
}

export const saveWebsite = async (
    websiteData: any
): Promise<ApiResponse<any>> => {
    const result = await crawlFetch(CREATE_WEBSITE, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(websiteData),
    })

    if (result.data) {
        result.success = true
    } else {
        result.success = false
        result.data = { error: "Custom error message for saveWebsite failure" }
    }

    return result
}

