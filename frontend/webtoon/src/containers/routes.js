import React from 'react';
import { Switch, Route } from 'react-router-dom';
import ListContainer from './List';
import Profile from './Profile';

function MainRoutes() {
  return (
    <Switch>
      <Route path="/" exact component={ListContainer} />
      <Route path="/profile" component={Profile} />
    </Switch>
  );
};
export default MainRoutes;