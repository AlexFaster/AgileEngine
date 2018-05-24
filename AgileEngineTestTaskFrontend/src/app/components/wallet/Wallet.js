import './Wallet.css';

import React, {Component} from 'react';

class Wallet extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            balance: 0,
            transactionHash: this.generateTransactionHash(),
            inputValidation: false,
            buttonDisabled: false,
            requestedAmount: null
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
            ).catch(alert)
    }

    render() {
        if (!this.state.isLoaded) {
            return <p>Loading...</p>
        }
        if (this.state.error) {
            alert(this.state.error)
        }

        let balanceInDollars = (this.state.balance / 100).toFixed(2);

        return (
            <div className="wallet">
                <h3>Wallet</h3>
                <div className="wallet-info">
                    <p className="money">Money: <span id="money">{balanceInDollars}$</span></p>
                </div>
                <div className="controls">
                    <button id="withdraw" className="btn-danger control-button"
                            onClick={this.handleWithdraw}>Withdraw
                    </button>
                    <input type="text" id="amount" placeholder="amount in cents!!" onChange={this.updateRequestedAmount}/>
                    <button id="deposit" className="btn-success control-button"
                            onClick={this.handleDeposit}>Deposit
                    </button>
                </div>
                <div className="note">
                    <p>For simplicity it always operates with wallet with id=1, but api is flexible enough</p>
                </div>
            </div>
        )
    }

    doTransaction = (url, errorHandler) => {
        if (this.state.inputValidation) {
            fetch(url, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify({amount: this.state.requestedAmount, hash: this.state.transactionHash})
            })
                .then(res => res.json())
                .then(
                    (result) => {
                        if (result.error) {
                            errorHandler(result);
                        } else {
                            this.setState({
                                isLoaded: true,
                                balance: result.money,
                                transactionHash: this.generateTransactionHash(),
                                buttonDisabled: false,
                                error: null
                            });
                            this.props.transactionStatus();
                        }
                    }
                ).catch(alert)
        } else {
            this.setState({
               error: "Incorrect requested amount"
            });
        }
    };

    handleDeposit = () => {
        this.doTransaction(
            "http://localhost:8080/api/v1/wallets/1/deposit",
            (result) => this.depositErrorHandler(result)
        );
    };


    handleWithdraw = () => {
        this.doTransaction(
            "http://localhost:8080/api/v1/wallets/1/withdraw",
            (result) => this.withdrawErrorHandler(result)
        );
    };


    withdrawErrorHandler = (result) => {
        this.setState({
            isLoaded: true,
            error: result.message,
            buttonDisabled: false
        });
    };

    depositErrorHandler = (result) => {
        this.setState({
            isLoaded: true,
            error: result.message,
            buttonDisabled: false
        });
    };

    generateTransactionHash = () => {
        return Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 16);
    };

    updateRequestedAmount = (evt) => {
        let requestedAmount = evt.target.value;
        let isAmountValid = parseInt(requestedAmount, 10) && requestedAmount > 0;
        this.setState({
            inputValidation: isAmountValid,
            requestedAmount: requestedAmount,
            error: null
        });
    }
}


export default Wallet;
