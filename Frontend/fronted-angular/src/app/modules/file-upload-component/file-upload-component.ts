import { Component } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload-component.html',
  styleUrls: ['./file-upload-component.scss'],
  standalone: true,
})
export class FileUploadComponentComponent {
  selectedFile: File | null = null;
  uploadProgress: number | null = null;
  message: string = '';

  constructor(private http: HttpClient) {}

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.message = '';
    }
  }

  uploadFile() {
    if (!this.selectedFile) {
      this.message = 'Пожалуйста, выберите файл.';
      console.warn(this.message);
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);
    const url = `http://localhost:8080/api/v1/object/${encodeURIComponent(this.selectedFile.name)}`;

    this.uploadProgress = 0;
    this.http.post(url, formData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe({
      next: (event) => {
        if (event.type === HttpEventType.UploadProgress && event.total) {
          this.uploadProgress = Math.round(100 * event.loaded / event.total);
        } else if (event.type === HttpEventType.Response) {
          console.log('Файл успешно загружен!', event.body);
          this.message = `Файл '${this.selectedFile?.name || 'unknown'}' успешно загружен.`;
          this.selectedFile = null;
          this.uploadProgress = null;
        }
      },
      error: (err) => {
        console.error('Ошибка при загрузке файла:', err);
        this.message = `Ошибка при загрузке файла: ${err.message || 'Неизвестная ошибка'}`;
        this.uploadProgress = null;
      }
    });
  }
}
