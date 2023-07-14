import {Component} from '@angular/core';
import axios, {AxiosResponse} from 'axios';
import {style} from "@angular/animations";

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
  showImage: boolean = false;
  constructor() {
    // Cấu hình url
    axios.defaults.baseURL = 'http://localhost:8080/api/data';
    // thiet lap interceptor de xu ly thang cong va loi
    axios.interceptors.response.use(this.handleSuccessResponse, this.handleErrorResponse);
  }

  convertData() {
    // kiem tra data co rong hay k,neu rong thi xoa kq,neu k thi cap nhat lai convert
    if (!this.data) {
      this.result = "";
      return;
    }
    axios.post<ConvertResponse>(`${this.from}/${this.to}`, {data: this.data})
      .then((response: AxiosResponse<ConvertResponse>) => {
        this.result = response.data.result;
      })
      .catch((error: any) => {
        if (error.response && error.response.data && error.response.data.message) {
          this.result = error.response.data.message;
        } else {
          this.result = "Unknown Error";
        }
        console.error(error);
      });
  }

  reverseConversion() {
    // luu gia tri ban dau
    const initValue = this.from;
    const initData = this.data;
    // // gan gia tri cua  'to' cho 'from'
    this.from = this.to;
    // // gan gia tri ban ban dau cua 'from' cho 'to'
    this.to = initValue;
    // gan gia tri hien tai vao
    this.data = this.result;
    //Reset lai gia tri
    this.result = '';
    //  gan gia tri ban dau cua data cho thang result
    this.result = initData;
  }

  // xu ly khi thanh cong
  private handleSuccessResponse(response: AxiosResponse<ConvertResponse>): AxiosResponse<ConvertResponse> {
    return response;
  }
  // xu ly khi loi
  private handleErrorResponse(error: any): Promise<never> {
    console.error(error);
    return Promise.reject(error);
  }
}
