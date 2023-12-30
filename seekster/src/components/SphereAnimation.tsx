import React, { useEffect, useRef } from "react"

const MAX = 50

function SphereAnimation(props: React.CanvasHTMLAttributes<HTMLCanvasElement>) {
    const canvasRef = useRef<HTMLCanvasElement>(null)
    const ctxRef = useRef<CanvasRenderingContext2D | null>(null)
    const points: [number, number, number][] = []
    let count = 0

    useEffect(() => {
        const canvas = canvasRef.current
        if (!canvas) return // Check if canvas is null

        const ctx = canvas.getContext("2d")
        if (!ctx) return // Check if ctx is null
        ctxRef.current = ctx
        canvas.width = canvas.height = 400
        ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height)

        // ctx.fillRect(0, 0, 400, 400);

        let r = 0
        for (let a = 0; a < MAX; a++) {
            points.push([Math.cos(r), Math.sin(r), 0])
            r += (Math.PI * 2) / MAX
        }

        for (let a = 0; a < MAX; a++) {
            points.push([0, points[a][0], points[a][1]])
        }

        for (let a = 0; a < MAX; a++) {
            points.push([points[a][1], 0, points[a][0]])
        }
        rus()
    }, [])

    function rus() {
        const ctx = ctxRef.current
        if (!ctx || !canvasRef.current) return // Check if ctx is null

        ctx.globalCompositeOperation = "source-over"
        ctx.fillStyle = "rgba(0,0,0,0.03)"
        ctx.fillRect(0, 0, canvasRef.current.width, canvasRef.current.height)
        ctx.globalCompositeOperation = "lighter"

        let tim = count / 5

        for (let e = 0; e < 3; e++) {
            tim *= 1.7
            let s = 1 - e / 3
            let a = tim / 59
            const yp = Math.cos(a)
            const yp2 = Math.sin(a)
            a = tim / 23
            const xp: number = Math.cos(a)
            const xp2 = Math.sin(a)
            const p2: [number, number, number][] = []

            for (let a = 0; a < points.length; a++) {
                let x = points[a][0]
                let y = points[a][1]
                let z = points[a][2]

                const y1 = y * yp + z * yp2
                const z1 = y * yp2 - z * yp
                const x1 = x * xp + z1 * xp2

                z = x * xp2 - z1 * xp
                const z1val = Math.pow(2, z * s)
                x = x1 * z1val
                y = y1 * z1val
                p2.push([x, y, z])
            }

            s *= 120
            for (let d = 0; d < 3; d++) {
                for (let a = 0; a < MAX; a++) {
                    const b = p2[d * MAX + a]
                    const c = p2[((a + 1) % MAX) + d * MAX]
                    ctx.beginPath()
                    ctx.strokeStyle =
                        "hsla(" + (((a / MAX) * 360) | 0) + ",70%,60%,0.15)"
                    ctx.lineWidth = Math.pow(6, b[2])
                    ctx.lineTo(b[0] * s + 200, b[1] * s + 200)
                    ctx.lineTo(c[0] * s + 200, c[1] * s + 200)
                    ctx.stroke()
                }
            }
        }
        count++
        requestAnimationFrame(rus)
    }

    return <canvas ref={ canvasRef } { ...props } />
}

export default SphereAnimation
