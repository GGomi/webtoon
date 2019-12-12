import request from '../../commons/lib/request';
import { LOGIN_REQUEST, UPDATE_AUTH } from '../constants';

export const fetchLogin = () => async (dispatch, getState) => {
  try {
    const {token} = getState().auth;
    await dispatch({type: LOGIN_REQUEST});
    const res = await request.post('/user/kakao/profile', { token: token });
    console.log(res);
    return res;

  } catch(error) {
    console.log(error);
  }
};

export const updateState = data => ({type: UPDATE_AUTH, data});