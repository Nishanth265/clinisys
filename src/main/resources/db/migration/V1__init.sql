CREATE TABLE users (
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT chk_users_role CHECK (role IN ('PATIENT', 'DOCTOR', 'ADMIN'))
);

CREATE TABLE doctor (
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    user_id        BIGINT       NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_doctor_user UNIQUE (user_id),
    CONSTRAINT fk_doctor_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE slot (
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    doctor_id  BIGINT      NOT NULL,
    booked_by  BIGINT      NULL,
    start_time DATETIME(6) NOT NULL,
    end_time   DATETIME(6) NOT NULL,
    booked_at  DATETIME(6) NULL,
    status     VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    PRIMARY KEY (id),
    CONSTRAINT fk_slot_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id),
    CONSTRAINT fk_slot_booked_by FOREIGN KEY (booked_by) REFERENCES users (id),
    CONSTRAINT chk_slot_status CHECK (status IN ('AVAILABLE', 'BOOKED')),
    CONSTRAINT chk_slot_time CHECK (end_time > start_time),
    CONSTRAINT chk_slot_booking CHECK (
        (status = 'AVAILABLE'
            AND booked_by IS NULL
            AND booked_at IS NULL)
        OR
        (status = 'BOOKED'
            AND booked_by IS NOT NULL
            AND booked_at IS NOT NULL)
    )
);

CREATE INDEX idx_slot_doctor_start
    ON slot (doctor_id, start_time);