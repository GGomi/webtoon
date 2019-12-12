import React from 'react';
import { Switch, Route } from 'react-router-dom';
import ListContainer from './List';

function MainRoutes() {
  return (
    <Switch>
      <Route path="/" exact component={ListContainer} />
    </Switch>
  );
};
export default MainRoutes;