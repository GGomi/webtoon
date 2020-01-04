import request from '../../commons/lib/request';
import { WEBTOON_LIST_REQUEST, UPDATE_WEBTOON_LIST_STATE, LIKE_LIST_REQUEST } from '../constants';

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
export const fetchLikeList = () => async dispatch => {
  try {
    const {data: {code, data}} = await request.get('/likeToon');
    
    if(code === "200") {
      await dispatch({ type: `${LIKE_LIST_REQUEST}_SUCCESS`, data: data});
    } else {
      console.error(code);
    }
  } catch(error) {
    console.error(error);
  }
};

export const likeToon = (toonCode) => async dispatch => {
  try {
    const {data: {code, data}} = await request.post('/likeToon/like', {toonCode: toonCode});
    
    if(code === "200") {
      
    } else {
      console.error(code);
    }
  } catch(error) {
    console.error(error);
  }
};

export const unlikeToon = (toonCode) => async dispatch => {
  try {
    const {data: {code, data}} = await request.post('/likeToon/unlike', {toonCode: toonCode});
    
    if(code === "200") {
      
    } else {
      console.error(code);
    }
  } catch(error) {
    console.error(error);
  }
};

export const updateState = data => ({ type: UPDATE_WEBTOON_LIST_STATE, data });
