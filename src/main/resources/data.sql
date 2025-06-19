-- Sample data for Horse Feed Service

-- Insert sample horses
INSERT INTO horses (name, breed, age, weight, activity_level) VALUES
('Thunder', 'Thoroughbred', 8, 520.5, 'HIGH'),
('Bella', 'Quarter Horse', 6, 480.0, 'MODERATE'),
('Spirit', 'Arabian', 12, 450.75, 'LOW'),
('Ranger', 'Appaloosa', 4, 510.25, 'HIGH'),
('Luna', 'Paint Horse', 9, 495.0, 'MODERATE');

-- Insert sample feed types
INSERT INTO feed_types (name, brand, calories_per_kg, protein_percentage, fiber_percentage, fat_percentage, description) VALUES
('Premium Horse Pellets', 'EquiNutrition', 2800.0, 14.0, 18.0, 4.0, 'High-quality pellets for active horses'),
('Senior Horse Feed', 'Golden Oats', 2600.0, 16.0, 15.0, 6.0, 'Specially formulated for older horses'),
('Performance Mix', 'Champion Feed', 3200.0, 18.0, 12.0, 8.0, 'High-energy feed for competition horses'),
('Maintenance Cubes', 'Country Feed', 2400.0, 12.0, 20.0, 3.0, 'Basic nutrition for light work horses'),
('Alfalfa Hay', 'Green Valley', 2200.0, 20.0, 32.0, 2.5, 'Premium quality alfalfa hay');

-- Insert sample feeding schedules
INSERT INTO feeding_schedules (horse_id, feed_type_id, quantity_kg, feeding_time, frequency, start_date, notes) VALUES
(1, 3, 2.5, '07:00:00', 'DAILY', '2025-06-01', 'Morning feed for Thunder - high performance'),
(1, 5, 1.0, '18:00:00', 'DAILY', '2025-06-01', 'Evening hay for Thunder'),
(2, 1, 2.0, '08:00:00', 'DAILY', '2025-06-01', 'Morning pellets for Bella'),
(2, 4, 1.5, '17:00:00', 'DAILY', '2025-06-01', 'Evening maintenance feed'),
(3, 2, 1.8, '09:00:00', 'DAILY', '2025-06-01', 'Senior feed for Spirit'),
(4, 3, 2.8, '06:30:00', 'DAILY', '2025-06-01', 'Early morning performance feed'),
(5, 1, 2.2, '07:30:00', 'DAILY', '2025-06-01', 'Standard morning feed for Luna');

-- Insert sample feeding logs
INSERT INTO feeding_logs (schedule_id, fed_at, quantity_given, fed_by, notes) VALUES
(1, '2025-06-18 07:05:00', 2.5, 'John Smith', 'Fed on time, horse eager'),
(2, '2025-06-18 18:10:00', 1.0, 'Mary Johnson', 'Good appetite'),
(3, '2025-06-18 08:02:00', 2.0, 'John Smith', 'Normal feeding'),
(4, '2025-06-18 17:05:00', 1.5, 'Sarah Wilson', 'Horse finished all feed'),
(5, '2025-06-18 09:00:00', 1.8, 'Mike Davis', 'Senior horse eating well');
