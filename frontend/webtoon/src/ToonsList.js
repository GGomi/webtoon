import React, {Component} from 'react';
import Webtoon from './Webtoon';
import NavBar from './Layout/NavBar';
import { faSpinner } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

class ToonsList extends Component {
    timer = null;

    constructor(props) {
        super(props);
        this.state = {
            token: '',
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
        if (data === 'NAVER' || data === 'DAUM') {
            this.setState((prevState) => ({
                data: prevState.data,
                tab: data
            }))
        } else {
            console.log(data);
            this.props.action(data);
        }
    };

    render() {
        let {data, isFetching} = this.state;
        let date = new Date().getDay();

        if (date === 0) {
            date = 6;
        } else {
            date = date - 1;
        }

        if (isFetching) {
            return (
                <div className="loading-page">
                    <FontAwesomeIcon color="white" icon={faSpinner} size="8x" spin={true}></FontAwesomeIcon>
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