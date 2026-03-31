-- Initial test data for Pet Adoption Platform

-- Delete existing pets to avoid duplicates
DELETE FROM pets;

-- Create test user (password: 123456) - only if not exists
INSERT IGNORE INTO users (username, email, password, phone, city, created_at)
VALUES ('testuser', 'test@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '18900000000', 'Shanghai', NOW());

-- Create test shelters - only if not exists
INSERT IGNORE INTO shelters (name, description, logo_url, phone, email, address, city, created_at)
VALUES
('Happy Paws Shelter', 'A loving shelter for abandoned pets', 'https://example.com/logo1.png', '1234567890', 'happypaws@example.com', '123 Pet Street, Shanghai', 'Shanghai', NOW()),
('Second Chance Animal Rescue', 'Giving animals a second chance at life', 'https://example.com/logo2.png', '0987654321', 'secondchance@example.com', '456 Animal Ave, Beijing', 'Beijing', NOW()),
('Furry Friends Foundation', 'Dedicated to finding forever homes', 'https://example.com/logo3.png', '1112223333', 'furryfriends@example.com', '789 Hope Road, Guangzhou', 'Guangzhou', NOW());

-- Create test pets
INSERT IGNORE INTO pets (name, species, breed, gender, age, age_detail, weight, description, temperament, health_status, vaccinated, neutered, microchipped, images, video_url, shelter_id, status, is_featured, category, created_at, updated_at)
VALUES
('马克斯', 'dog', 'Golden Retriever', 'male', 'adult', '3 years', 25.5, '马克斯是一只友好且充满活力的金毛寻回犬，喜欢玩接球游戏和长时间散步。它对小孩和其他狗狗都非常友善。', 'Friendly, Loyal, Playful', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1552053831-71594a27632d?w=400", "https://images.unsplash.com/photo-1561037404-61cd46aa615b?w=400"]', NULL, 1, 'available', TRUE, 'new', NOW(), NOW()),

('露娜', 'cat', 'Persian', 'female', 'adult', '2 years', 4.2, '露娜是一只美丽的波斯猫，性格温柔安静。它喜欢被宠爱，享受安静的时光。', 'Calm, Gentle, Affectionate', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400", "https://images.unsplash.com/photo-1573865526739-10659fec78a5?w=400"]', NULL, 1, 'available', TRUE, 'companion', NOW(), NOW()),

('查理', 'dog', 'Labrador', 'male', 'puppy', '8 months', 18.3, '查理是一只可爱的拉布拉多幼犬，智商很高且渴望学习。它会是一个出色的家庭宠物。', 'Intelligent, Energetic, Obedient', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 2, 'available', TRUE, 'new', NOW(), NOW()),

('贝拉', 'cat', 'Siamese', 'female', 'young', '1 year', 3.8, '贝拉是一只活泼的暹罗猫，喜欢攀爬和探索。它非常爱叫，喜欢与人类互动。', 'Active, Vocal, Curious', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1513245543132-31f507417b26?w=400", "https://images.unsplash.com/photo-1574158622682-e40e69881006?w=400"]', NULL, 1, 'available', FALSE, 'companion', NOW(), NOW()),

('库珀', 'dog', 'Border Collie', 'male', 'adult', '4 years', 22.0, '库珀是一只非常聪明的边境牧羊犬，擅长敏捷训练。它需要一个能给它大量运动量的活跃家庭。', 'Intelligent, Active, Athletic', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1503256207526-0d5d80fa2f47?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 2, 'available', FALSE, 'adoptable', NOW(), NOW()),

('可可', 'rabbit', 'Holland Lop', 'female', 'young', '6 months', 1.5, '可可是一只可爱的荷兰垂耳兔，耳朵下垂。它非常友好，喜欢被人抱和抚摸。', 'Friendly, Gentle, Calm', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400", "https://images.unsplash.com/photo-1515488042361-25f468213828?w=400"]', NULL, 3, 'available', TRUE, 'special', NOW(), NOW()),

('洛奇', 'dog', 'German Shepherd', 'male', 'adult', '5 years', 30.0, '洛奇是一只忠诚且护主的德国牧羊犬。它训练有素，是一个优秀的护卫犬和家庭伙伴。', 'Loyal, Protective, Obedient', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1589941013453-ec89f33b5e95?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 1, 'available', TRUE, 'adoptable', NOW(), NOW()),

('米洛', 'cat', 'British Shorthair', 'male', 'kitten', '4 months', 2.5, '米洛是一只可爱的英国短毛猫幼崽，拥有浓密的被毛。它活泼好动，与其他猫咪相处融洽。', 'Playful, Curious, Friendly', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1574158622682-e40e69881006?w=400", "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?w=400"]', NULL, 2, 'available', FALSE, 'new', NOW(), NOW()),

('黛西', 'dog', 'Beagle', 'female', 'adult', '2 years', 10.5, '黛西是一只可爱的比格犬，喜欢嗅闻和探索。它对小孩很友好，喜欢户外活动。', 'Sweet, Curious, Friendly', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1505628346881-b72b27e84530?w=400", "https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400"]', NULL, 3, 'available', FALSE, 'companion', NOW(), NOW()),

('威斯克', 'cat', 'Maine Coon', 'male', 'adult', '3 years', 6.5, '威斯克是一只威严的缅因猫，性格温和如巨人。它喜欢被拥抱，对家庭非常友好。', 'Gentle, Affectionate, Calm', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1615796153287-98eacf0abb13?w=400", "https://images.unsplash.com/photo-1606214174585-fe31582dc6ee?w=400"]', NULL, 1, 'available', TRUE, 'special', NOW(), NOW()),

('巴迪', 'dog', 'Poodle', 'male', 'puppy', '10 months', 8.0, '巴迪是一只可爱的贵宾犬幼犬，非常聪明且低过敏原。非常适合有过敏体质的家庭。', 'Smart, Playful, Hypoallergenic', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 2, 'available', FALSE, 'new', NOW(), NOW()),

('雪球', 'rabbit', 'Netherland Dwarf', 'female', 'adult', '1 year', 1.2, '雪球是一只可爱的白色荷兰侏儒兔。它性格温和，容易照顾。', 'Gentle, Quiet, Easy-going', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400", "https://images.unsplash.com/photo-1452857297128-d9c29adba80b?w=400"]', NULL, 3, 'available', FALSE, 'adoptable', NOW(), NOW()),

('奥利弗', 'cat', 'Ragdoll', 'male', 'adult', '2 years', 5.0, '奥利弗是一只迷人的布偶猫，喜欢瘫倒在人怀里。它非常亲人，适合家庭饲养。', 'Affectionate, Calm, Docile', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1574158622682-e40e69881006?w=400", "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400"]', NULL, 1, 'available', TRUE, 'companion', NOW(), NOW()),

('萨迪', 'dog', 'French Bulldog', 'female', 'adult', '3 years', 12.0, '萨迪是一只可爱的法国斗牛犬，性格活泼。非常适合公寓生活。', 'Playful, Loyal, Alert', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400", "https://images.unsplash.com/photo-1591160690555-5debfba289f0?w=400"]', NULL, 2, 'available', FALSE, 'adoptable', NOW(), NOW()),

('莱奥', 'dog', 'Jack Russell Terrier', 'male', 'young', '1.5 years', 8.5, '莱奥是一只精力充沛的杰克罗素梗，喜欢玩耍和探索。需要一个活跃的主人。', 'Energetic, Brave, Intelligent', 'Healthy', TRUE, TRUE, FALSE, '["https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 3, 'available', TRUE, 'special', NOW(), NOW()),

('姜饼', 'cat', 'Tabby', 'female', 'kitten', '5 months', 2.2, '姜饼是一只活泼的虎斑猫幼崽，喜欢追逐玩具和攀爬。', 'Playful, Curious, Active', 'Healthy', TRUE, FALSE, FALSE, '["https://images.unsplash.com/photo-1574158622682-e40e69881006?w=400", "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?w=400"]', NULL, 1, 'available', FALSE, 'new', NOW(), NOW()),

('公爵', 'dog', 'Great Dane', 'male', 'adult', '4 years', 65.0, '公爵是一只温柔的巨人大丹犬。尽管体型庞大，它性格非常温和，喜欢被人拥抱。', 'Gentle, Friendly, Patient', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1559190394-df5a28aab5c5?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 2, 'available', TRUE, 'companion', NOW(), NOW()),

('佩珀', 'rabbit', 'Mini Rex', 'male', 'young', '8 months', 1.8, '佩珀是一只可爱的迷你雷克斯兔，拥有丝绒般的皮毛。它友好喜欢探索。', 'Friendly, Curious, Active', 'Healthy', TRUE, FALSE, TRUE, '["https://images.unsplash.com/photo-1452857297128-d9c29adba80b?w=400", "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400"]', NULL, 3, 'available', FALSE, 'special', NOW(), NOW()),

('影子', 'cat', 'Black Domestic Shorthair', 'male', 'adult', '5 years', 4.5, '影子是一只光滑的黑色家猫，有着一双锐利的黄色眼睛。它非常安静且独立。', 'Quiet, Independent, Loyal', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400", "https://images.unsplash.com/photo-1573865526739-10659fec78a5?w=400"]', NULL, 1, 'available', TRUE, 'adoptable', NOW(), NOW()),

('露比', 'dog', 'Cocker Spaniel', 'female', 'adult', '2.5 years', 12.5, '露比是一只美丽的可卡犬，性格甜美。它喜欢被人拥抱和宠爱。', 'Sweet, Affectionate, Gentle', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400", "https://images.unsplash.com/photo-1552053831-71594a27632d?w=400"]', NULL, 2, 'available', FALSE, 'companion', NOW(), NOW()),

('翠丝', 'bird', 'Cockatiel', 'female', 'young', '6 months', 0.1, '翠丝是一只可爱的凤头鹦鹉，喜欢吹口哨。它非常社交化，喜欢被人关注。', 'Social, Musical, Friendly', 'Healthy', TRUE, FALSE, TRUE, '["https://images.unsplash.com/photo-1552728089-57bdde30beb3?w=400", "https://images.unsplash.com/photo-1591608971362-f08b2a3f10f2?w=400"]', NULL, 3, 'available', TRUE, 'new', NOW(), NOW()),

('芬恩', 'dog', 'Shiba Inu', 'male', 'puppy', '7 months', 7.0, '芬恩是一只可爱的柴犬幼犬，有着像狐狸一样的外表。它精力充沛且独立。', 'Spirited, Independent, Alert', 'Healthy', TRUE, FALSE, TRUE, '["https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=400", "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?w=400"]', NULL, 1, 'available', TRUE, 'new', NOW(), NOW()),

('桂皮', 'rabbit', 'Lionhead', 'female', 'adult', '1.5 years', 1.6, '桂皮是一只毛茸茸的狮子兔，性格迷人。它喜欢被人梳理毛发。', 'Gentle, Affectionate, Calm', 'Healthy', TRUE, TRUE, TRUE, '["https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400", "https://images.unsplash.com/photo-1452857297128-d9c29adba80b?w=400"]', NULL, 2, 'available', FALSE, 'special', NOW(), NOW());