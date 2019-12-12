import request from 'common/lib/request';
// import { encrypt, decrypt } from 'common/lib/utils';

const storage = window.sessionStorage;
// const secretKey = process.env.REACT_APP_SECRET_CODE || 'secretkey';

/**
 * 로그인 된 정보 스토리지에서 가져오기
 *
 * @return {Object}
 */
export const getAuth = () => {
  const rawData = storage.getItem('login-info');

  if (!rawData) {
    return {};
  }

  const info = JSON.parse(rawData);
  const [session, nonce] = info.t_id.split(':');

  // set request header
  request.defaults.headers.common['session'] = session;
  request.defaults.headers.common['nonce'] = nonce;
  request.defaults.headers.common['user_id'] = info.admin_id;

  // return Authorized Data
  return {
    auth: {
      isAuthenticated: true,
      pending: false,
      error: null,
      admin_id: info.admin_id,
      admin_name: info.admin_name,
      admin_type: info.admin_type,
      updated: info.updated,
    }
  }
}

/**
 * 로그인 된 정보 스토리지에 저장
 *
 * @param {Object}
 */
export const setAuth = info => {
  const data = JSON.stringify(info);
  const saveData = encrypt(data, secretKey);
  storage.setItem('admin-login-info', saveData);
}

/**
 * 로그인 된 정보 스토리지에서 제거
 *
 */
export const removeAuth = () => {
  storage.removeItem('admin-login-info');
  storage.removeItem('nonce');
}

/**
 * 잘못된 요청 방지를 위한 Request Count 값 조회
 *
 * @return {Number}
 */
export const getNonce = () => {
  const nonce = storage.getItem('nonce');
  return nonce ? Number(nonce) : 0;
}

/**
 * 잘못된 요청 방지를 위한 Request Count 값 저장
 *
 * @param {Number}
 */
export const setNonce = (nonce = 0) => {
  storage.setItem('nonce', nonce);
}
