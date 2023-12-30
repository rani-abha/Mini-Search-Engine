import React from "react"

interface ResultCardProps {
    title: string,
    description: string,
    url: string,
}

const MAX_DESCRIPTION_LENGTH: number = 250

const truncateText = (text: string, limit: number) => {
    if (text.length <= limit) {
        return text
    }
    return text.slice(0, limit) + "..."
}

const ResultCard: React.FC<ResultCardProps> = ({ url, title, description }) => {
    const truncatedDescription = truncateText(description, MAX_DESCRIPTION_LENGTH)

    return (
        <div
            style={ {
                backgroundColor: "rgb(255, 255, 255, 0.8)",
                borderRadius: "10px",
                width: "60%",
                padding: "10px",
                margin: "10px auto",
                boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
                minHeight: "150px",
            } }
        >
            <div
                style={ {
                    marginLeft: "5%",
                    display: "flex",
                    flexDirection: "column"
                } }>
                <p
                    style={ {
                        fontWeight: "bold",
                        color: "#1a0dab",
                        fontSize: "15px",
                        cursor: "pointer",
                    } }
                >
                    { title }
                </p>
                <p
                    style={ {
                        color: "grey",
                        marginTop: -20,
                        fontSize: "13px",
                        cursor: "pointer",
                    } }>
                    { url }
                </p>
            </div>
            <div
                style={ {
                    marginLeft: "1.5%",
                    marginTop: "-3%",
                } }>
                <p
                    style={ {
                        fontSize: "20px",
                        fontWeight: "bold",
                        textDecoration: "underline",
                        cursor: "pointer",
                    } }>
                    { title }
                </p>
                <p style={ {
                    marginTop: "-1%",
                    fontSize: "15px",
                    lineHeight: "20px",
                    height: "40px",
                } }>
                    { truncatedDescription }

                </p>
            </div>
        </div>
    )
}

export default ResultCard