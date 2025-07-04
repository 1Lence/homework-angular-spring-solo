import {Status} from './status';

export interface BookCreateDto {
  title: string,
  author: string,
  isbn: string,
  publishedDate: Date,
  status: Status
}
