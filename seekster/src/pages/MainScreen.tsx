import React from "react"
import SphereAnimation from "../components/SphereAnimation"
import "../styles/HomeScreen.css"
import SearchBar from "../components/SearchBar"
import ErrorCard from "../components/ErrorCard"
import { Link, NavLink } from "react-router-dom"
import ResultCard from "../components/ResultCard"

interface MainScreenProps {
}

const MainScreen: React.FC<MainScreenProps> = () => {
    const [scrolled, setScrolled] = React.useState<boolean>(false)
    const [searchInput, setSearchInput] = React.useState<string>("")

    const handleScroll = () => {
        const scrollY = window.scrollY
        if (scrollY > window.innerHeight / 1.3) {
            setScrolled(true)
        } else {
            setScrolled(false)
        }
    }

    console.log("input", searchInput)

    React.useEffect(() => {
        window.addEventListener("scroll", handleScroll)

        return () => {
            window.removeEventListener("scroll", handleScroll)
        }
    }, [])
    return (
        <div>
            <>
                <div className={ `App${scrolled ? " scrolled" : ""}` }>
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
                            color: "white",
                            fontSize: "120px",
                            fontWeight: "bold",
                            position: "absolute",
                            top: "35%",
                            width: "100%",
                            fontStretch: "extra-expanded",
                            textAlign: "center",
                            zIndex: 1000,
                        } }>SEEKSTER</div>
                    <div
                        style={ {
                            backgroundColor: "black",
                            marginTop: "10%",
                            flex: 1,
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center",
                            opacity: 1,
                        } }>
                        <SphereAnimation id="canvasID" style={ { border: "1px solid", zIndex: 50 } } />
                    </div>
                    <div
                        style={ {
                            marginTop: "-15%",
                            zIndex: 1001,
                            height: "400px",
                            justifyContent: "center",
                            background:
                                "linear-gradient(180deg, rgba(2, 0, 36, 0.0005252100840336116) 0%, rgba(0, 0, 10, 1) 30%)",
                        } }>
                        <div
                            style={ {
                                margin: "10%",
                                width: "50%",
                                marginLeft: "26.7%",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                                flexDirection: "column",
                            } }
                        >
                            <div style={ { width: "90%", marginRight: "5%" } }>
                                <SearchBar
                                    placeholder="Search..."
                                    searchTerms={ searchInput }
                                />
                            </div>

                        </div>
                    </div>
                </div>

                <div
                    style={ {
                        height: "100%",
                        width: "100%",
                        marginTop: -5,
                        marginBottom: 100,
                        bottom: 0,
                    } }
                >
                    <p style={ { color: "wheat", padding: 100, lineHeight: "2" } }>
                        <strong>
                            Introducing Seekster: Revolutionizing Your Search Experience
                        </strong>
                        <br />
                        <br />
                        In a digital age where information is abundant, Seekster reshapes
                        online searches to meet individual needs. Traditional search engines
                        fall short, flooding users with irrelevant content. Seekster addresses
                        this by providing a personalized, efficient, and user-controlled
                        search experience.
                        <br />
                        <br />
                        🌐 <strong>Changing User Expectations:</strong>
                        <br />
                        Empowering users with a say in their searches, Seekster offers a
                        personalized and focused approach, departing from standard search
                        engine conventions.
                        <br />
                        💼 <strong>Specialized Information Needs:</strong>
                        <br />
                        Professionals requiring specific information find relief in Seekster.
                        Unlike regular search engines, Seekster prioritizes and retrieves
                        content from specialized sources.
                        <br />
                        🎨 <strong>Customization and Control:</strong>
                        <br />
                        Seekster gives users the ability to customize searches by inputting
                        URLs, ensuring results are not only relevant but tailored to unique
                        preferences.
                        <br />
                        🚀 <strong>Enhanced Relevance and Efficiency:</strong>
                        <br />
                        No more sifting through irrelevant results. Seekster focus on
                        specific URLs guarantees precise and useful information, making
                        searches more efficient.
                        <br />
                        🌟 <strong>Objective of the New System:</strong>
                        <br />
                        Seekster aims to make online information discovery personalized and
                        user-friendly. Our objectives include creating personalized search
                        experiences, streamlining web crawling and indexing, improving search
                        precision, and boosting user engagement.
                        <br />
                        <br />
                        🚀 <strong>Core Components:</strong>
                        <br />
                        <br />
                        1️⃣ <strong>Web Crawler: </strong>
                        <br />
                        - Handles user-submitted URLs for crawling.
                        <br />
                        - Extracts relevant text and metadata using customizable parsing.
                        <br />
                        - Enables incremental crawling for timely updates.
                        <br />
                        - Utilizes RabbitMQ for seamless communication with the Indexing
                        Service.
                        <br />
                        <br />
                        2️⃣ <strong>Indexing Service:</strong>
                        <br />
                        - Maps extracted content to searchable terms for efficient storage.
                        <br />
                        - Processes user queries, retrieving relevant results.
                        <br />
                        - Adopts a scalable microservices architecture.
                        <br />
                        - Optimizes indexing using Lucene capabilities.
                        <br />
                        - Integrates with the Web Crawler via RabbitMQ for efficient
                        communication.
                        <br />
                        <br />
                        🌐 <strong>Why Seekster?</strong>
                        <br />
                        Traditional search engines overwhelm users with data, hindering
                        precise searches. Seekster puts users in control, allowing them to add
                        and prioritize specific websites. Bridging the gap between general
                        and personalized search engines, Seekster enhances the search
                        experience by catering to diverse user needs.
                        <br /> <br /> <br /> <br />
                        🌐 <strong>Join us on the journey to redefine your search experience
                            with Seekster!</strong>
                    </p>{ " " }
                    {/* <HelloAnimation /> */ }
                </div>
            </>
            {/* <SphereAnimation /> */ }
            {/* Add your component content here */ }
        </div >
    )
}

export default MainScreen
