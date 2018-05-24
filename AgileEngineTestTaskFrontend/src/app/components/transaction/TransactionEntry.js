import './Transaction.css';
import React, {Component} from 'react';

class TransactionEntry extends Component {
    render() {
        const {transaction} = this.props;
        let amountInDollars = (transaction.amount / 100).toFixed(2);
        let createdDate = new Date(transaction.created);
        return (
            <tr className="transaction-unit">
                <td>
                    {transaction.walletId}
                </td>
                <td>
                    {transaction.type}
                </td>
                <td>
                    {amountInDollars}$
                </td>
                <td>
                    {createdDate.toLocaleString()}
                </td>
            </tr>
        )
    }
}

export default TransactionEntry