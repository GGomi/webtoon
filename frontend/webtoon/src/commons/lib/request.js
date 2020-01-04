import axiosGlobal from 'axios';

let axios = axiosGlobal.create({
  baseURL: '/api/v1',
  timeout: 1000 * 10,
  headers: {
    'userId': '1',
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    'token': sessionStorage.getItem('token')
  },
});

export default axios;
