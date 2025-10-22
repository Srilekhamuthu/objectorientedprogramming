DROP TABLE matches;
CREATE TABLE matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team1 VARCHAR(100) NOT NULL,
    team2 VARCHAR(100) NOT NULL,
    team1_score INT DEFAULT 0,
    team2_score INT DEFAULT 0,
    sport VARCHAR(50),
    status VARCHAR(20) DEFAULT 'live',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO matches (team1, team2, team1_score, team2_score, sport, status) 
VALUES 
('India', 'Australia', 250, 180, 'Cricket', 'live'),
('Barcelona', 'Real Madrid', 2, 1, 'Football', 'live');