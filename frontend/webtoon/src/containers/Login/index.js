import React, {Component} from 'react';
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import KakaoLogin from 'react-kakao-login';
import config from '../../config/OAuthConfig';
import kakaoBtn from '../../commons/images/kakao-login.png';
import {fetchLogin} from "../../stores/auth/action";
import ModalWrapper from '../../components/ModalWrapper';
import swal from 'sweetalert';

@withRouter
@connect(state => ({auth: state.auth}))
class Login extends Component {
  constructor() {
    super(...arguments);
    this.state = {
      user_id: '',
      provider: '',
      openModal: false
    };

    if (localStorage.getItem('auth')) {
      swal({icon: 'error', text: '이미 로그인되어있습니다.'}).then(() => {
        window.location.href = '/';
      });

    }
  }

  responseKakao = async ({response}) => {
    console.log(response);
    sessionStorage.setItem('token', response.access_token);
    await this.props.dispatch(fetchLogin(response.access_token, response.refresh_token));

    if (this.props.auth.user_name === '') {
      this.setState({
        ...this.state,
        token: response.access_token,
        user_id: this.props.auth.user_id,
        openModal: true
      });
    } else {
      sessionStorage.setItem("auth", JSON.stringify({
        user_id: this.props.auth.user_id,
        user_name: this.props.auth.user_name,
        token: response.access_token
      }));

      window.location.href = '/';
    }
  };


  responseFail = (error) => {
    console.error(error);
  };

  render() {
    return (
        <div>
          <KakaoLogin jsKey={config.KAKAO_JS_KEY} className="social-login-btn" onSuccess={this.responseKakao}
                      onFailure={this.responseFail}><img src={kakaoBtn} value="kakao" alt="kakaoBtn"/></KakaoLogin>
          <ModalWrapper userId={this.state.user_id} modal={this.state.openModal} token={this.state.token}
                        dispatch={this.props.dispatch}/>
        </div>
    )
  }
}

export default Login;