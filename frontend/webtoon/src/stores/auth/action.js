import request from '../../commons/lib/request';
import { UPDATE_AUTH } from '../constants';

export const fetchLogin = (token, refreshToken) => async (dispatch) => {
  try {
    const {data: {code, data}} = await request.post('/user/kakao/profile', { token: token, refresh_token: refreshToken });
    console.log(data);
    if(code === '200') {
      await dispatch(updateState({
        user_id: data.userId,
        user_name: data.username
      }));
    }
  } catch(error) {
    console.log(error);
  }
};

export const userSignUp = (userInfo) => async () => {
  try {
    const res = await request.post('/user/signup', userInfo);
    console.log(res);
    return res.data;
  } catch (error) {
    console.error(error);
  }
};

export const updateState = data => ({type: UPDATE_AUTH, data});