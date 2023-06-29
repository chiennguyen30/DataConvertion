import { Component } from '@angular/core';
import axios, { AxiosResponse } from 'axios';

interface ConvertResponse {
  result: string;
}

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent {
  from: string = 'String';
  to: string = 'Base64';
  data: string = '';
  result: string = '';

  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080/api/data';
    axios.interceptors.response.use(this.handleSuccessResponse, this.handleErrorResponse);
  }

  convertData() {
    axios.post<ConvertResponse>(`${this.from}/${this.to}`, { data: this.data })
      .then((response: AxiosResponse<ConvertResponse>) => {
        this.result = response.data.result;
      })
      .catch((error: any) => {
        console.error(error);
      });
  }

  private handleSuccessResponse(response: AxiosResponse<ConvertResponse>): AxiosResponse<ConvertResponse> {
    return response;
  }

  private handleErrorResponse(error: any): Promise<never> {
    console.error(error);
    return Promise.reject(error);
  }
}
