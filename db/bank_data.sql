INSERT INTO public.customer (id, first_name, last_name) VALUES (DEFAULT, 'Kadi', 'Supermann');
INSERT INTO public.customer (id, first_name, last_name) VALUES (DEFAULT, 'Aleksei', 'Roland');
INSERT INTO public.customer (id, first_name, last_name) VALUES (DEFAULT, 'Mart', 'Tamm');
INSERT INTO public.customer (id, first_name, last_name) VALUES (DEFAULT, 'Keit', 'Klaabu');

INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE123', 1, 0, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE456', 2, 0, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE666', 3, 0, false);
INSERT INTO public.bank_account (id, account_number, customer_id, balance, locked) VALUES (DEFAULT, 'EE999', 4, 0, false);

INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 0, 'n', 0, '2022-01-27 22:51:18.000000', 1, null, null);
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 100, 'd', 100, '2022-01-27 22:51:18.000000', 1, 'ATM', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 200, 'd', 200, '2022-01-27 22:51:18.000000', 4, 'ATM', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 0, 'n', 0, '2022-01-27 22:51:18.000000', 2, null, null);
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 0, 'n', 0, '2022-01-27 22:51:18.000000', 3, null, null);
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 0, 'n', 0, '2022-01-27 22:51:18.000000', 4, null, null);
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 80, 's', 20, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE456');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 20, 'r', 20, '2022-01-27 22:51:18.000000', 2, 'EE456', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 60, 's', 20, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE666');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 20, 'r', 20, '2022-01-27 22:51:18.000000', 3, 'EE666', 'EE123');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 50, 's', 10, '2022-01-27 22:51:18.000000', 1, 'EE123', 'EE666');
INSERT INTO public.bank_transaction (id, balance, type, amount, transaction_date_time, bank_account_id, sender_account_number, receiver_account_number) VALUES (DEFAULT, 30, 'r', 10, '2022-01-27 22:51:18.000000', 3, 'EE666', 'EE123');
