import { useState } from "react"
import "./App.css"

function App() {
  const [isLogin, setIsLogin] = useState(false)
  return (
    <div>
      <button onClick={() => setIsLogin(!isLogin)}>
        <h1>{isLogin ? "Hello" : "Hello, please login."}</h1>
      </button>
    </div>
  )
}

export default App
