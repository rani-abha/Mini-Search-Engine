import React from "react"
import { Link } from "react-router-dom"
import SearchIcon from "@mui/icons-material/Search"



interface SearchBarProps {
    placeholder: string,
    searchTerms?: string,
    fetchData?: () => Promise<void>,
}

const SearchBar: React.FC<SearchBarProps> = ({ placeholder, searchTerms, fetchData }) => {
    const [searchTerm, setSearchTerm] = React.useState<string>(searchTerms || "")

    console.log("searchTerm", searchTerm)

    React.useEffect(() => {
        setSearchTerm(searchTerms || "")
    }, [searchTerms])
    return (
        <div
            style={ {
                color: "black",
                backgroundColor: "white",
                height: "40 px",
                borderRadius: "20px",
                justifyContent: "center",
                alignItems: "center",
                padding: "1px",
            } }>
            <div
                style={ {
                    padding: "7px 3px 4px",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                    marginInline: "auto",
                    marginLeft: 12,
                } }>
                <SearchIcon />
                <input
                    style={ {
                        border: "none",
                        outline: "none",
                        background: "none",
                        width: "100%",
                        fontSize: "18px",
                        color: "black",
                        padding: "4px 5px",
                        textAlign: "justify"

                    } }
                    type="text"
                    placeholder={ placeholder }
                    value={ searchTerm }
                    onChange={ (e) => setSearchTerm(e.target.value) }
                />
                <Link
                    style={ {
                        padding: 2,
                        justifyContent: "center",
                        display: "flex",
                        transition: "background-color 0.3s ease",
                        width: "10%",
                        fontWeight: 300,
                    } }
                    to={ searchTerm ? "/search" : "#" }
                    state={ { searchTerm } }
                    onClick={ (e) => !searchTerm && e.preventDefault() }
                >
                    <button
                        style={ {
                            height: 30,
                            width: 60,
                            background: "none",
                            border: "none",
                            backgroundColor: "white",
                            borderRadius: "30%",
                            cursor: "pointer",
                            fontSize: "16px",
                            marginLeft: "200%"
                        } }

                        onClick={ () => { fetchData && fetchData() } }
                    >
                        Search
                    </button>
                </Link>
            </div>
        </div>
    )
}

export default SearchBar