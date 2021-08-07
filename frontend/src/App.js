import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  client;
  state = {
  };

  async componentDidMount() {
    const response = await fetch('/api/v1/iothome/clients');
    const body = await response.json();
    this.client = body.client;
    // this.setState({clients: body});
  }

  render() {
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Clients</h2>
              {client}
              {/*{clients.length}*/}
            </div>
          </header>
        </div>
    );
  }
}
export default App;