CREATE OR REPLACE FUNCTION on_income_check_transaction_type_fn()
    RETURNS trigger
    LANGUAGE plpgsql
AS
'
    BEGIN
        IF ''TYPE_INCOME'' NOT IN (SELECT t.type
                                   FROM transaction t
                                   WHERE t.id = NEW.transaction_id) THEN
            RAISE EXCEPTION ''Inserted row not references income typed transaction'';
        END IF;

        RETURN NEW;
    END;
';

CREATE OR REPLACE TRIGGER on_income_check_transaction_type_trigger
    AFTER INSERT
    ON income
    FOR EACH ROW
EXECUTE FUNCTION on_income_check_transaction_type_fn();