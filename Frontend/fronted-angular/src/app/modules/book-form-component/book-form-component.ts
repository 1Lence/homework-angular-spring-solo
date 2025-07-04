import {Component, inject} from '@angular/core';
import {AsyncPipe} from '@angular/common';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  TuiAppearance,
  TuiButton,
  TuiError,
  TuiLabel,
  TuiTextfieldComponent,
  TuiTextfieldDirective, TuiTitle
} from '@taiga-ui/core';
import {TuiCardLarge, TuiForm, TuiHeader} from '@taiga-ui/layout';
import {TuiFieldErrorPipe, TuiRadioList} from '@taiga-ui/kit';
import {Status} from '../../interfaces/status';
import {DateInput} from '../date-input/date-input';
import {TuiDay} from '@taiga-ui/cdk';
import {BookCreateDto} from '../../interfaces/bookCreateDto';
import {BookService} from '../../services/book-service';

@Component({
  selector: 'app-book-form-component',
  imports: [
    AsyncPipe,
    ReactiveFormsModule,
    TuiAppearance,
    TuiButton,
    TuiCardLarge,
    TuiError,
    TuiFieldErrorPipe,
    TuiForm,
    TuiHeader,
    TuiLabel,
    TuiTextfieldComponent,
    TuiTextfieldDirective,
    TuiTitle,
    FormsModule,
    TuiRadioList,
    DateInput
  ],
  templateUrl: './book-form-component.html',
  styleUrl: './book-form-component.scss',
})
export class BookFormComponent {
  protected readonly form = new FormGroup({
    title: new FormControl('', Validators.required),
    author: new FormControl('', Validators.required),
    isbn: new FormControl('', Validators.required),
    publishedDate: new FormControl<TuiDay | null>(null, Validators.required),
    status: new FormControl(Status.AVAILABLE, Validators.required),
    subscribe: new FormControl(false),
    basic: new FormControl(true),
  });
  protected readonly statusOptions = Object.values(Status);
  protected selectedStatus: Status = Status.AVAILABLE;

  private readonly bookService = inject(BookService)

  protected onSubmit(): void {
    if (this.form.valid) {
      const bookData : BookCreateDto = {
        title: this.form.value.title ?? '',
        author: this.form.value.author ?? '',
        isbn: this.form.value.isbn ?? '',
        publishedDate: this.form.value.publishedDate?.toLocalNativeDate() ?? new Date(),
        status: this.form.value.status ?? Status.AVAILABLE
      };
      this.bookService.create(bookData)
      console.log('BookCreateDto data:', bookData);
      this.form.reset();
    }
  }
  protected onCancel(): void {
    this.form.reset();
    this.selectedStatus = Status.AVAILABLE;
  }
}
