import * as React from "react"
import { BrowserRouter, Route, Routes, RouterProvider, createBrowserRouter } from "react-router-dom"
import MainScreen from "../pages/MainScreen"
import ErrorPage from "../pages/ErrorPage"
import ConsoleScreen from "../pages/ConsoleScreen"
import SearchResultScreen from "../pages/SearchResultScreen"

const router = createBrowserRouter([
    {
        path: "/",
        element: <MainScreen />,
        errorElement: <ErrorPage />,
    },
    {
        path: "/console",
        element: <ConsoleScreen />,
        errorElement: <ErrorPage />,
    },
    {
        path: "/search",
        element: <SearchResultScreen />,
        errorElement: <ErrorPage />,
    },
])

function MainNavigation() {
    return (
        <RouterProvider router={ router } />
    )
}

export default MainNavigation
