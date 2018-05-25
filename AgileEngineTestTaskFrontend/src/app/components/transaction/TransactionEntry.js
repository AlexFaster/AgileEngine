import './Transaction.css';
import React from 'react';

const TransactionEntry =  ({transaction}) => {
        const amountInDollars = (transaction.amount / 100).toFixed(2);
        const createdDate = new Date(transaction.created);
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
};

export default TransactionEntry