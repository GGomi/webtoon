import React, { Component } from 'react';
import ToonList from './ToonsList';

class Main extends Component {
  constructor() {
    super(...arguments);
    this.state = {
      type: 'webtoon'
    }
  }

  changeTab = (changeType) => {
    this.setState({
      type: changeType
    })
  }

  render() {
    const { type } = this.state;

    if(type === 'webtoon') {
      return <ToonList action={this.changeTab}></ToonList>;
    } else {
      return <div>HI</div>;
    }
    
  }

}
export default Main;