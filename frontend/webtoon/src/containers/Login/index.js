import React, {Component} from 'react';
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import KakaoLogin from 'react-kakao-login';
import config from '../../config/OAuthConfig';
import kakaoBtn from '../../commons/images/kakao-login.png';
import {fetchLogin, updateState} from "../../stores/auth/action";
import ModalWrapper from '../../components/ModalWrapper';

@withRouter
@connect(state => ({auth: state.auth}))
class Login extends Component {
  constructor() {
    super(...arguments);
    this.state = {
      userId: '',
      provider: '',
      openModal: false
    };
  }

  responseKakao = ({response}) => {
    console.log(response);

    this.props.dispatch(fetchLogin()).then(response => {
      const data = response.data;
      if (data.code !== '200') {
        window.alert('로그인 실패!');
        window.location.href = '/login';
      } else {
        if (data.data.username === '') {
          this.setState({
            ...this.state,
            userId: data.data.userId,
            openModal: true
          });
        } else {
          this.props.dispatch(updateState({
            isAuthenticated: true,
            token: response.access_token
          }));
          window.location = '/';
        }
      }
    });
  };

  responseFail = (error) => {
    console.error(error);
  };

  render() {
    return (
        <div>
          <KakaoLogin jsKey={config.KAKAO_JS_KEY} className="social-login-btn" onSuccess={this.responseKakao}
                      onFailure={this.responseFail}><img src={kakaoBtn} value="kakao" alt="kakaoBtn"/></KakaoLogin>
          <ModalWrapper token={this.state.token} userId={this.state.userId} modal={this.state.openModal} dispatch={this.props.dispatch}/>
        </div>
    )
  }
}

export default Login;