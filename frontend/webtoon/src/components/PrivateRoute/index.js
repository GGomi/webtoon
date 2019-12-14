import React from 'react';

import {useDispatch, useSelector} from 'react-redux';
import {Route, Redirect} from 'react-router-dom';

function PrivateRoute({component, ...props}) {
  const auth = useSelector(state => state.auth);
  const dispatch = useDispatch();

  const InnerComponent = component;
  const redirectTo = {
    pathname: '/login',
    state: {
      from: props.location
    },
  };

  if(!localStorage.getItem('auth')) {
    return <Redirect to={redirectTo}/>;
  }

  return (
      <Route {...props} render={props => <InnerComponent {...props} />}/>
  );
}

export default PrivateRoute;
