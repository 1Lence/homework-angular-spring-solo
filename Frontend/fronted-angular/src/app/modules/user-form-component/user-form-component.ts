import {Component, inject} from '@angular/core';
import {AsyncPipe} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  TuiAppearance,
  TuiButton,
  TuiError,
  TuiLabel,
  TuiTextfieldComponent, TuiTextfieldDirective, TuiTitle
} from '@taiga-ui/core';
import {TuiCardLarge, TuiForm, TuiHeader} from '@taiga-ui/layout';
import {TuiFieldErrorPipe} from '@taiga-ui/kit';
import {UserCreateDto} from '../../interfaces/userCreateDto';
import {UserService} from '../../services/user-service';

@Component({
  selector: 'app-user-form-component',
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
  ],
  templateUrl: './user-form-component.html',
  styleUrl: './user-form-component.scss'
})
export class UserFormComponent {
  protected readonly form = new FormGroup({
    userName: new FormControl('', Validators.required),
    fullName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    subscribe: new FormControl(false),
    basic: new FormControl(true),
  });

  private readonly userService = inject(UserService)

  protected onSubmit(): void {
    if (this.form.valid) {
      const userData : UserCreateDto = {
        userName: this.form.value.userName ?? '',
        fullName: this.form.value.fullName ?? '',
        email: this.form.value.email ?? '',
      };
      this.userService.create(userData)
      console.log('UserCreateDto data:', userData);
      this.form.reset();
    }
  }

  protected onCancel(): void {
    this.form.reset();
  }

}
