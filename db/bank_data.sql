INSERT INTO public.customer (id, first_name, last_name, personal_identification_code) VALUES (DEFAULT, 'Kadi', 'Supermann', '40000000001');
INSERT INTO public.customer (id, first_name, last_name, personal_identification_code) VALUES (DEFAULT, 'Aleksei', 'Roland', '30000000002');
INSERT INTO public.customer (id, first_name, last_name, personal_identification_code) VALUES (DEFAULT, 'Mart', 'Tamm', '30000000003');
INSERT INTO public.customer (id, first_name, last_name, personal_identification_code) VALUES (DEFAULT, 'Keit', 'Klaabu', '40000000004');

INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE123', 1, 50, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE456', 3, 20, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE666', 2, 30, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE999', 4, 200, false);

INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 100, 'd', 100, '2022-01-27 22:51:18.000000', 1, 'ATM', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 200, 'd', 200, '2022-01-27 22:51:18.000000', 4, 'ATM', 'EE999');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 80, 's', 20, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE456');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 20, 'r', 20, '2022-01-27 22:51:18.000000', 3, 'EE456', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 60, 's', 20, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE666');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 20, 'r', 20, '2022-01-27 22:51:18.000000', 2, 'EE123', 'EE666');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 50, 's', 10, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE666');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 30, 'r', 10, '2022-01-27 22:51:18.000000', 2, 'EE123', 'EE666');
