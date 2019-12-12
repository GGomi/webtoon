import request from '../../commons/lib/request';
import { WEBTOON_LIST_REQUEST, UPDATE_WEBTOON_LIST_STATE } from '../constants';

export const fetchList = () => async dispatch => {
  await dispatch({type: WEBTOON_LIST_REQUEST});

  try {
    const {data: {code, data}} = await request.get('/webtoon/list');
    
    if(code === "200") {
      await dispatch({ type: WEBTOON_LIST_REQUEST + "_SUCCESS", data: data});
    } else {
      console.error(code);
    }

  } catch(error) {
    console.error(error);
  }
};

export const updateState = data => ({ type: UPDATE_WEBTOON_LIST_STATE, data });
