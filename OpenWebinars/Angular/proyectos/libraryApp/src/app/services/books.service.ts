import { inject, Injectable, signal } from "@angular/core";
import { Observable } from "rxjs";
import {Firestore, addDoc, collection, collectionData, deleteDoc, doc, docData, setDoc, updateDoc} from "@angular/fire/firestore";
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
        const userDoc = doc(this.db, `books/${bookId}`);
        return docData(userDoc, { idField: 'id' }) as Observable<Book>; 
        
    }
    
}