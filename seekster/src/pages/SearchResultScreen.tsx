import React from "react"
import { useLocation } from "react-router-dom"
import { NavLink } from "react-router-dom"
import { LinearProgress } from "@mui/material"
import SearchBar from "../components/SearchBar"
import ResultCard from "../components/ResultCard"
import { searchQuery, ApiResponse } from "../utils/API/APIService"
import ErrorCard from "../components/ErrorCard"

const SearchResultScreen = () => {
    const [searchResult, setSearchResult] = React.useState<ApiResponse<any>>({
        success: false,
        data: [],
    })
    const [loading, setLoading] = React.useState<boolean>(false)
    const [error, setError] = React.useState<string>("")
    const location = useLocation()
    const { searchTerm } = location.state

    React.useEffect(() => {
        fetchSearchResult()
    }, [])

    console.log("term query 1", searchTerm)

    const fetchSearchResult = (): Promise<void> => {
        console.log("term query 2", searchTerm)

        return searchQuery({ query: searchTerm }).then((term) => {
            setLoading(true)
            setSearchResult(term)
            console.log("Search result api---------->", term.data)
        }).catch((error) => {
            setError(error.message)
            console.log(error.message)
        }).finally(() => {
            setLoading(false)
            setTimeout(() => {
                setError("")
            }, 3000)
        }
        )
    }

    if (loading) {
        return (
            <div style={ {
                overflow: "hidden",
            } }>
                <LinearProgress />
            </div>)
    }

    return (
        <div>
            <div style={ {
                display: "flex",
                position: "relative",
                marginLeft: "80%",
                justifyContent: "flex-end",
                width: "20%"
            } }>
                <NavLink to="/console"
                    style={ {
                        color: "white",
                        textDecoration: "none",
                        padding: "10px 20px 10px 0",
                        cursor: "pointer",
                    } }
                    onMouseEnter={ (e) => (e.target as HTMLElement).style.color = "#ffff00" }
                    onMouseLeave={ (e) => (e.target as HTMLElement).style.color = "white" }
                >
                    <div className="console-text">
                        Search console
                    </div>
                </NavLink>
            </div>
            <div
                style={ {
                    margin: "3%",
                    marginLeft: "4%",
                    marginRight: "4%",
                    width: "90%"
                } }>
                <SearchBar placeholder="Search Seekster" searchTerms={ searchTerm } fetchData={ fetchSearchResult } />
                { !loading && <p>Loading...</p> }
                { loading && !searchResult.success && <p>No results found.</p> }


            </div>
            { searchResult.data.message === false && !loading && <ErrorCard message={ error } /> }
            { !loading &&
                searchResult.success === true &&
                searchResult.data.result.map((result: { id: React.Key | null | undefined; url: string; title: string; content: string }) => (
                    <ResultCard
                        key={ result.id || result.url }
                        url={ result.url }
                        title={ result.title }
                        description={ result.content }
                    />
                )) }
        </div>
    )
}

export default SearchResultScreen