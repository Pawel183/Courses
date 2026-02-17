import { Trash2 } from "lucide-react"
import { Todo } from "../types/todo"

interface TodoItemProps {
  todo: Todo
  onCompletedChange: (id: number, completed: boolean) => void
  onDelete: (id: number) => void
}

export default function TodoItem({
  todo,
  onCompletedChange,
  onDelete,
}: TodoItemProps) {
  return (
    <div className="flex items-center gap-1">
      <label className="flex grow items-center gap-2 border rounded-md p-2 border-gray-400 bg-white hover:bg-slate-50">
        <input
          type="checkbox"
          checked={todo.completed}
          onChange={(e) => onCompletedChange(todo.id, e.target.checked)}
          className="mr-2 scale-125"
        />
        <span
          className={`text-lg ${
            todo.completed ? "line-through text-gray-500" : ""
          }`}
        >
          {todo.title}
        </span>
      </label>
      <button onClick={() => onDelete(todo.id)} className="p-2">
        <Trash2 size={25} className="text-gray-500" />
      </button>
    </div>
  )
}
