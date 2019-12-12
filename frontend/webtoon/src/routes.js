import React, { Suspense, Fragment } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import PrivateRoute from './components/PrivateRoute';
import Loading from './components/Loading';
import withReduxProvider from './stores';

import LoginContainer from './containers/Login';
import MainContainer from './containers/index';

function Routes() {
  return (
    <Router>
      <Suspense fallback={ <Loading /> }>
        <Fragment>
          <Switch>
            <Route path="/login" exact component={LoginContainer} />
            <PrivateRoute path="/" component={MainContainer} />
          </Switch>
        </Fragment>
      </Suspense>
    </Router>
  );
}
export default withReduxProvider(Routes);