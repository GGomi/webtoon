import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
// import 'bootstrap/dist/css/bootstrap.css';

document.title = "MyToon | JeongminOh";
ReactDOM.render(<App />, document.getElementById('root'));

serviceWorker.unregister();
