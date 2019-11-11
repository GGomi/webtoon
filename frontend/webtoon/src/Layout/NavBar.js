import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, Button, Form, FormGroup, Input } from 'reactstrap';
import "bootstrap/dist/css/bootstrap.css"

const NavBar = (props) => {

    const [collapsed, setCollapsed] = useState(true);

    const toggleNavbar = () => setCollapsed(!collapsed);
    const [cSelected, setCSelected] = useState([]);

    const onCheckboxBtnClick = (selected) => {
        const index = cSelected.indexOf(selected);
        if (index < 0) {
            cSelected.push(selected);
        } else {
            cSelected.splice(index, 1);
        }
        setCSelected([...cSelected]);
    }

    return (
        <div className="nav-header">
            <Navbar color="faded" light>
                <NavbarBrand href="/" className="mr-auto">Webtoooooooon</NavbarBrand>
                <NavbarToggler onClick={toggleNavbar} className="mr-2" />
                <Collapse isOpen={!collapsed} navbar>
                    <Nav navbar>
                        <NavItem className="nav-item">
                            <Button color="danger" value="NAVER" onClick={e => props.action("NAVER")} active={cSelected.includes(1)}>네이버</Button>
                        </NavItem>
                        <NavItem className="nav-item">
                            <Button color="danger" value="DAUM" onClick={e => props.action("DAUM")} active={cSelected.includes(2)}>다음</Button>
                        </NavItem>
                        <NavItem className="nav-item">
                            <Button color="danger" onClick={() => onCheckboxBtnClick(3)} active={cSelected.includes(3)}>My Toons</Button>
                        </NavItem>
                        <NavItem className="">
                            <Form inline>
                                <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                                    <Input type="text" name="email" id="searchInput" placeholder="Search" />
                                </FormGroup>
                                <Button>Submit</Button>
                            </Form>
                        </NavItem>

                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    );
}

export default NavBar;