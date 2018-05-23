import './Wallet.css';

import React, {Component} from 'react';

class Wallet extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            balance: 0
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/v1/wallets/1/balance")
            .then(res => res.json())
            .then(
                (result) => {
                    if (result.error) {
                        this.setState({
                            isLoaded: true,
                            error: result.message
                        });
                    } else {
                        this.setState({
                            isLoaded: true,
                            balance: result.money
                        });
                    }

                }
            )
    }

    render() {
        if (this.state.error) {
            return <div>{this.state.error}</div>
        }
        if (!this.state.isLoaded) {
            return <p>Loading...</p>
        }
        return (
            <div className="wallet">
                <h3>Wallet</h3>
                <div className="wallet-info">
                    <p className="money">Money: <span id="money">{this.state.balance}</span></p>
                </div>
                <div className="controls">
                    <input type="button" id="withdraw" value="Withdraw" className="btn-danger control-button"/>
                    <input type="text" id="amount"/>
                    <input type="button" id="deposit" value="Deposit" className="btn-success control-button"/>
                </div>
                <div className="note">
                    <p>For simplicity it always operates with wallet with id=1, but api is flexible enough</p>
                </div>
            </div>
        )
    }
}

export default Wallet;
