import React from "react"
import "../styles/ConsoleCard.css"
import Card from "@mui/material/Card"
import CardActions from "@mui/material/CardActions"
import CardContent from "@mui/material/CardContent"
import Button from "@mui/material/Button"
import Typography from "@mui/material/Typography"
import { CardHeader, Box, TextField, LinearProgress } from "@mui/material"
import { saveWebsite, ApiResponse } from "../utils/API/APIService"

interface ConsoleScreenProps { }

interface Website {
    seed: string
}


const ConsoleScreen: React.FC<ConsoleScreenProps> = () => {
    const [website, setWebsite] = React.useState<Website>({
        seed: "https://www.example.com",
    })
    const [websiteResponse, setWebsiteResponse] = React.useState<ApiResponse<any>>({
        success: false,
        data: [],
    })
    const [loading, setLoading] = React.useState<boolean>(false)
    const [error, setError] = React.useState<string>("")


    const resetWebsite = () => {
        setWebsite({
            seed: "",
        })
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>, property: string) => {
        setWebsite({ ...website, [property]: event.target.value })
    }

    const createWebsite = (event: React.FormEvent<HTMLButtonElement>): Promise<void> => {
        event.preventDefault()
        setLoading(true)
        return saveWebsite(website).then((url) => {
            setWebsiteResponse(url)
            console.log("Website response", url)
            resetWebsite()
            setError(websiteResponse.data.message)
        }).catch((error) => {
            setError(error.message)
            console.log(error.message)
        }).finally(() => {
            setLoading(false)
            setTimeout(() => {
                setError("")
            }, 2000)
        })
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
        <div className="consoleCard">
            <Card sx={ { minWidth: 275, background: "#1B1C1D" } }>
                <CardHeader
                    sx={ { mb: 0, pb: 0 } }
                    title={
                        <Typography variant="h2" color="#CCC">
                            Welcome to Seekster Search Console
                        </Typography>
                    }
                />
                <CardHeader
                    sx={ { mt: 0, pt: 0 } }
                    title={
                        <Typography variant="h6" color="#CCC" align="center">
                            To Start Crawl, Submit Your Website Home Url.
                        </Typography>
                    }
                />
                <CardContent
                    sx={ {
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        justifyContent: "center",
                    } }
                >
                    <div className="icon">
                        <svg width="48" height="36" viewBox="0 0 48 36">
                            <g fill="none">
                                <rect x="17" width="14" height="8" fill="#BDBDBD"></rect>
                                <rect x="17" y="28" width="14" height="8" fill="#BDBDBD"></rect>
                                <rect x="23" y="8" width="2" height="20" fill="#BDBDBD"></rect>
                                <rect x="6" y="18" width="2" height="10" fill="#BDBDBD"></rect>
                                <rect x="6" y="18" width="36" height="2" fill="#BDBDBD"></rect>
                                <rect x="40" y="18" width="2" height="10" fill="#BDBDBD"></rect>
                                <rect x="34" y="28" width="14" height="8" fill="#BDBDBD"></rect>
                                <rect y="28" width="14" height="8" fill="#4285F4"></rect>
                            </g>
                        </svg>
                    </div>
                    <div className="list">
                        <li>Only URLs under entered address</li>
                        <li>Only URLs under specified protocol</li>
                    </div>
                    <div className="inputBox">
                        <Box
                            component="form"
                            sx={ {
                                "& > :not(style)": { m: 1, width: "25ch" },
                            } }
                            noValidate
                            autoComplete="off"
                        >
                            <TextField
                                id="standard-textarea"
                                label="Enter Url"
                                placeholder="https://www.example.com"
                                multiline
                                variant="standard"
                                value={ website.seed }
                                onChange={
                                    (event: React.ChangeEvent<HTMLInputElement>) => handleChange(event, "seed")
                                }
                                InputLabelProps={ { style: { color: "#CCC" } } }
                                inputProps={ { style: { color: "#CCC" } } }
                            />
                        </Box>
                    </div>
                </CardContent>
                <CardActions
                    sx={ {
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        justifyContent: "center",
                        mt: 0,
                        mb: 5,
                    } }
                >
                    <Button size="large" onClick={
                        createWebsite
                    }>
                        Continue
                    </Button>
                    <Button size="large" onClick={ () => window.history.back() }>
                        Back
                    </Button>
                    <div style={ { height: 20 } }>
                        { (websiteResponse.success === true || error) && (<p style={ {
                            color: "white",
                            fontSize: 15,
                        } }>
                            { withFading({ Faded: error, duration: 5, isOut: true }) }
                        </p>) }
                    </div>
                </CardActions>
            </Card>
        </div>
    )
}

export default ConsoleScreen



const withFading = ({ Faded, duration, isOut }: { Faded: any, duration: any, isOut: any }) => {
    const inEffect = `
    @keyframes react-fade-in {
      0%   { opacity: 0; }
      100% { opacity: 1; }
    }
  `

    const outEffect = `
    @keyframes react-fade-out {
      0%   { opacity: 1; }
      100% { opacity: 0; }
    }
  `

    return (
        <div>
            <style>
                { isOut ? outEffect : inEffect }
            </style>
            <div
                style={
                    {
                        animationDuration: `${duration}s`,
                        animationIterationCount: 1,
                        animationName: `react-fade-${isOut ? "out" : "in"}`,
                        animationTimingFunction: isOut ? "ease-out" : "ease-in", // Add a comma here
                    }
                }
            >
                { Faded }
            </div>
        </div>
    )
}
