import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {TuiButton, TuiCalendar, TuiLabel, TuiTextfieldComponent, TuiTextfieldDropdownDirective} from '@taiga-ui/core';
import {TuiInputDateDirective} from '@taiga-ui/kit';
import {TuiDay} from '@taiga-ui/cdk';

@Component({
  selector: 'app-date-input',
  imports: [
    ReactiveFormsModule,
    TuiButton,
    TuiCalendar,
    TuiInputDateDirective,
    TuiLabel,
    TuiTextfieldComponent,
    TuiTextfieldDropdownDirective
  ],
  templateUrl: './date-input.html',
  styleUrl: './date-input.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DateInput {
  @Input() control!: FormControl<TuiDay | null>;
  protected readonly today = TuiDay.currentLocal();
}
