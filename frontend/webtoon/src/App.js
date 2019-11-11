import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from 'react-router-dom';
import ToonsList from './ToonsList';
import Login from './Login';

class App extends Component {
    onLogout() {
        sessionStorage.removeItem('token');
    }

    render() {
        return (
            <Router>
                <Route exact path = "/" component = {Login} />
                <Route path = "/list" component = {ToonsList} />
            </Router>
        );
    }
}

export default App;