import { combineReducers } from "redux";
import auth from './auth/reducers';
import list from './list/reducers';

export default combineReducers({
  auth,
  list
});
