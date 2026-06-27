DELIMITER //

-- SCENARIO 1: Apply 1% discount to loan interest for customers above 60
CREATE PROCEDURE ApplyLoanDiscount()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customerid INT;
    DECLARE v_name VARCHAR(100);
    DECLARE v_dob DATE;
    DECLARE v_loanid INT;
    DECLARE v_rate DECIMAL(5,2);
    DECLARE v_age INT;

    DECLARE cur CURSOR FOR
        SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
        FROM Customers c JOIN Loans l ON c.CustomerID = l.CustomerID;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_customerid, v_name, v_dob, v_loanid, v_rate;
        IF done THEN LEAVE read_loop; END IF;

        SET v_age = TIMESTAMPDIFF(YEAR, v_dob, CURDATE());

        IF v_age > 60 THEN
            UPDATE Loans SET InterestRate = InterestRate - 1
            WHERE LoanID = v_loanid;
            SELECT CONCAT('Customer: ', v_name, ' | Age: ', v_age,
                          ' | New Rate: ', (v_rate - 1), '%') AS Message;
        END IF;
    END LOOP;
    CLOSE cur;
END //

-- SCENARIO 2: Set IsVIP = TRUE for balance > 10000
CREATE PROCEDURE SetVIPStatus()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_id INT;
    DECLARE v_name VARCHAR(100);
    DECLARE v_balance DECIMAL(10,2);

    DECLARE cur CURSOR FOR
        SELECT CustomerID, Name, Balance FROM Customers;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_id, v_name, v_balance;
        IF done THEN LEAVE read_loop; END IF;

        IF v_balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE'
            WHERE CustomerID = v_id;
            SELECT CONCAT(v_name, ' → VIP! Balance: $', v_balance) AS Message;
        ELSE
            SELECT CONCAT(v_name, ' → Not VIP. Balance: $', v_balance) AS Message;
        END IF;
    END LOOP;
    CLOSE cur;
END //

-- SCENARIO 3: Send reminders for loans due in 30 days
CREATE PROCEDURE LoanReminders()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_name VARCHAR(100);
    DECLARE v_loanid INT;
    DECLARE v_enddate DATE;
    DECLARE v_amount DECIMAL(10,2);

    DECLARE cur CURSOR FOR
        SELECT c.Name, l.LoanID, l.EndDate, l.LoanAmount
        FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_name, v_loanid, v_enddate, v_amount;
        IF done THEN LEAVE read_loop; END IF;
        SELECT CONCAT('REMINDER: Dear ', v_name,
            ', Loan #', v_loanid,
            ' of $', v_amount,
            ' due on ', v_enddate) AS Reminder;
    END LOOP;
    CLOSE cur;
END //

DELIMITER ;

-- Run all 3
CALL ApplyLoanDiscount();
CALL SetVIPStatus();
CALL LoanReminders();