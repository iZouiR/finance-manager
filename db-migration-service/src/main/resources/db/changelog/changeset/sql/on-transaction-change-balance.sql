CREATE OR REPLACE FUNCTION on_transaction_change_balance_fn()
    RETURNS trigger
    LANGUAGE plpgsql
AS
'
    DECLARE
        _difference NUMERIC;
    BEGIN
        IF NEW.type = ''TYPE_INCOME'' THEN
            _difference = NEW.amount;
        ELSEIF NEW.type = ''TYPE_EXPENSE'' THEN
            _difference = -NEW.amount;
        END IF;

        UPDATE account
        SET balance = balance + _difference
        WHERE account.id = NEW.account_id;

        RETURN NEW;
    END;
';

CREATE OR REPLACE TRIGGER on_transaction_change_balance_trigger
    AFTER INSERT
    ON transaction
    FOR EACH ROW
EXECUTE FUNCTION on_transaction_change_balance_fn();