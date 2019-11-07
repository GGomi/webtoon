import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from 'react-router-dom';
import Main from './Main';
// import Login from './Login';

class App extends Component {
    onLogout() {
        sessionStorage.removeItem('token');
    }

    render() {
        return (
            <Router>
                {/* <Route exact path = "/" component = {Login} /> */}
                <Route exact path = "/" component = {Main} />
            </Router>
        );
    }
}

export default App;