DELIMITER //

-- SCENARIO 1: CalculateAge
CREATE FUNCTION CalculateAge(p_dob DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, p_dob, CURDATE());
END //

-- SCENARIO 2: CalculateMonthlyInstallment
CREATE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount   DECIMAL(10,2),
    p_interest_rate DECIMAL(5,2),
    p_years         INT
) RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE v_monthly_rate DECIMAL(10,6);
    DECLARE v_months       INT;
    DECLARE v_emi          DECIMAL(10,2);

    SET v_monthly_rate = p_interest_rate / (12 * 100);
    SET v_months       = p_years * 12;
    SET v_emi = p_loan_amount * v_monthly_rate *
                POW(1 + v_monthly_rate, v_months) /
                (POW(1 + v_monthly_rate, v_months) - 1);
    RETURN ROUND(v_emi, 2);
END //

-- SCENARIO 3: HasSufficientBalance
CREATE FUNCTION HasSufficientBalance(
    p_account_id INT,
    p_amount     DECIMAL(10,2)
) RETURNS VARCHAR(3)
DETERMINISTIC
BEGIN
    DECLARE v_balance DECIMAL(10,2);
    SELECT Balance INTO v_balance
    FROM Accounts WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN 'YES';
    ELSE
        RETURN 'NO';
    END IF;
END //

DELIMITER ;

-- Test
SELECT Name, CalculateAge(DOB) AS Age FROM Customers;
SELECT CalculateMonthlyInstallment(50000, 8, 5) AS Monthly_EMI;
SELECT HasSufficientBalance(1, 500) AS Has_Balance;
SELECT HasSufficientBalance(1, 99999) AS Has_Balance;