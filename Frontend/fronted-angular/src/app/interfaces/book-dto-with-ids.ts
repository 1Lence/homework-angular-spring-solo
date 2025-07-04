import {Status} from './status';

export interface BookDtoWithIds {
  id: number,
  title: string,
  author: string,
  isbn: string,
  publishedDate: Date,
  status: Status
}
