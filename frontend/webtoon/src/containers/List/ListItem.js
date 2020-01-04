import React, {Component} from 'react';
import { likeToon, unlikeToon } from '../../stores/list/actions'

class ListItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
           isLike: this.props.isLike,
           isCall: false
       };
     }
    
    isMobile() {
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
    }

    updateLike = async() => {
        if (this.state.isCall) {
            alert("기다려주세여")
        } else if (!this.state.isLike) {
            this.setState({isLike: !this.state.isLike})
            this.setState({isCall: true});
            await likeToon(this.props.toonCode);
            this.setState({isCall: false});
        } else if (this.state.isLike) {
            this.setState({isLike: !this.state.isLike})
            this.setState({isCall: true});
            await unlikeToon(this.props.toonCode);
            this.setState({isCall: false});
        }
    }

    render() {
        
        const {name, img, href, provider} = this.props;

        let prefixHref;
        if (provider === "NAVER") {
            prefixHref = "https://comic.naver.com";
        } else if (provider === "DAUM") {
            prefixHref = this.isMobile() ? "http://m.webtoon.daum.net/m" : "http://webtoon.daum.net";
        }

        const link = prefixHref + href;

        return (
            <div className="webtoon-item">
                <a href={link} target="_sub">
                    <div>
                        <img className="thumb-img" src={img} alt={name}/>
                    </div>
                </a>
                <span className="thumb-title">{name}</span>
                <span className="webtoon-item-like" onClick={this.updateLike} >
                {this.state.isLike ? '♥︎' : '♡'}
                </span>
            </div>
        )
    }
}

export default ListItem;