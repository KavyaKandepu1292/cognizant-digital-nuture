DELIMITER //

-- SCENARIO 1: SafeTransferFunds
CREATE PROCEDURE SafeTransferFunds(
    IN p_from INT,
    IN p_to   INT,
    IN p_amount DECIMAL(10,2)
)
BEGIN
    DECLARE v_balance DECIMAL(10,2);
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'ERROR: Transaction rolled back!' AS Message;
    END;

    START TRANSACTION;
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from;

    IF v_balance < p_amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient funds!';
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;
    COMMIT;
    SELECT CONCAT('Transfer of $', p_amount, ' successful!') AS Message;
END //

-- SCENARIO 2: UpdateSalary
CREATE PROCEDURE UpdateSalary(
    IN p_emp_id INT,
    IN p_percent DECIMAL(5,2)
)
BEGIN
    DECLARE v_count INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'ERROR: Update failed!' AS Message;
    END;

    SELECT COUNT(*) INTO v_count FROM Employees WHERE EmployeeID = p_emp_id;

    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Employee not found!';
    END IF;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percent / 100)
    WHERE EmployeeID = p_emp_id;

    SELECT CONCAT('Salary updated for Employee ID: ', p_emp_id) AS Message;
END //

-- SCENARIO 3: AddNewCustomer
CREATE PROCEDURE AddNewCustomer(
    IN p_id      INT,
    IN p_name    VARCHAR(100),
    IN p_dob     DATE,
    IN p_balance DECIMAL(10,2)
)
BEGIN
    DECLARE v_count INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'ERROR: Insert failed!' AS Message;
    END;

    SELECT COUNT(*) INTO v_count FROM Customers WHERE CustomerID = p_id;

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Customer ID already exists!';
    END IF;

    INSERT INTO Customers VALUES(p_id, p_name, p_dob, p_balance, NOW(), 'FALSE');
    SELECT CONCAT('Customer ', p_name, ' added!') AS Message;
END //

DELIMITER ;

-- Test
CALL SafeTransferFunds(2, 1, 500);
CALL SafeTransferFunds(1, 2, 99999);
CALL UpdateSalary(1, 10);
CALL UpdateSalary(99, 10);
CALL AddNewCustomer(5, 'New Customer', '2000-01-01', 2000);
CALL AddNewCustomer(1, 'Duplicate', '2000-01-01', 2000);