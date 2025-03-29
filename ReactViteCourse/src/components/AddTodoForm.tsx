import { useState } from "react"

interface AddTodoFormProps {
  onAddTodo: (title: string) => void
}

export default function AddTodoForm({ onAddTodo }: AddTodoFormProps) {
  const [input, setInput] = useState("")

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault()

    if (input.trim() === "") return

    onAddTodo(input)
    setInput("")
  }

  return (
    <form className="flex">
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        type="text"
        placeholder="Add a new todo..."
        className="rounded-s-md grow border border-gray-400 p-2"
      />
      <button
        type="submit"
        onClick={handleSubmit}
        className="w-16 rounded-e-md bg-slate-900 text-white hover:bg-slate-800"
      >
        Add
      </button>
    </form>
  )
}
