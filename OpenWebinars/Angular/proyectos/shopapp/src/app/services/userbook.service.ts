import { inject, Injectable} from "@angular/core";
import { firstValueFrom, map,Observable} from "rxjs";
import {Firestore, addDoc, collection, collectionData, deleteDoc, doc} from "@angular/fire/firestore";
import { Book } from "../components/Book.interface";
import { BookCRUD } from "./books.service";
import { UserBook } from "../components/userBook.interface";

@Injectable({
    providedIn: 'root'
})

export class UserBookService {
    db = inject(Firestore);
    bookservice = inject(BookCRUD);

    async add(email: string, bookId: string): Promise<void> {
        const tosend = {email, bookId}
        const booksCollection = collection(this.db, 'userBook');
        try {
            const docRef = await addDoc(booksCollection, tosend);
            console.log('Book added in user account');
        } catch (error) {
            console.error('Error adding book:', error);
            throw error;
        }
    }

    deleteByBookId(bookid: string) {
        this.getAll().pipe(
            map(references => references
                .filter(book => book.bookId === bookid)
                .map(reference => reference.id)
            )
        ).subscribe(Uids => {
            if (!Uids.length) {
                console.warn("No se encontraron libros con ese ID");
                return;
            }
    
            Uids.forEach(Uid => {
                if (Uid) { 
                    const booksDocRef = doc(this.db, 'userBook', Uid);
                    deleteDoc(booksDocRef).then(() => {
                        console.log('User Book deleted');
                    }).catch(error => {
                        console.error('Error deleting book:', error);
                    });
                } else {
                    console.error("ID inválido:", Uid);
                }
            });
        });
    }

    getAll(): Observable<UserBook[]>{
        const booksCollection = collection(this.db, 'userBook');
        return collectionData(booksCollection, {idField: 'id'}) as Observable<UserBook[]>;
    }

    getAllBooksReferencesByUser (email: string): Observable<string[]> {
        return this.getAll().pipe(
            map((references) => {
                return references.filter((book) => book.email === email).map((book) => book.bookId);
            })
        );
    }

    async getUserUidByBookId(bookId: string): Promise<string> {
        try {
            const references = await firstValueFrom(this.getAll().pipe(
                map(references => references.filter(book => book.bookId === bookId))
            ));
    
            if (!references || references.length === 0) {
                console.warn("No se encontraron libros con ese ID");
                return '';
            }
    
            return references[0].email;
        } catch (error) {
            console.error("Error obteniendo UID del usuario:", error);
            return '';
        }
    }
    
    
}