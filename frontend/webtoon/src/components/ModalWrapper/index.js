import React, {useState, useEffect, useCallback} from 'react';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter, Input} from 'reactstrap';
import '../../commons/styles/style.css';
import {updateState, userSignUp} from "../../stores/auth/action";

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

  const onSubmit = async () => {
    console.log(userId);

    const signUpResponse = await dispatch(userSignUp({
      userId: userId,
      username: requestNickname,
      token: token
    }));

    if (signUpResponse.code !== '200') {
      console.error(signUpResponse);
      window.alert('서버와 통신이 원활하지않습니다.');
    } else {
      localStorage.setItem("auth", JSON.stringify({
        user_id: userId,
        user_name: requestNickname,
        token: token
      }));

      window.location.href = '/';
    }


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