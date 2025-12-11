import { TuiRoot } from "@taiga-ui/core";
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {UserFormComponent} from './modules/user-form-component/user-form-component';
import {BookFormComponent} from './modules/book-form-component/book-form-component';
import {BookListComponent} from './modules/book-list-component/book-list-component';
import { FileUploadComponentComponent } from './modules/file-upload-component/file-upload-component'; // Убедитесь, что путь к файлу правильный

@Component({
  selector: 'app-root',
  // FileUploadComponentComponent теперь standalone и может быть импортирован здесь
  imports: [RouterOutlet, TuiRoot, UserFormComponent, BookFormComponent, BookListComponent, FileUploadComponentComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'fronted-angular';
}
