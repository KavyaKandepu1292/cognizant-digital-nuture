DELIMITER //

-- SCENARIO 1: ProcessMonthlyInterest
CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    SELECT 'Monthly 1% interest applied to Savings accounts!' AS Message;
END //

-- SCENARIO 2: UpdateEmployeeBonus
CREATE PROCEDURE UpdateEmployeeBonus(
    IN p_dept    VARCHAR(50),
    IN p_percent DECIMAL(5,2)
)
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percent / 100)
    WHERE Department = p_dept;

    SELECT CONCAT('Bonus of ', p_percent, '% applied to ', p_dept,
                  ' department. Rows: ', ROW_COUNT()) AS Message;
END //

-- SCENARIO 3: TransferFunds
CREATE PROCEDURE TransferFunds(
    IN p_from   INT,
    IN p_to     INT,
    IN p_amount DECIMAL(10,2)
)
BEGIN
    DECLARE v_balance DECIMAL(10,2);

    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from;

    IF v_balance < p_amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient balance!';
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;

    SELECT CONCAT('Transferred $', p_amount,
                  ' from Account ', p_from,
                  ' to Account ', p_to) AS Message;
END //

DELIMITER ;

-- Test
CALL ProcessMonthlyInterest();
CALL UpdateEmployeeBonus('IT', 15);
CALL UpdateEmployeeBonus('HR', 10);
CALL TransferFunds(2, 1, 500);