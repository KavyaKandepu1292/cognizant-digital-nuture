DELIMITER //

-- SCENARIO 1: CustomerManagement procedures
CREATE PROCEDURE CM_AddNewCustomer(
    IN p_id INT, IN p_name VARCHAR(100),
    IN p_dob DATE, IN p_balance DECIMAL(10,2)
)
BEGIN
    INSERT INTO Customers VALUES(p_id,p_name,p_dob,p_balance,NOW(),'FALSE');
    SELECT CONCAT('Added: ', p_name) AS Message;
END //

CREATE PROCEDURE CM_UpdateCustomerDetails(
    IN p_id INT, IN p_name VARCHAR(100), IN p_balance DECIMAL(10,2)
)
BEGIN
    UPDATE Customers SET Name=p_name, Balance=p_balance
    WHERE CustomerID=p_id;
    SELECT CONCAT('Updated Customer ID: ', p_id) AS Message;
END //

CREATE FUNCTION CM_GetCustomerBalance(p_id INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE v_bal DECIMAL(10,2);
    SELECT Balance INTO v_bal FROM Customers WHERE CustomerID=p_id;
    RETURN v_bal;
END //

-- SCENARIO 2: EmployeeManagement procedures
CREATE PROCEDURE EM_HireEmployee(
    IN p_id INT, IN p_name VARCHAR(100), IN p_pos VARCHAR(50),
    IN p_sal DECIMAL(10,2), IN p_dept VARCHAR(50), IN p_hire DATE
)
BEGIN
    INSERT INTO Employees VALUES(p_id,p_name,p_pos,p_sal,p_dept,p_hire);
    SELECT CONCAT('Hired: ', p_name) AS Message;
END //

CREATE PROCEDURE EM_UpdateEmployeeDetails(
    IN p_id INT, IN p_pos VARCHAR(50), IN p_sal DECIMAL(10,2)
)
BEGIN
    UPDATE Employees SET Position=p_pos, Salary=p_sal
    WHERE EmployeeID=p_id;
    SELECT CONCAT('Updated Employee ID: ', p_id) AS Message;
END //

CREATE FUNCTION EM_CalculateAnnualSalary(p_id INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE v_sal DECIMAL(10,2);
    SELECT Salary * 12 INTO v_sal FROM Employees WHERE EmployeeID=p_id;
    RETURN v_sal;
END //

-- SCENARIO 3: AccountOperations procedures
CREATE PROCEDURE AO_OpenAccount(
    IN p_id INT, IN p_cust_id INT,
    IN p_type VARCHAR(20), IN p_bal DECIMAL(10,2)
)
BEGIN
    INSERT INTO Accounts VALUES(p_id,p_cust_id,p_type,p_bal,NOW());
    SELECT CONCAT('Account opened: ID=', p_id) AS Message;
END //

CREATE PROCEDURE AO_CloseAccount(IN p_id INT)
BEGIN
    DELETE FROM Accounts WHERE AccountID=p_id;
    SELECT CONCAT('Account closed: ID=', p_id) AS Message;
END //

CREATE FUNCTION AO_GetTotalBalance(p_cust_id INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE v_total DECIMAL(10,2);
    SELECT IFNULL(SUM(Balance),0) INTO v_total
    FROM Accounts WHERE CustomerID=p_cust_id;
    RETURN v_total;
END //

DELIMITER ;

-- Test all
CALL CM_AddNewCustomer(6,'Tom Hardy','1995-01-01',3000);
CALL CM_UpdateCustomerDetails(6,'Tom Hardy Updated',5000);
SELECT CM_GetCustomerBalance(6) AS Balance;

CALL EM_HireEmployee(4,'Diana Prince','Tester',50000,'QA',CURDATE());
SELECT EM_CalculateAnnualSalary(1) AS Annual_Salary;

CALL AO_OpenAccount(5,1,'Savings',1000);
SELECT AO_GetTotalBalance(1) AS Total_Balance;
CALL AO_CloseAccount(5);