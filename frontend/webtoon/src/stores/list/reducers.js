import { WEBTOON_LIST_REQUEST, UPDATE_WEBTOON_LIST_STATE } from '../constants';

const initialState = {
  error: null,
  loading: false,
  tab: 'NAVER',
  data: {}
}

export default (state = initialState, action) => {
  switch(action.type) {
    case WEBTOON_LIST_REQUEST:
      return { ...state, loading: true };
    case WEBTOON_LIST_REQUEST + '_SUCCESS':
      return { ...state, loading: false, data: action.data };
    case UPDATE_WEBTOON_LIST_STATE:
        return { ...state, ...action.data };
    default:
      return state;
  }
}