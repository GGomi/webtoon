import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from 'react-router-dom';
import ToonsList from './ToonsList';
// import Login from './Login';
// import Signup from './SignUp';

class App extends Component {
    onLogout() {
        sessionStorage.removeItem('token');
    }

    render() {
        return (
            <Router>
                {/* <Route exact path = "/login" component = {Login} /> */}
                <Route exact path = "/" component = {ToonsList} />
            </Router>
        );
    }
}

export default App;