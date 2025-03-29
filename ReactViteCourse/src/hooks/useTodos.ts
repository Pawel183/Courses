import { useEffect, useState } from "react"
import { dummyData } from "../data/todos"
import { Todo } from "../types/todo"

export default function useTodos() {
  const [todos, setTodos] = useState(() => {
    const savedTodos = JSON.parse(localStorage.getItem("todos") || "[]")
    return savedTodos.length > 0 ? savedTodos : dummyData
  })

  useEffect(() => {
    localStorage.setItem("todos", JSON.stringify(todos))
  }, [todos])

  function setTodoCompleted(id: number, completed: boolean) {
    setTodos((prevTodos: Todo[]) =>
      prevTodos.map((todo: Todo) =>
        todo.id === id ? { ...todo, completed } : todo
      )
    )
  }

  function addTodo(title: string) {
    setTodos((prevTodos: Todo[]) => [
      { id: Date.now(), title, completed: false },
      ...prevTodos,
    ])
  }

  function deleteTodo(id: number) {
    setTodos((prevTodos: Todo[]) =>
      prevTodos.filter((todo: Todo) => todo.id !== id)
    )
  }

  function deleteAllCompleted() {
    setTodos((prevTodos: Todo[]) =>
      prevTodos.filter((todo: Todo) => !todo.completed)
    )
  }

  return {
    todos,
    setTodoCompleted,
    addTodo,
    deleteTodo,
    deleteAllCompleted,
  }
}
