import { TuiRoot } from "@taiga-ui/core";
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {UserFormComponent} from './modules/user-form-component/user-form-component';
import {BookFormComponent} from './modules/book-form-component/book-form-component';
import {BookListComponent} from './modules/book-list-component/book-list-component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TuiRoot, UserFormComponent, BookFormComponent, BookListComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'fronted-angular';
}
