import { Injectable } from "@angular/core";
import { Book } from "../interfaces/book.interface";

@Injectable({
  providedIn: 'root'
})
export class UserBookLikeService {

  private storageKey = 'userBookLikes'; // Clave para el localStorage

  constructor() {}

  // Método para agregar un libro al localStorage
  async add(book: Book): Promise<void> {
    const likedBooks = this.getAllBooksFromStorage(); // Recupera los libros existentes del localStorage

    // Verifica si el libro ya está en el localStorage
    if (!this.isBookInStorage(book.id!)) {
      likedBooks.push(book); // Si no está, lo agregamos a la lista
      localStorage.setItem(this.storageKey, JSON.stringify(likedBooks)); // Guardamos la lista de vuelta en el localStorage
      console.log('Libro agregado a los favoritos');
    } else {
      console.log('Este libro ya está en tus favoritos');
    }
  }

  // Método para obtener todos los libros guardados en el localStorage
  getAllBooksFromStorage(): Book[] {
    const storedBooks = localStorage.getItem(this.storageKey);
    return storedBooks ? JSON.parse(storedBooks) : []; // Si existen libros, los devuelve, si no, devuelve un array vacío
  }

  // Método para verificar si un libro está en el localStorage por su ID
  isBookInStorage(bookId: string): boolean {
    const likedBooks = this.getAllBooksFromStorage();
    return likedBooks.some(book => book.id === bookId); // Comprueba si el libro con ese ID está en los favoritos
  }

  // Método para obtener un libro específico del localStorage por su ID
  getBookById(bookId: string): Book | null {
    const likedBooks = this.getAllBooksFromStorage();
    const book = likedBooks.find(b => b.id === bookId); // Busca el libro por ID
    return book || null; // Devuelve el libro si lo encuentra, o null si no lo encuentra
  }

  // Método para eliminar un libro del localStorage por su ID
  async deleteByBookId(bookId: string): Promise<void> {
    let likedBooks = this.getAllBooksFromStorage(); // Recupera todos los libros del localStorage
    likedBooks = likedBooks.filter(book => book.id !== bookId); // Filtra el libro que deseas eliminar

    // Vuelve a guardar el array de libros actualizado en el localStorage
    localStorage.setItem(this.storageKey, JSON.stringify(likedBooks));
    console.log(`Libro con ID ${bookId} eliminado de los favoritos`);
  }

  // Método para eliminar todos los libros guardados en localStorage
  async deleteAllBooks(): Promise<void> {
    localStorage.removeItem(this.storageKey); // Elimina todos los libros del localStorage
    console.log('Todos los libros han sido eliminados de los favoritos');
  }
}
