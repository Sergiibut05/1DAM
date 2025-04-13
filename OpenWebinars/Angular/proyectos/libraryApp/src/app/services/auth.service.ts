import { inject, Injectable, signal } from "@angular/core";
import { Auth, createUserWithEmailAndPassword, GoogleAuthProvider, signInWithEmailAndPassword, signInWithPopup, signOut, updateProfile, User, user } from "@angular/fire/auth";
import { from, Observable } from "rxjs";
import { UserInterface } from "../interfaces/user.interface";
import { collection, doc, DocumentData, Firestore, getDocs, query, setDoc, where} from "@angular/fire/firestore";

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

    async updatePhotoUrl(photoUrl: string): Promise<void> {
        try {
            
            const user = await this.getCurrentUser();
            if (!user) {
                throw new Error("No user is currently authenticated");
            }

            
            const userDocRef = doc(this.db, 'users', user.uid);
            const userData = {
                email: user.email,
                displayName: user.displayName,
                photoURL: photoUrl
            };

            
            await setDoc(userDocRef, userData, { merge: true });
            console.log('Foto del usuario actualizada en Firestore');
        } catch (error) {
            console.error('Error al actualizar la foto del usuario en Firestore:', error);
        }
    }

    
    private async getCurrentUser(): Promise<User | null> {
        return new Promise((resolve, reject) => {
            this.user$.subscribe(user => {
                if (user) {
                    resolve(user);
                } else {
                    reject("No user is authenticated");
                }
            });
        });
    }

    public async getCurrentFireStoreUser(): Promise<UserInterface | null> {
        const user = await this.getCurrentUser();
        if (!user) {
            console.log('No authenticated user found.');
            return null;  // Si no hay usuario autenticado, retornar null
        }
    
        // Consultar Firestore para obtener los detalles del usuario basado en el email
        const usersRef = collection(this.db, "users");
        const q = query(usersRef, where("email", "==", user.email));
    
        try {
            // Obtener los documentos de la consulta
            const querySnapshot = await getDocs(q);
            if (querySnapshot.empty) {
                console.log("No matching document found");
                return null;  // Si no se encuentra el documento, retornar null
            }
    
            const userDoc = querySnapshot.docs[0];  // Obtenemos el primer documento
    
            // Mapear los datos del Firestore a la interfaz UserInterface
            const userData: UserInterface = {
                email: userDoc.data()["email"],
                displayName: userDoc.data()["displayName"] || "",  // Puede ser vacío si no está presente
                photoURL: userDoc.data()["photoURL"] || ""  // Puede ser vacío si no está presente
            };
    
            return userData;  // Devolver los datos del usuario en formato UserInterface
    
        } catch (error) {
            console.error("Error getting document: ", error);
            return null;  // En caso de error, retornar null
        }
    }
    private async saveUserToFirestore(user: User) {
        const userDocRef = doc(this.db, 'users', user.uid);
        let foundUser: any;
        if(user.email){
            foundUser =  this.searchForUser(user.email);
        }
        const userDatav1 = {
            email: user.email,
            displayName: user.displayName,
            photoURL: user.photoURL
        };
        const userDatav2 = {
            email: user.email,
            displayName: user.displayName
        };
        try{
                // If the found user exists and the photo URL has changed, update it
            if (foundUser && foundUser['photoURL'] !== user.photoURL && foundUser['photoURL']) {
                await setDoc(userDocRef, userDatav2, { merge: true });
                console.log('Información del usuario guardada en Firestore');
            } else if (!foundUser) {
                // If the user is not found, proceed to create a new document
                await setDoc(userDocRef, userDatav1, { merge: true });
                console.log('Información del usuario guardada en Firestore');
            }
        }catch (error) {
            console.error('Error al guardar la información del usuario en Firestore:', error);
        }
    }

    private async searchForUser(email: string){
        const usersRef = collection(this.db, "users");
        const q = query(usersRef, where("email", "==", email));
        try {
            const querySnapshot = await getDocs(q);
            const userDoc = querySnapshot.docs[0]; 
        
            if (userDoc) {
              return userDoc.data(); // Return document data if found
            } else {
              console.log("No matching document found");
              return null; // Return null if no matching document is found
            }
          } catch (error) {
            console.error("Error getting document: ", error);
            return null;
          }

    }
}