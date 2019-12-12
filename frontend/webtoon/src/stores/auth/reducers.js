import {LOGIN_REQUEST, UPDATE_AUTH} from '../constants';

const initialState = {
  isAuthenticated: false,
  token: '',
  error: null,
  user_id: null,
  user_name: '',
  user_type: null,
  user_role: '',
};

export default (state = initialState, action) => {
  switch (action.type) {
    case LOGIN_REQUEST:
      return {...state, ...action.data};
    case UPDATE_AUTH:
      return {...state, ...action.data};
    default:
      return state;
  }
}