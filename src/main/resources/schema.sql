-- Horse Feed Service Database Schema

-- Horses table
CREATE TABLE horses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    breed VARCHAR(50),
    age INTEGER,
    weight DECIMAL(5,2),
    activity_level VARCHAR(20) DEFAULT 'MODERATE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Feed types table
CREATE TABLE feed_types (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(50),
    calories_per_kg DECIMAL(8,2),
    protein_percentage DECIMAL(5,2),
    fiber_percentage DECIMAL(5,2),
    fat_percentage DECIMAL(5,2),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Feeding schedule table
CREATE TABLE feeding_schedules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    horse_id BIGINT NOT NULL,
    feed_type_id BIGINT NOT NULL,
    quantity_kg DECIMAL(5,2) NOT NULL,
    feeding_time TIME NOT NULL,
    frequency VARCHAR(20) DEFAULT 'DAILY',
    start_date DATE NOT NULL,
    end_date DATE,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (horse_id) REFERENCES horses(id),
    FOREIGN KEY (feed_type_id) REFERENCES feed_types(id)
);

-- Feeding logs table
CREATE TABLE feeding_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    schedule_id BIGINT NOT NULL,
    fed_at TIMESTAMP NOT NULL,
    quantity_given DECIMAL(5,2) NOT NULL,
    notes TEXT,
    fed_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (schedule_id) REFERENCES feeding_schedules(id)
);
