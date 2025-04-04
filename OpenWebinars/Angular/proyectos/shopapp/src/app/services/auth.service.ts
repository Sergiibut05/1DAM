import { inject, Injectable, signal } from "@angular/core";
import { Auth, createUserWithEmailAndPassword, GoogleAuthProvider, signInWithEmailAndPassword, signInWithPopup, signOut, updateProfile, User, user } from "@angular/fire/auth";
import { from, Observable } from "rxjs";
import { UserInterface } from "../user.interface";
import { doc, Firestore, setDoc} from "@angular/fire/firestore";

@Injectable({
    providedIn: 'root'
})

export class AuthService {
    firebaseAuth = inject(Auth)
    db = inject(Firestore)
    user$ = user(this.firebaseAuth)
    currentUserSig = signal<UserInterface | null | undefined>(undefined)


    register(
        email:string,
        username:string,
        password:string,
    ): Observable<void> {
        const promise = createUserWithEmailAndPassword(
            this.firebaseAuth,
            email,
            password
        ).then(async (response) => 
            await this.saveUserToFirestore(response.user)
            )

        return from(promise)
    }

    login(email:string, password:string): Observable<void> {
        const promise = signInWithEmailAndPassword(
            this.firebaseAuth,
            email,
            password,
        ).then(async (result) => {
            const user = result.user
            await this.saveUserToFirestore(user);
        });

        return from(promise)
    }

    logout(): Observable<void> {
        const promise = signOut(this.firebaseAuth);
        return from(promise);
    }

    loginWithGoogle(): Observable<void> {
        const provider = new GoogleAuthProvider();
        const promise = signInWithPopup(this.firebaseAuth, provider).then(async (result) => {
            const user = result.user;
            await this.saveUserToFirestore(user);
        });
        return from(promise);
    }

    private async saveUserToFirestore(user: User) {
        const userDocRef = doc(this.db, 'users', user.uid);
        const userData = {
            email: user.email,
            displayName: user.displayName,
            photoURL: user.photoURL
        };

        try {
            await setDoc(userDocRef, userData, { merge: true });
            console.log('Información del usuario guardada en Firestore');
        } catch (error) {
            console.error('Error al guardar la información del usuario en Firestore:', error);
        }
    }
}