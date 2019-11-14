import React, { Component } from 'react';

class SignUp extends Component {
    constructor(){
        super(...arguments);        
        this.state = {
            requestNickname:''
        };

        this.requestNicknameChange = this.requestNicknameChange.bind(this);
    }

    onSubmit() {
        let userInfo = {
            'nickname': this.state.requestNickname
        };

        const apiPath = '/api/v1/user/signup';
        
        fetch(apiPath, {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userInfo)
        }).then((response)=> response.json())
            .then((responseData)=> {
                if (responseData.code === 200) {
                    sessionStorage.setItem('token', responseData.data);
                } else {
                    alert(responseData.msg);
                }
            });
    }

    requestNicknameChange(event) {
        this.setState({requestNickname: event.target.value});
    }

    render() {
        if (sessionStorage.getItem('token') != null) {
            window.location = '/list';
        } else {
            return (
                <div className="loginpanel">
                    <div className="loginwindow">
                        
                        <ul>
                            <h2 className="title">Login</h2>
                            <input type="text" name="requestNickname" placeholder="Nickname" value={this.state.requestNickname} onChange={this.requestNicknameChange}/>
                            <button className="loginwindowbutton" onClick={this.onSubmit.bind(this)}>회원가입</button>
                        </ul>
                    </div>
                </div>
            );
        }
    }
}

export default SignUp;