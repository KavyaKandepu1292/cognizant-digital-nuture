DELIMITER //

-- SCENARIO 1: UpdateCustomerLastModified
CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = NOW();
END //

-- SCENARIO 2: LogTransaction
CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(TransactionID, LogDate, Message)
    VALUES(
        NEW.TransactionID,
        NOW(),
        CONCAT('Txn inserted: ID=', NEW.TransactionID,
               ' Amount=$', NEW.Amount,
               ' Type=', NEW.TransactionType)
    );
END //

-- SCENARIO 3: CheckTransactionRules
CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE v_balance DECIMAL(10,2);

    IF NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance
        FROM Accounts WHERE AccountID = NEW.AccountID;

        IF v_balance < NEW.Amount THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Withdrawal exceeds balance!';
        END IF;

    ELSEIF NEW.TransactionType = 'Deposit' THEN
        IF NEW.Amount <= 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Deposit must be positive!';
        END IF;
    END IF;
END //

DELIMITER ;

-- Test
UPDATE Customers SET Balance = 2000 WHERE CustomerID = 1;
SELECT LastModified FROM Customers WHERE CustomerID = 1;

INSERT INTO Transactions VALUES(3, 1, NOW(), 100, 'Deposit');
SELECT * FROM AuditLog;

INSERT INTO Transactions VALUES(4, 1, NOW(), 50, 'Deposit');