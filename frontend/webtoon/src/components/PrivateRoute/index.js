import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Redirect } from 'react-router-dom';

function PrivateRoute({ component, ...props }) {
  const auth = useSelector(state => state.auth);
  const InnerComponent = component;
  const redirectTo = {
    pathname: '/login',
    state: {
      from: props.location
    },
  };

  if (!auth.isAuthenticated || auth.username === '') {
    console.log(auth);
    console.log(props);
    // return <Redirect to={redirectTo} />;
  }

  return (
    <Route {...props} render={props => <InnerComponent {...props} />} />
  );
}
export default PrivateRoute;
