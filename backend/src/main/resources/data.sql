-- Initial test data for Pet Adoption Platform

-- Create test user (password: password123)
INSERT INTO users (username, email, password, phone, city, created_at)
VALUES ('testuser', 'test@example.com', '$2a$10$8K1p/a0dL5rLQz/O5YZ3KeY5j/a0dL5rLQz/O5YZ3KeY5j/a0dL5r', '1234567890', 'Shanghai', NOW());

-- Create test shelters
INSERT INTO shelters (name, description, logo_url, phone, email, address, city, created_at)
VALUES
('Happy Paws Shelter', 'A loving shelter for abandoned pets', 'https://example.com/logo1.png', '1234567890', 'happypaws@example.com', '123 Pet Street, Shanghai', 'Shanghai', NOW()),
('Second Chance Animal Rescue', 'Giving animals a second chance at life', 'https://example.com/logo2.png', '0987654321', 'secondchance@example.com', '456 Animal Ave, Beijing', 'Beijing', NOW()),
('Furry Friends Foundation', 'Dedicated to finding forever homes', 'https://example.com/logo3.png', '1112223333', 'furryfriends@example.com', '789 Hope Road, Guangzhou', 'Guangzhou', NOW());

-- Create test pets
INSERT INTO pets (name, species, breed, gender, age, age_detail, weight, description, temperament, health_status, vaccinated, neutered, microchipped, images, video_url, shelter_id, status, is_featured, category, created_at, updated_at)
VALUES
('Max', 'dog', 'Golden Retriever', 'male', 'adult', '3 years', 25.5, 'Max is a friendly and energetic Golden Retriever who loves to play fetch and go for long walks. He is great with children and other dogs.', 'Friendly, Loyal, Playful', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1552053831-71594a27632d?w=400", "https://images.unsplash.com/photo-1561037404-61cd46aa615b?w=400"]', NULL, 1, 'available', TRUE, 'dog', NOW(), NOW()),

('Luna', 'cat', 'Persian', 'female', 'adult', '2 years', 4.2, 'Luna is a beautiful Persian cat with a calm and gentle personality. She loves to be pampered and enjoys quiet moments.', 'Calm, Gentle, Affectionate', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400", "https://images.unsplash.com/photo-1573865526739-10659fec78a5?w=400"]', NULL, 1, 'available', TRUE, 'cat', NOW(), NOW()),

('Charlie', 'dog', 'Labrador', 'male', 'puppy', '8 months', 18.3, 'Charlie is an adorable Labrador puppy who is very intelligent and eager to learn. He would make a great family pet.', 'Intelligent, Energetic, Obedient', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400", "https://images.unsplash.com/photo-1591160690555-5debfba289f0?w=400"]', NULL, 2, 'available', TRUE, 'dog', NOW(), NOW()),

('Bella', 'cat', 'Siamese', 'female', 'young', '1 year', 3.8, 'Bella is an active Siamese cat who loves to climb and explore. She is very vocal and loves to interact with her humans.', 'Active, Vocal, Curious', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1592194996308-7b43878e84a6?w=400", "https://images.unsplash.com/photo-1526336024174-e58f5cdd8e13?w=400"]', NULL, 1, 'available', FALSE, 'cat', NOW(), NOW()),

('Cooper', 'dog', 'Border Collie', 'male', 'adult', '4 years', 22.0, 'Cooper is a highly intelligent Border Collie who excels at agility training. He needs an active family who can give him lots of exercise.', 'Intelligent, Active, Athletic', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1503256207526-0d5d80fa2f47?w=400", "https://images.unsplash.com/photo-1518717758536-85ae29035b6d?w=400"]', NULL, 2, 'available', FALSE, 'dog', NOW(), NOW()),

('Coco', 'rabbit', 'Holland Lop', 'female', 'young', '6 months', 1.5, 'Coco is a adorable Holland Lop rabbit with floppy ears. She is very friendly and loves to be held and petted.', 'Friendly, Gentle, Calm', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400", "https://images.unsplash.com/photo-1535241749838-299f6e571ec3?w=400"]', NULL, 3, 'available', TRUE, 'rabbit', NOW(), NOW()),

('Rocky', 'dog', 'German Shepherd', 'male', 'adult', '5 years', 30.0, 'Rocky is a loyal and protective German Shepherd. He is well-trained and would make an excellent guard dog and family companion.', 'Loyal, Protective, Obedient', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?w=400", "https://images.unsplash.com/photo-1568572933382-74d440642117?w=400"]', NULL, 1, 'available', TRUE, 'dog', NOW(), NOW()),

('Milo', 'cat', 'British Shorthair', 'male', 'kitten', '4 months', 2.5, 'Milo is a cute British Shorthair kitten with a plush coat. He is playful and gets along well with other cats.', 'Playful, Curious, Friendly', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1574158622682-e40e69881006?w=400", "https://images.unsplash.com/photo-1495360010541-f48722b34f7d?w=400"]', NULL, 2, 'available', FALSE, 'cat', NOW(), NOW()),

('Daisy', 'dog', 'Beagle', 'female', 'adult', '2 years', 10.5, 'Daisy is a sweet Beagle who loves to sniff and explore. She is great with kids and enjoys outdoor activities.', 'Sweet, Curious, Friendly', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1505628346881-b72b27e84530?w=400", "https://images.unsplash.com/photo-1537151608828-ea2b11777ee8?w=400"]', NULL, 3, 'available', FALSE, 'dog', NOW(), NOW()),

('Whiskers', 'cat', 'Maine Coon', 'male', 'adult', '3 years', 6.5, 'Whiskers is a majestic Maine Coon with a gentle giant personality. He loves to cuddle and is great with families.', 'Gentle, Affectionate, Calm', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1606214174585-fe31582dc6ee?w=400", "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?w=400"]', NULL, 1, 'available', TRUE, 'cat', NOW(), NOW()),

('Buddy', 'dog', 'Poodle', 'male', 'puppy', '10 months', 8.0, 'Buddy is an adorable Poodle puppy who is very smart and hypoallergenic. Perfect for families with allergies.', 'Smart, Playful, Hypoallergenic', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1616149256706-3c560b556b12?w=400", "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=400"]', NULL, 2, 'available', FALSE, 'dog', NOW(), NOW()),

('Snowball', 'rabbit', 'Netherland Dwarf', 'female', 'adult', '1 year', 1.2, 'Snowball is a cute white Netherland Dwarf rabbit. She is gentle and easy to care for.', 'Gentle, Quiet, Easy-going', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1591382696684-38c127d1e81b?w=400", "https://images.unsplash.com/photo-1555685812-4b943f1cb0eb?w=400"]', NULL, 3, 'available', FALSE, 'rabbit', NOW(), NOW());