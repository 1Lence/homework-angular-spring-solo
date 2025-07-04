import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserCreateDto} from '../interfaces/userCreateDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly _httpClient = inject(HttpClient)
  private readonly _url = "http://localhost:8080/api/v1/user"


  create(dto: UserCreateDto){
    console.info("Отправка запроса на создание todo")
    return this._httpClient.post<UserCreateDto>(`${this._url}`, dto).subscribe({
      next: () => {
        console.log('Пользователь успешно создана');
      },
      error: (error) => {
        console.error('Ошибка при создании пользователя:', error);
      }
    });
  }

  // findAll(){
  //   return this._httpClient.get<DTO>(`${this._url}`)
  // }
}
