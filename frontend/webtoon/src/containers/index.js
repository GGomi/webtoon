import React from 'react';
import { useDispatch } from 'react-redux';
import LeftMenu from '../containers/Layout/LeftMenu';
import MainRoutes from './routes';


function MainContainer() {
  const dispatch = useDispatch();
  return (
    <main>
      <LeftMenu dispatch={dispatch}/>
      <MainRoutes />
    </main>
  );
}

export default MainContainer;
