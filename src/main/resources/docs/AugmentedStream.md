# Augmented Stream Example

## Problem Statement

- Creating an event model that represents a hybrid state across multiple data sources that are related. 

```
Account: {
    accountNumber, 
    accountOwnerName, 
    accountBalance, 
    Type
}

Balance: {
    accountNumber, 
    amount
}

Relation: {
    relationKey, 
    accountNumber
}

```
