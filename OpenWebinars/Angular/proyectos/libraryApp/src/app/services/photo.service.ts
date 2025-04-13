import { inject, Injectable } from '@angular/core';
import { Camera, CameraResultType, CameraSource, Photo } from '@capacitor/camera';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { Preferences } from '@capacitor/preferences';
import { firstValueFrom, Observable } from 'rxjs';
import { Platform } from '@ionic/angular/common';
import { FireStorageService } from './fire-storage.service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  plataform = inject(Platform);
  private auth = inject(AuthService);
  photoURL$: string = '';

  constructor(
    public storage: FireStorageService
  ) {

  }


  public async makeNewProfilePhoto(): Promise<string[] | undefined>{
    try{
      if(this.plataform.is('hybrid')){
        const image = await Camera.getPhoto({
          resultType: CameraResultType.DataUrl,
          source: CameraSource.Camera,
          quality: 90,
          allowEditing: false,
        });
        if(image.dataUrl){
          const blob = this.fromDataUrlToBlob(image.dataUrl)
          const photoUrl = await firstValueFrom(this.storage.upload(blob));
          this.auth.updatePhotoUrl(photoUrl[0]);
          return photoUrl;
        }
      }else{
        const image = await Camera.getPhoto({
          quality: 90,
          allowEditing: false,
          resultType: CameraResultType.DataUrl,
          source: CameraSource.Camera,
          webUseInput: false // Esto fuerza el uso de la cámara web
        });
        console.log('fotito: ,', image)
        if(image.dataUrl){
          const blob = this.fromDataUrlToBlob(image.dataUrl);
          console.log(blob)
          const photoUrl = await firstValueFrom(this.storage.upload(blob));
          this.auth.updatePhotoUrl(photoUrl[0]);
          return photoUrl;
        }
      }
      
      return undefined;
    }catch(error){
      console.error('Error al tomar la foto: ',error)
      return undefined;
    }

  }
  // Método para Pasar de DataUrl a Blob (suponiendo que el mime sea 'image/png')
  public fromDataUrlToBlob(dataUrl: string): Blob {
    try {
      // Verifica si la Data URL tiene el formato correcto
      const regex = /^data:(image\/(png|jpeg));base64,/;
      const matches = dataUrl.match(regex);
  
      if (!matches) {
        throw new Error("La Data URL no tiene un formato de imagen válido.");
      }
  
      // Extraer los datos base64 del Data URL
      const base64Data = dataUrl.split(',')[1];  // Divide la Data URL por la coma y toma la segunda parte
      const byteCharacters = atob(base64Data);  // Decodificar base64 en un string de bytes
  
      // Crear un arreglo de bytes para construir el Blob
      const byteArrays = new Uint8Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteArrays[i] = byteCharacters.charCodeAt(i);  // Convertir cada carácter en el byte correspondiente
      }
  
      // Crear un Blob con el arreglo de bytes y el tipo MIME
      const mimeType = matches[1];  // "image/png" o "image/jpeg"
      const blob = new Blob([byteArrays], { type: mimeType });
  
      console.log('Blob creado correctamente:', blob);  // Verificar el Blob
  
      return blob;
    } catch (error) {
      console.error('Error al convertir Data URL a Blob:', error);
      return new Blob();  // Retornar un Blob vacío en caso de error
    }
  }
  
  
}
