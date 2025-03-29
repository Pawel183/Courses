import { Todo } from "../types/todo"
import TodoItem from "./TodoItem"

interface TodoListProps {
  todos: Todo[]
  onCompletedChange: (id: number, completed: boolean) => void
  onDelete: (id: number) => void
}

const TodoList = ({ todos, onCompletedChange, onDelete }: TodoListProps) => {
  const todosSorted = todos.sort(
    (a, b) => Number(a.completed) - Number(b.completed)
  )

  return (
    <>
      <div className="space-y-2">
        {todosSorted.map((todo: Todo) => (
          <TodoItem
            key={todo.id}
            todo={todo}
            onCompletedChange={onCompletedChange}
            onDelete={onDelete}
          />
        ))}
      </div>
      {todos.length === 0 && (
        <p className="text-center text-gray-500">No todos yet</p>
      )}
    </>
  )
}

export default TodoList
