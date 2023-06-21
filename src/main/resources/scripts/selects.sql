SELECT ap.id, ap.client_id, ap.doctor_id, ap.date_time, ap.summary, usr.first_name, usr.last_name, docs.first_name, docs.last_name
FROM appointments ap
    INNER JOIN users usr on usr.id = ap.client_id
    INNER JOIN doctors docs on docs.id = ap.doctor_id
WHERE client_id = ?  ORDER BY date_time;


SELECT ap.id, ap.client_id, ap.doctor_id, ap.date_time, ap.summary, usr.first_name, usr.last_name, docs.first_name, docs.last_name
FROM appointments ap
    INNER JOIN users usr on usr.id = ap.client_id
    INNER JOIN doctors docs on docs.id = ap.doctor_id
WHERE doctor_id = ?  ORDER BY date_time;

SELECT ap.id, ap.client_id, ap.doctor_id, ap.date_time, ap.summary, usr.first_name, usr.last_name, docs.first_name, docs.last_name
FROM appointments ap
    INNER JOIN users usr on usr.id = ap.client_id
    INNER JOIN doctors docs on docs.id = ap.doctor_id
WHERE doctor_id = ?  ORDER BY date_time;

SELECT * FROM doctors WHERE email = '';
SELECT * FROM doctors WHERE phone_number = '';
select * from users where email = '' and user_role = 'admin';
select * from users where user_role = 'client' ORDER BY last_name = '';
select * from users where email = '';
select * from users where email = '' and user_role = 'client';
select * from users where phone_number = '' and user_role = 'client'
