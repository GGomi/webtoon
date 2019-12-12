import React from 'react';
import { hot } from 'react-hot-loader/root';
import Route from './routes';

import './commons/styles/style.css';
import 'bootstrap/dist/css/bootstrap.css';

function RootContainer() {
  return <Route />;
}
export default hot(RootContainer);
