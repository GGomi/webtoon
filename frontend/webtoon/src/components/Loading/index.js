import React from 'react';
import Icon from '../Icon';
import { faCircleNotch } from '@fortawesome/free-solid-svg-icons';
import styled, { keyframes } from 'styled-components';

const fade = keyframes`
  0% {
    background-color: rgba(0, 0, 0, 0.05);
  }
  50% {
    background-color: rgba(0, 0, 0, 0.075);
  }
  100% {
    background-color: rgba(0, 0, 0, 0.05);
  }
`;

const LoadingWrapper = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 100;

  .backdrop {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-color: rgba(0, 0, 0, 0.05);
    /** name | duration | timing-function | delay | iteration-count | direction | fill-mode | play-state */
    animation: ${fade} 1.5s ease-in-out 500ms infinite;
  }
  .contents {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 100;
  }
`;

function Loading() {
  return (
    <LoadingWrapper>
    <div className="backdrop"/>
    <div className="contents">
      <Icon icon={faCircleNotch} spin size="5x" />
    </div>
  </LoadingWrapper>
  );
}
export default Loading;
