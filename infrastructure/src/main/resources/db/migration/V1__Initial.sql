
CREATE TABLE short_url (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    target VARCHAR(255) NOT NULL,
    validity DATETIME(6) NOT NULL,
    created_at DATETIME(6) NOT NULL
);