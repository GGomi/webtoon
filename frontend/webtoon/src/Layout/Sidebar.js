import React, { Component } from 'react';

class Sidebar extends Component {
    render() {
        return (
            <div className="sidebar">
                <nav className="menu">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Pricing</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">Blog</a></li>
                </nav>
            </div>
        );
    }
}

export default Sidebar;