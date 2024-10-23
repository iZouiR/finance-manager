CREATE
    OR REPLACE FUNCTION on_transaction_update_limitation_spent_fn()
    RETURNS trigger
    LANGUAGE plpgsql
AS
'
    DECLARE
        _l_rec limitation;
    BEGIN
        IF NEW.type = ''TYPE_EXPENSE'' THEN
            FOR _l_rec IN (SELECT *
                           FROM limitation l
                           WHERE l.account_id = NEW.account_id
                             AND l.start_date < NOW()
                             AND l.end_date > NOW()
                             AND l.reached = false)
                LOOP
                    UPDATE limitation l
                    SET spent = spent + NEW.amount
                    WHERE l.account_id = NEW.account_id;
                END LOOP;
        END IF;
        RETURN NEW;
    END;
';

CREATE
    OR REPLACE TRIGGER on_transaction_update_limitation_spent_trigger
    AFTER INSERT
    ON transaction
    FOR EACH ROW
EXECUTE FUNCTION on_transaction_update_limitation_spent_fn();