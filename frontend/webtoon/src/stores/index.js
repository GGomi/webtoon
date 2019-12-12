import React from 'react';
import { compose, applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';
import rootReducer from './reducers';

/**
 * Initialize Global Redux Store Settings
 * @return {Function}
 */
function initStore() {
  let composeEnhancers = compose;
  if (process.env.NODE_ENV === 'development' && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__) {
    composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__;
  }

  const middlewares = composeEnhancers(applyMiddleware(thunk));
  const store = createStore(rootReducer, window.__PRELOADED_STATE__ || {}, middlewares);
  return store;
}

/**
 * Redux Provider HOC
 * @param {function(): *} WrappedComponent
 * @return {React.Component}
 */
const withReduxProvider = WrappedComponent => {
  function ReduxProvider(props) {
    return (
      <Provider store={initStore()} key={Math.random()}>
        <WrappedComponent {...props} />
      </Provider>
    );
  }
  return ReduxProvider;
}

export default withReduxProvider;
