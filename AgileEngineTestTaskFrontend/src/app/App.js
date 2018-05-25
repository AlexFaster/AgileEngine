import React, {Component} from 'react';
import logo from '../logo.svg';
import './App.css';
import Wallet from "./components/wallet/Wallet";
import TransactionList from "./components/transaction/TransactionList";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            requestCount: 0
        }
    }

    pingTransactionComponent = () => {
        this.setState({
            requestCount: this.state.requestCount + 1
        })
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to Wallet Service</h1>
                </header>
                <Wallet transactionStatus={this.pingTransactionComponent}/>
                <hr/>
                <TransactionList ping={this.state.requestCount}/>
            </div>
        );
    }
}

export default App;
