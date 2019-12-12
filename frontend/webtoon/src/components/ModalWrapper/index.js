import React, {useState, useEffect, useCallback} from 'react';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter, Input} from 'reactstrap';
import '../../commons/styles/style.css';

const ModalWrapper = (props) => {

  const {
    className,
    userId,
    modal,
    token,
    dispatch
  } = props;

  const [trigger, setTrigger] = useState(modal);
  const [requestNickname, setRequestNickName] = useState('');

  const toggle = () => setTrigger(!trigger);

  useEffect(() => {
    setTrigger(props.modal);
  }, [props]);

  const modalCancel = () => {
    toggle();
    window.location.reload(true);
  };

  const requestNicknameChange = useCallback((event) => {
    setRequestNickName(event.target.value)
  }, []);

  const onSubmit = () => {
    // const apiPath = '/api/v1/user/signup';
    //
    // const userInfo = {
    //   'userId': userId,
    //   'nickname': requestNickname
    // };
    //
    // fetch(apiPath, {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json'
    //   },
    //   body: JSON.stringify(userInfo)
    // }).then((response) => response.json())
    // .then((responseData) => {
    //   console.log(responseData);
    //   if (responseData.code === "200") {
    //     sessionStorage.setItem('token', token);
    //     sessionStorage.setItem('username', requestNickname);
    //     window.location.href = '/';
    //   } else {
    //     console.error(responseData);
    //     window.alert('서버와 통신이 원활하지않습니다.', window.location.reload(true));
    //   }
    // });
  };

  return (
      <div>
        <Modal isOpen={trigger} toggle={toggle} className={className}>
          <ModalHeader className="custom-modal-header">
            <span role="img" aria-label="smile">🥳</span>회원가입<span role="img" aria-label="smile">🥳</span>
          </ModalHeader>
          <ModalBody>
            닉네임 : <Input name="requestNickname" value={requestNickname} onChange={requestNicknameChange}/>
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={onSubmit}>회원가입</Button>{' '}
            <Button color="secondary" onClick={modalCancel}>취소</Button>
          </ModalFooter>
        </Modal>
      </div>
  );
}

export default ModalWrapper;