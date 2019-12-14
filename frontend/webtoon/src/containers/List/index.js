import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {fetchList} from '../../stores/list/actions'
import Webtoon from './ListItem';
import Loading from '../../components/Loading';
import Profile from '../Profile';

const getDate = () => {
  let date = new Date().getDay();
  if (date === 0) {
    date = 6;
  } else {
    date = date - 1;
  }
  return date;
};

function ToonsList() {
  const dispatch = useDispatch();
  const state = useSelector(state => state.list);

  useEffect(() => {
    const getInit = async () => {
      await dispatch(fetchList());
    };
    getInit();
  }, []);

  const thisDate = getDate();
  const weekArr = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"];
  const data = state.data[state.tab];

  if (!data) {
    return <Loading/>;
  }

  return (
      <div className="main">
        {
          state.tab === 'profile'
              ? <Profile/>
              : <div className="webtoon-list">
                {weekArr.map(function (object, i) {
                  const column = data[object].map((value, index) => <Webtoon name={value.name}
                                                                             provider={value.provider}
                                                                             day={value.serializeDay}
                                                                             img={value.imgSrc}
                                                                             href={value.href}
                                                                             key={index}/>);

                  let className = i === thisDate ? "daliy-list side today" : "daliy-list side";

                  return (
                      <div className={className} key={i}>
                        <div className="date-header">{weekArr[i]}</div>
                        {column}
                      </div>
                  )
                })}
              </div>
        }

      </div>
  );
}

export default ToonsList;
