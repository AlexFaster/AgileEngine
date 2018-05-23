import './Transaction.css';
import React, {Component} from 'react';
import TransactionEntry from "./TransactionEntry";

class TransactionList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            transactions: []
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/v1/transactions?sort=created,desc")
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
                            transactions: result.content
                        });
                    }

                }
            )
    }

    render() {
        return (
            <div className="transactions">
                <h3>Transactions</h3>
                <table className="transactions-table table">
                    <tbody>
                    <tr>
                        <th>Wallet Id</th>
                        <th>Transaction type</th>
                        <th>Amount</th>
                        <th>Time</th>
                    </tr>
                    {this.state.transactions.map(transaction => (
                        <TransactionEntry transaction={transaction} key={transaction.id}/>
                        // <li key={item.id}>
                        //     {item.type} {item.amount}
                        // </li>
                    ))}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default TransactionList;