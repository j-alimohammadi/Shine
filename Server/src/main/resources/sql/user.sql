# Sample User
INSERT INTO SHINE_USER (ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUDIATION)
VALUES (-1, true, 'smith', 'smith@domain.com', '$2a$10$wgsBKRLSrt0JdEM1gRK0xuhwSflI3O/B9S6uiPs2z7VIsjZnqKz4q',
        CURRENT_TIMESTAMP(), 0);

# Anonymous User
INSERT INTO SHINE_USER (ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUDIATION)
VALUES (-2, true, 'anonymous_user', 'anonymous@domain.com', 'not_use', CURRENT_TIMESTAMP(), 0);
