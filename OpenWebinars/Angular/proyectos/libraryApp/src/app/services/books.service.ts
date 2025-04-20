import { inject, Injectable, signal } from "@angular/core";
import { filter, from, map, Observable, take } from "rxjs";
import {DocumentReference, Firestore, addDoc, collection, collectionData, deleteDoc, doc, docData, getDoc, setDoc, updateDoc} from "@angular/fire/firestore";
import { Book } from "../interfaces/book.interface";

@Injectable({
    providedIn: 'root'
})

export class BookCRUD {
    db = inject(Firestore);

    getAll(): Observable<Book[]>{
        const booksCollection = collection(this.db, 'books');
        return collectionData(booksCollection, {idField: 'id'}) as Observable<Book[]>;
    }

    async add(book: Book): Promise<string> {
        const booksCollection = collection(this.db, 'books');
        try {
            const docRef = await addDoc(booksCollection, book);
            console.log('Book added');
            return docRef.id
        } catch (error) {
            console.error('Error adding book:', error);
            throw error;
        }
    }

    delete(bookid: string): Promise<void> {
        const booksDocRef = doc(this.db, 'books', bookid);
        return deleteDoc(booksDocRef).then(() => {
            console.log('Book deleted');
        }).catch((error) => {
            console.error('Error deleting book:', error);
        })
    }

    updateBook(updatedBook: any, bookId: string): Promise<void> {
        const booksDocRef = doc(this.db, 'books', bookId);
        return updateDoc(booksDocRef, updatedBook).then(() => {
            console.log('Book updated');
        }).catch((error) => {
            console.error('Error updating book:', error);
        })
    }

    getBookById(bookId: string): Observable<Book> {
        const userDocRef: DocumentReference<Book> = doc(this.db, `books/${bookId}`) as DocumentReference<Book>;
      
        return from(getDoc(userDocRef)).pipe(
          map(snapshot => {
            if (!snapshot.exists()) {
              throw new Error(`Libro ${bookId} no existe`);
            }
      
            return { id: snapshot.id, ...(snapshot.data() as Book) };
          })
        );
    }
    
}