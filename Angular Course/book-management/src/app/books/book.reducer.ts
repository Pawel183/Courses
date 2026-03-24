import {Book} from "../models/book";
import {createReducer, on} from "@ngrx/store";
import {AddBook, AddBookFailure, AddBookSuccess, RemoveBook} from "./book.actions";

export const initialState: Book[] = [];

export const BookReducer = createReducer(
  initialState,
  on(AddBook, (state) => {return state}),
  on(AddBookSuccess, (state, {id, title, author}) => [...state, {id, title, author}]),
  on(AddBookFailure, (state, {error}) => {
    console.error(error);
    return state;
  }),
  on(RemoveBook, (state, {bookId}) => state.filter(book => book.id !== bookId))
);
