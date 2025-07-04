import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BookCreateDto} from '../interfaces/bookCreateDto';
import {BookDtoWithIds} from '../interfaces/book-dto-with-ids';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private readonly _httpClient = inject(HttpClient)
  private readonly _url = "http://localhost:8080/api/v1/books"

  //Пока не понял как сделать вывод обратного ответа нормально (не хочу копировать у ивана)
  create(dto: BookCreateDto){
    console.info("Отправка запроса на создание todo")
    return this._httpClient.post<BookCreateDto>(`${this._url}`, dto).subscribe({
      next: () => {
        console.log('Книга успешно создана');
      },
      error: (error) => {
        console.error('Ошибка при создании книги:', error);
      }
    });
  }

  findAll() {
    return this._httpClient.get<BookDtoWithIds[]>(`${this._url}`)
  }

  update(dto: BookDtoWithIds){
    return this._httpClient.put(`${this._url + '/' + dto.id}`, dto)
  }

  delete(dto: BookDtoWithIds){
    return this._httpClient.delete(`${this._url + '/' + dto.id}`)
  }
}
