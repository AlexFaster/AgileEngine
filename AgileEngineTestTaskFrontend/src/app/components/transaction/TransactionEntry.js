import './Transaction.css';
import React, {Component} from 'react';

class TransactionEntry extends Component {
    render() {
        const {transaction} = this.props;
        return (
            <tr className="transaction-unit">
                <td>
                    {transaction.walletId}
                </td>
                <td>
                    {transaction.type}
                </td>
                <td>
                    {transaction.amount}
                </td>
                <td>
                    {transaction.created}
                </td>
            </tr>
        )
    }
}

export default TransactionEntry