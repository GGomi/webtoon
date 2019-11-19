import React from 'react';
import SideNav, { NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';
import { faBookOpen, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const SideBar = (props) => {
    return (
        <SideNav style={{"position": "fixed"}} onSelect={(selected) => {
            props.action(selected);
        }}>
            <SideNav.Toggle />
            <SideNav.Nav defaultSelected="webtoon">
                <NavItem eventKey="webtoon">
                    <NavIcon>
                        <FontAwesomeIcon icon={faBookOpen} style={{ fontSize: '1.75em' }}></FontAwesomeIcon>
                    </NavIcon>
                    <NavText>
                        웹툰
                    </NavText>
                    <NavItem eventKey="NAVER">
                        <NavText>
                            네이버
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="DAUM">
                        <NavText>
                            다음
                        </NavText>
                    </NavItem>
                </NavItem>
                <NavItem eventKey="profile">
                    <NavIcon>
                        {/* <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} /> */}
                        <FontAwesomeIcon icon={faUser} style={{ fontSize: '1.75em' }}></FontAwesomeIcon>
                    </NavIcon>
                    <NavText>
                        내정보
                    </NavText>
                </NavItem>
            </SideNav.Nav>
        </SideNav>
    )
}
export default SideBar;
