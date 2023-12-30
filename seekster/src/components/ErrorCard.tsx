import React from "react"

interface ErrorCardProps {
    message?: string
}

const ErrorCard: React.FC<ErrorCardProps> = ({ message }) => {
    return (
        <div
            style={ {
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                height: "100vh",
                backgroundColor: "#f8d7da",
                color: "#721c24",
            } }>
            <p
                style={ {
                    fontSize: "3rem",
                    fontWeight: "bold",
                    textAlign: "center",
                } }>
                404
            </p>
            <p
                style={ {
                    fontSize: "1.5rem",
                    fontWeight: "bold",
                    textAlign: "center",
                } }>
                { message ? message : "Network Error" }
            </p>
        </div>
    )
}

export default ErrorCard