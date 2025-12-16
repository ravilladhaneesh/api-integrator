INSERT INTO api_config (id, app_name, base_url, auth_type, headers)
VALUES (1, 'CALENDLY', 'https://api.calendly.com', 'BEARER',
'{"Authorization":"Bearer xxxx"}');

INSERT INTO api_request_config (id, app_name, endpoint, http_method, response_mapping)
VALUES (1, 'CALENDLY', '/users/me', 'GET',
'{"id":"$/resource/uri","email":"$/resource/email","name":"$/resource/name"}');