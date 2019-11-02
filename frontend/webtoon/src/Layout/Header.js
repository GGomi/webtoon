import React, { Component } from 'react';

class Header extends Component {
    render() {
        return (
            <div className="title-bar">
                <input id="searchBox" type="text" />
                <button className="btn search-btn"><img src={require("../commons/images/search-btn.png")} alt="search"></img></button>
                <button id="signUp" onClick={this.handleOpenModal}>회원가입</button>
            </div>
        );

       

        
    }
}

export default Header;