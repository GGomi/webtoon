import React, {Component} from 'react';
import styled from 'styled-components';

const imgWrapper = styled.div`
    
`;

class ListItem extends Component {
    isMobile() {
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
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
                <p className="thumb-title">{name}</p>
            </div>
        )
    }
}

export default ListItem;