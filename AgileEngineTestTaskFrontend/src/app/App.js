import React, {Component} from 'react';
import logo from '../logo.svg';
import './App.css';
import Wallet from "./components/wallet/Wallet";
import TransactionList from "./components/transaction/TransactionList";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            needTransactionUpdate: true
        }
    }

    pingTransactionComponent = () => {
        this.setState({
            needTransactionUpdate: !this.state.needTransactionUpdate
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
                <TransactionList ping={this.state.needTransactionUpdate}/>
            </div>
        );
    }
}

export default App;
