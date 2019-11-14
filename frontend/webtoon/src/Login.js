import React, { Component } from 'react';
import OAuthConfig from './config/OAuthConfig';
import kakaoBtn from './commons/images/kakao-login.png';
import KakaoLogin from 'react-kakao-login';
import ModalWrapper from './Layout/ModalWrapper';

class Login extends Component {
    constructor(){
        super(...arguments);
        this.state = {
            userId: '',
            token: '',
            provider: '',
            openModal: false
        };
    }

    responseKakao = (response) => {
        const res = response.response;
        console.log(res.access_token);
        // sessionStorage.setItem('token', res.access_token);
        this.setState({
            token: res.access_token,
            provider: 'kakao'
        });

        const accessToken = {"token" : res.access_token};

        fetch('/api/v1/user/kakao/profile', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(accessToken)
        }).then(res => res.json())
            .then(body => {
                const { data } = body;
                console.log(data);
                if (data.username === '') {
                    this.setState({
                        ...this.state,
                        userId: data.userId,
                        openModal: true
                    });
                } else {
                    const userData = {
                        'token': res.access_token,
                        'username': data.username
                    };
                    sessionStorage.setItem('userdata', userData);
                    
                    window.location = '/list';
                }
            });
    }

    // requestLogin(value) {
    //     // value로 어떤 소셜로그인인지 판단해서 로그인 진행
    //     console.log(value);
    // }

    responseFail = (err) => {
        console.error(err);
    }

    render() {
        console.log(this.state);
        if (sessionStorage.getItem('token') != null) {
            window.location = '/list';
        } else {
            return (
                <div className="loginpanel">
                    <div className="loginwindow">
                        <ul>
                            <h2 className="title">Login</h2>
                            <h3><KakaoLogin jsKey={OAuthConfig.KAKAO_JS_KEY} className="social-login-btn" onSuccess={this.responseKakao} onFailure={this.responseFail}><img src={kakaoBtn} value="kakao" alt="kakaoBtn"/></KakaoLogin></h3>
                        </ul>
                    </div>
                    <ModalWrapper token={this.state.token} userId={this.state.userId} modal={this.state.openModal}></ModalWrapper>
                </div>
            );
        }
    }
}

export default Login;