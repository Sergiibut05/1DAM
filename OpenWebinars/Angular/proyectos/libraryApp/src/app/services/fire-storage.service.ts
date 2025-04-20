import { Injectable } from '@angular/core';
import { Storage, ref, uploadBytesResumable, getDownloadURL, StorageReference, uploadBytes, getStorage, deleteObject } from '@angular/fire/storage';
import { inject } from '@angular/core';
import { from, map, Observable, switchMap } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FireStorageService {
  storage: Storage = inject(Storage);
  auth = inject(AuthService);

  public upload(blob: Blob): Observable<string[]> {
    return from(this.auth.user$).pipe(
      switchMap(user => {
        if (!user) throw new Error('Usuario no autenticado');
        
        const fileName = `${Date.now()}_${Math.random().toString(36).substring(2)}`;
        const storageRef: StorageReference = ref(this.storage, `uploads/${fileName}`);

        const metadata = {
          contentType: blob.type,
          customMetadata: {
            'uploaded-by': user.uid || 'anonymous'
          }
        };
        console.log('metadata: , ',metadata)
        return from(uploadBytes(storageRef, blob, metadata)).pipe(
          switchMap(snapshot => getDownloadURL(snapshot.ref)),
          map(url => [url])
        );
      })
    );
  }
  //Devuelve el path de firestore de un archivo en base a su URL
  public getStoragePathFromUrl(url: string): string {
    const decodedUrl = decodeURIComponent(url); // convierte %2F a /
    const startIndex = decodedUrl.indexOf('/o/') + 3; // salta "/o/"
    const endIndex = decodedUrl.indexOf('?');
    const path = decodedUrl.substring(startIndex, endIndex); // por ejemplo: uploads/foto123.jpg
    return path;
  }

  deleteFileFromUrl(url: string) {
    const storage = getStorage(); // Inicializa el storage
    const fileRef = ref(storage, this.getStoragePathFromUrl(url)); 
    deleteObject(fileRef).then(() => {
      console.log('Archivo eliminado con éxito');
    }).catch((error) => {
      console.error('Error al eliminar el archivo o la Url no es de Fire Storage:', error);
    });
  }
}
