import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TuiTable} from '@taiga-ui/addon-table';
import {
  TuiButton, TuiDialog,
  TuiTitle, TuiHintDirective,
} from '@taiga-ui/core';
import {
  TuiRadioList,
  TuiStatus,
} from '@taiga-ui/kit';
import {TuiCell} from '@taiga-ui/layout';
import {Status} from '../../interfaces/status';
import {BookService} from '../../services/book-service';
import {rxResource} from '@angular/core/rxjs-interop';
import {TuiAutoFocus, TuiDay} from '@taiga-ui/cdk';
import {TuiInputModule} from '@taiga-ui/legacy';
import {DateInput} from '../date-input/date-input';
import {BookDtoWithIds} from '../../interfaces/book-dto-with-ids';

@Component({
  selector: 'app-book-list-component',
  imports: [
    FormsModule,
    TuiButton,
    TuiCell,
    TuiStatus,
    TuiTable,
    TuiTitle,
    ReactiveFormsModule,
    TuiDialog,
    TuiInputModule,
    TuiAutoFocus,
    TuiRadioList,
    DateInput,
  ],
  templateUrl: './book-list-component.html',
  styleUrl: './book-list-component.scss',
})
export class BookListComponent {
  protected readonly statusOptions = Object.values(Status);
  private readonly bookService = inject(BookService)

  protected readonly sizes = ['l', 'm', 's'] as const;

  protected size = this.sizes[0];

  books = rxResource({
    stream: () => this.bookService.findAll()
  })
  protected readonly Status = Status;

  onDelete(book: any) {
    this.bookService.delete(book).subscribe()
    console.log("Book to delete id: " + book.id)
  }

  protected bookForChange: BookDtoWithIds | null = null;

  showDialog(book: any): void {
    this.bookForChange = book;
    this.open = true;

    this.form.patchValue({
      title: book.title,
      author: book.author,
      isbn: book.isbn,
      publishedDate: book.publishedDate ? TuiDay.fromLocalNativeDate(new Date(book.publishedDate)) : null,
      status: book.status || Status.BORROWED,
      subscribe: book.subscribe || false,
      basic: book.basic || true
    });
  }

  protected form = new FormGroup({
    title: new FormControl(''),
    author: new FormControl(''),
    isbn: new FormControl(''),
    publishedDate: new FormControl<TuiDay | null>(null),
    status: new FormControl(Status.BORROWED),
    subscribe: new FormControl(false),
    basic: new FormControl(true),
  });

  protected open = false;

  save() {
    const bookData: BookDtoWithIds = {
        id: this.bookForChange!.id,
        title: this.form.value.title ?? this.bookForChange!.title,
        author: this.form.value.author ?? this.bookForChange!.author,
        isbn: this.form.value.isbn ?? this.bookForChange!.isbn,
        publishedDate: this.form.value.publishedDate?.toLocalNativeDate() ?? new Date(),
        status: this.form.value.status ?? Status.AVAILABLE,
      };

    this.bookService.update(bookData).subscribe()

    this.books = rxResource({
      stream: () => this.bookService.findAll()
    });

    console.log('BookDtoWithIds data:', bookData);
  }
}
