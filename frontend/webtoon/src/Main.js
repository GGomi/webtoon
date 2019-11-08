import React, {Component} from 'react';
import Webtoon from './Webtoon';
import NavBar from '../src/Layout/NavBar';

class Main extends Component {
    constructor() {
        super(...arguments);
        this.state = {
            data: undefined,
            searchData: undefined,
            tab: "NAVER"
        }
    }

    componentDidMount() {
        document.title = "MyToon | JeongminOh";

        fetch('/api/v1/webtoon/list').then(res => res.json())
            .then(data => {
                this.setState({
                    data: data.data
                });
            });
    }

    changeTab = (data) => {
        this.setState((prevState) => ({
            data: prevState.data,
            tab: data
        }))
    };

    render() {
        let {data} = this.state;
        let date = new Date().getDate();

        if (date === 0) {
            date = 6;
        } else {
            date = date - 1;
        }

        if (!data) {
            return (
                <div className="loading-page">
                    <h2>로딩중........</h2>
                </div>
            )
        } else {
            data = this.state.data[this.state.tab];

            const weekArr = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"];

            return (
                <div className="main">
                    {/* <div className="fixed-menu">
            <input data-function="swipe" id="swipe" type="checkbox"/>
            <label data-function="swipe" htmlFor="swipe">&#xf057;</label>
            <label data-function="swipe" htmlFor="swipe">&#xf0c9;</label>
            <img data-function="swipe" className="menu-img" src={require("./commons/images/menu-button.png")} />
          </div> */}
                    <NavBar action={this.changeTab}/>

                    <div className="webtoon-list">
                        {weekArr.map(function (object, i) {
                            const column = data[object].map((value, index) => <Webtoon name={value.toon_name}
                                                                                       provider={value.toon_provider}
                                                                                       day={value.serialize_day}
                                                                                       img={value.toon_imgsrc}
                                                                                       href={value.toon_href}
                                                                                       key={index}/>);

                            let className = i === date ? "daliy-list side today" : "daliy-list side";

                            return (
                                <div className={className} key={i}>
                                    <div className="date-header">{weekArr[i]}</div>
                                    {column}
                                </div>
                            )
                        })}
                    </div>
                </div>
            );
        }
    }
}

export default Main;