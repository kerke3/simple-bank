# Simple Bank

A simple banking web application that allows users to create accounts, manage balances, and process transactions. The project is built with Java and Maven and can be deployed using Docker.

## Features
- User Creation
- User Deactivation
- Account creation
- Balance inquiry
- Deposit and withdrawal operations
- Balance transfer between accounts and users
- Transaction history

## Setup

### Requirements

- Docker
- Docker Compose (Optional)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/kerke3/simple-bank.git
   cd simple-bank

2. Using Docker
    ```bash
    docker build . -t anas/simple-bank:dev
    docker run -d --name simple-bank-container -p 8080:8080 anas/simple-bank:dev
    # to remove container
    docker stop simple-bank-container
    docker rm simple-bank-container
    

3. Using Docker Compose
    ```bash
    docker-compose build simple-bank
    docker-compose up -d simple-bank
    # to remove container
    docker-compose down

4. Remove image
    ```bash
    docker rmi anas/simple-bank:dev

The application should now be running on http://localhost:8080.

## API Documentation

You can import the postman collection inside the "postman" folder to make requests to the APIs.

### 1. Create User

- **Endpoint**: `POST /api/v1/user/create`
- **Description**: Create a new user with default "Current" account with a zero balance. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/user/create' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas"
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "active": true,
            "accounts": {
                "Current": {
                    "accountId": "Current",
                    "balance": 0.0
                }
            }
        }
    }
    ```
### 2. Deactivate User

- **Endpoint**: `POST /api/v1/user/deactivate`
- **Description**: Deactivate a user's profile.
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/user/deactivate' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas"
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "message": "Your user profile has been successfully deactivated, We hope to see you again!"
        }
    }
    ```

### 3. Open New Account for User

- **Endpoint**: `POST /api/v1/account/open-account`
- **Description**: Open a new account for the user with the requested account ID. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/account/open-account' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas1",
        "accountId": "Savings"
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "accounts": {
                "Savings": {
                    "accountId": "Savings",
                    "balance": 0.0
                },
                "Current": {
                    "accountId": "Current",
                    "balance": 0.0
                }
            }
        }
    }
    ```

### 4. Retrieve User's Accounts

- **Endpoint**: `GET /api/v1/account/accounts`
- **Description**: Retrieve a user account list with the user ID given as a request param. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/account/accounts?userId=anas'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "accounts": {
                "Savings": {
                    "accountId": "Savings",
                    "balance": 0.0
                },
                "Current": {
                    "accountId": "Current",
                    "balance": 0.0
                }
            }
        }
    }
    ```

### 5. Deposit Into Account

- **Endpoint**: `POST /api/v1/transaction/deposit`
- **Description**: Deposit funds into a user's account. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/transaction/deposit' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas",
        "accountId": "Current",
        "amount": 100
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "account": {
                "accountId": "Current",
                "balance": 100.0
            },
            "transaction": {
                "transactionId": "dc1e8a0a-3381-4dd4-9b6e-bd835813aa66",
                "datetime": "2024-10-03T11:49:25.587919400Z",
                "amount": 100.0,
                "transactionType": "DEPOSIT",
                "receivingUser": "anas",
                "receievingAccount": "Current"
            }
        }
    }
    ```

### 6. Withdraw From Account

- **Endpoint**: `POST /api/v1/transaction/withdraw`
- **Description**: Withdraw funds from a user's account. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/transaction/withdraw' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas",
        "accountId": "Current",
        "amount": 90
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "account": {
                "accountId": "Current",
                "balance": 10.0
            },
            "transaction": {
                "transactionId": "972739d9-d5f1-4fd3-9d53-757d6d19d2d2",
                "datetime": "2024-10-03T12:10:02.386492700Z",
                "amount": 90.0,
                "transactionType": "WITHDRAWAL",
                "sendingUser": "anas",
                "sendingAccount": "Current"
            }
        }
    }
    ```
### 7. Transfer From Account To Another Account

- **Endpoint**: `POST /api/v1/transaction/transfer`
- **Description**: Transfer funds from a user's account to another user's account. It is also possible for a user to tranfer from one of his accounts to another. 
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/transaction/transfer' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId":"anas",
        "accountId": "Current",
        "recipientId":"anas",
        "recipientAccountId" : "Savings",
        "amount": 10
    }'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "account": {
                "accountId": "Current",
                "balance": 0.0
            },
            "transaction": {
                "transactionId": "5cb8da44-a845-4d29-8d9f-6d35c7fe5bb5",
                "datetime": "2024-10-03T12:10:33.322311900Z",
                "amount": 10.0,
                "transactionType": "DEBIT",
                "sendingUser": "anas",
                "receivingUser": "anas",
                "sendingAccount": "Current",
                "receievingAccount": "Savings"
            }
        }
    }
    ```
### 8. View User's transactions

- **Endpoint**: `POST /api/v1/transaction/transactions`
- **Description**: Retrieves a list of a user's transactions on all his accounts.
- **Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/transaction/transactions?userId=anas'
    ```
- **Response**:
    ```json
    {
        "success": true,
        "payload": {
            "userId": "anas",
            "accounts": {
                "Savings": {
                    "accountId": "Savings",
                    "balance": 10.0
                },
                "Current": {
                    "accountId": "Current",
                    "balance": 0.0
                }
            },
            "transactions": [
                {
                    "transactionId": "5cb8da44-a845-4d29-8d9f-6d35c7fe5bb5",
                    "datetime": "2024-10-03T12:10:33.322311900Z",
                    "amount": 10.0,
                    "transactionType": "DEBIT",
                    "sendingUser": "anas",
                    "receivingUser": "anas",
                    "sendingAccount": "Current",
                    "receievingAccount": "Savings"
                },
                {
                    "transactionId": "0894b1cc-37be-4ad7-8212-9e4b15324e5d",
                    "datetime": "2024-10-03T12:10:33.322311900Z",
                    "amount": 10.0,
                    "transactionType": "CREDIT",
                    "sendingUser": "anas",
                    "receivingUser": "anas",
                    "sendingAccount": "Current",
                    "receievingAccount": "Savings"
                },
                {
                    "transactionId": "972739d9-d5f1-4fd3-9d53-757d6d19d2d2",
                    "datetime": "2024-10-03T12:10:02.386492700Z",
                    "amount": 90.0,
                    "transactionType": "WITHDRAWAL",
                    "sendingUser": "anas",
                    "sendingAccount": "Current"
                },
                {
                    "transactionId": "79f2985e-32a0-4527-aafb-c64817250d04",
                    "datetime": "2024-10-03T12:09:59.970898600Z",
                    "amount": 100.0,
                    "transactionType": "DEPOSIT",
                    "receivingUser": "anas",
                    "receievingAccount": "Current"
                }
            ]
        }
    }
    ```
### Response errors
- **Description**: Errors are are always returned as an Array of errors.
- **Sameple Request**:
    ```bash
    curl --location 'localhost:8080/api/v1/user/create' \
    --header 'Content-Type: application/json' \
    --data '{}'
    ```
- **Error Response**:
    ```json
    {
        "success": false,
        "errors": [
            "userId field is required",
            "must not be null"
        ]
    }
    ```