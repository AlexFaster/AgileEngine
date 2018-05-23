import React, {Component} from 'react';
import logo from '../logo.svg';
import './App.css';
import Wallet from "./components/wallet/Wallet";
import TransactionList from "./components/transaction/TransactionList";

class App extends Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to Wallet Service</h1>
                </header>
                <Wallet />
                <hr/>
                <TransactionList/>
            </div>
        );
    }
}

export default App;
