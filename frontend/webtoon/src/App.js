import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from 'react-router-dom';
import Main from './Main';

class App extends Component {
    render() {
        return (
            <Router>
                <Route exact path = "/" component = {Main} />
            </Router>
        );
    }
}

export default App;