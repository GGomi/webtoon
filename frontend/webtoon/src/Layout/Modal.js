import ReactModal from 'react-modal';
import React from 'react';
import '../App.css';
ReactModal.setAppElement("body");

class Modal extends React.Component {
    constructor() {
        super();
        this.state = {
            showModal: false
        };

        this.handleOpenModal = this.handleOpenModal.bind(this);
        this.handleCloseModal = this.handleCloseModal.bind(this);
    }

    handleOpenModal() {
        this.setState({ showModal: true });
    }

    handleCloseModal() {
        this.setState({ showModal: false });
    }

    render() {
        return (
            <div >
                <div className="modal">
            
                <button onClick={this.handleCloseModal}>닫기</button>
                </div>
            </div>
        );
    }
}
export default Modal;