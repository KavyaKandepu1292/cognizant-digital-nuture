DELIMITER //

-- SCENARIO 1: GenerateMonthlyStatements
CREATE PROCEDURE GenerateMonthlyStatements()
BEGIN
    SELECT c.Name, t.TransactionID, t.Amount,
           t.TransactionType, t.TransactionDate
    FROM Customers c
    JOIN Accounts a     ON c.CustomerID = a.CustomerID
    JOIN Transactions t ON a.AccountID  = t.AccountID
    WHERE MONTH(t.TransactionDate) = MONTH(CURDATE())
    AND   YEAR(t.TransactionDate)  = YEAR(CURDATE());
END //

-- SCENARIO 2: ApplyAnnualFee
CREATE PROCEDURE ApplyAnnualFee()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_accid INT;
    DECLARE v_bal   DECIMAL(10,2);
    DECLARE v_fee   DECIMAL(10,2) DEFAULT 50.00;

    DECLARE cur CURSOR FOR
        SELECT AccountID, Balance FROM Accounts;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_accid, v_bal;
        IF done THEN LEAVE read_loop; END IF;

        UPDATE Accounts SET Balance = Balance - v_fee
        WHERE AccountID = v_accid;

        SELECT CONCAT('Account ', v_accid,
                      ': Fee deducted. New Balance=$',
                      (v_bal - v_fee)) AS Message;
    END LOOP;
    CLOSE cur;
END //

-- SCENARIO 3: UpdateLoanInterestRates
CREATE PROCEDURE UpdateLoanInterestRates()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_loanid INT;
    DECLARE v_rate   DECIMAL(5,2);

    DECLARE cur CURSOR FOR
        SELECT LoanID, InterestRate FROM Loans;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_loanid, v_rate;
        IF done THEN LEAVE read_loop; END IF;

        UPDATE Loans SET InterestRate = InterestRate + 0.5
        WHERE LoanID = v_loanid;

        SELECT CONCAT('Loan #', v_loanid,
                      ': Rate updated to ',
                      (v_rate + 0.5), '%') AS Message;
    END LOOP;
    CLOSE cur;
END //

DELIMITER ;

-- Test
CALL GenerateMonthlyStatements();
CALL ApplyAnnualFee();
CALL UpdateLoanInterestRates();