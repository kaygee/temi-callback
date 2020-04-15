import React, { Component } from 'react'
import './FetchData.css'

export default class FetchData extends Component {
    constructor(props) {
        super(props);
        this.state =
        {
            error: null,
            isLoaded: false,
            searchResults: []
        };
    }

    componentDidMount() {
        // fetch('http://qa-ci.internal.rev.com:7331/jobs/all')
        fetch('http://localhost:7331/jobs/all')
            // fetch('https://jsonplaceholder.typicode.com/users/')
            .then(res => res.json())
            .then((result) => {
                this.setState({
                    isLoaded: true,
                    searchResults: result
                })
                console.log(`Show data fetched. Count: ${result.length}`);
                console.log(this.state.searchResults);
            },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const { error, isLoaded, searchResults } = this.state;
        if (error) {
            return <div>Error: {error.message} - Install CORS</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else if (searchResults.length === 0) {
            return <div>Length of Array is 0. Could not fetch data</div>;
        } else {
            return (
                <div className="App" >
                    <h1>ReactJS + Spring Data REST</h1>
                    <h2>Fetch a list from an API and display it</h2>
                    < div className="datas" >
                        {searchResults.map((data, index) => {
                            return (
                                <div className="data">
                                    <h3>Data {index + 1}</h3>
                                    <h2>Database ID: {data.databaseId}</h2>

                                    <div className="details">
                                        <p>Http Status: {data.httpStatus}</p>
                                        <p>Job Type: {data.jobType}</p>
                                    </div>
                                </div>
                            );
                        })}
                    </div >
                </div >
            )
        }
    }
}