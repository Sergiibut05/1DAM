import { Injectable } from '@angular/core';
import { Storage, ref, uploadBytesResumable, getDownloadURL, StorageReference, uploadBytes } from '@angular/fire/storage';
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
}
