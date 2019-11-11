import React, { Component } from 'react';
import KakaoLogin from 'react-kakao-login';

class Login extends Component {
    constructor(){
        super(...arguments);
        this.state = {
            userId: '',
            name: '',
            token: '',
            provider: ''
        };
    }

    onJoin() {
        this.props.history.push('/join');
    }

    responseKakao = (response) => {
        const res = response.response;
        console.log(res.access_token);
        sessionStorage.setItem('token', res.access_token);
        this.setState({
            token: res.access_token,
            provider: 'kakao'
        });
    }

    responseFail = (err) => {
            console.error(err);
    }

    render() {
        if (sessionStorage.getItem('token') != null) {
            return (
                <div>Login Success</div>
            );
        } else {
            return (
                <div className="loginpanel">
                    <div className="loginwindow">
                        <ul>
                            <h2 className="title">Login</h2>
                            <h3><KakaoLogin jsKey="88c27881818f0ed103f0ae99ead2bd34" buttonText="Kakao" onSuccess={this.responseKakao} onFailure={this.responseFail}></KakaoLogin></h3>
                        </ul>
                    </div>
                </div>
            );
        }
    }
}

export default Login;