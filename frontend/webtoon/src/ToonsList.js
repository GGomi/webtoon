import React, {Component} from 'react';
import Webtoon from './Webtoon';
import NavBar from './Layout/NavBar';
import loading from './commons/images/loading.gif';

class ToonsList extends Component {
    timer = null;

    constructor() {
        super(...arguments);
        this.state = {
            data: undefined,
            searchData: undefined,
            isFetching: true,
            tab: "NAVER"
        }
    }

    componentDidMount() {
        this.fetch();
        // this.timer = setInterval(() => this.fetch(), 3000);
    }

    fetch= () => {
        this.setState({data: undefined, searchData: undefined, isFetching: true})
        fetch('/api/v1/webtoon/list').then(res => res.json())
            .then(data => {
                this.setState({
                    data: data.data,
                    isFetching: false
                });
            });
    }

    componentWillMount() {
        // this.timer = null;
    }

    changeTab = (data) => {
        this.setState((prevState) => ({
            data: prevState.data,
            tab: data
        }))
    };

    render() {
        let {data, isFetching} = this.state;
        console.log(this.state);
        let date = new Date().getDate();

        if (date === 0) {
            date = 6;
        } else {
            date = date - 1;
        }

        if (isFetching) {
            return (
                <div className="loading-page">
                    <img src={loading} alt="loading" />
                </div>
            )
        } else {
            data = this.state.data[this.state.tab];

            const weekArr = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"];

            return (
                <div className="main">
                    <NavBar action={this.changeTab}/>

                    <div className="webtoon-list">
                        {weekArr.map(function (object, i) {
                            const column = data[object].map((value, index) => <Webtoon name={value.toonName}
                                                                                       provider={value.toonProvider}
                                                                                       day={value.serializeDay}
                                                                                       img={value.toonImgsrc}
                                                                                       href={value.toonHref}
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

export default ToonsList;