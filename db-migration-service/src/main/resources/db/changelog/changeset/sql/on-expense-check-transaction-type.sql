CREATE OR REPLACE FUNCTION on_expense_check_transaction_type_fn()
    RETURNS trigger
    LANGUAGE plpgsql
AS
'
    BEGIN
        IF ''TYPE_EXPENSE'' NOT IN (SELECT t.type
                                    FROM transaction t
                                    WHERE t.id = NEW.transaction_id) THEN
            RAISE EXCEPTION ''Inserted row not references expense typed transaction'';
        END IF;

        RETURN NEW;
    END;
';

CREATE OR REPLACE TRIGGER on_expense_check_transaction_type_trigger
    AFTER INSERT
    ON expense
    FOR EACH ROW
EXECUTE FUNCTION on_expense_check_transaction_type_fn();