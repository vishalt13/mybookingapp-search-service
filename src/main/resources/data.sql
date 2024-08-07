INSERT INTO partner(partner_id, partner_name) VALUES (1, 'ADLABS');
INSERT INTO partner(partner_id, partner_name) VALUES (2, 'PVR');
INSERT INTO partner(partner_id, partner_name) VALUES (3, 'INOX');

INSERT INTO Location (location_id, city, state, country) VALUES (1, 'Pune', 'Maharashtra', 'India');
INSERT INTO Location (location_id, city, state, country) VALUES (2, 'Bengaluru', 'Karnataka', 'India');
INSERT INTO Location (location_id, city, state, country) VALUES (3, 'Noida', 'Uttar Pradesh', 'India');

INSERT INTO Theatre (theatre_id, partner_theatre_id, name, zipcode, address, partner_id, location_id) 
	VALUES (1, 'PVR-009', 'Westend Multiplex', '411094', 'Parihar chowk, Aundh', 2, 1);
INSERT INTO Theatre (theatre_id, partner_theatre_id, name, zipcode, address, partner_id, location_id) 
	VALUES (2, 'ADLB-079', 'Galaxy Adlabs', '411057', 'Shivaji chowk, Hinjewadi', 1, 1);
INSERT INTO Theatre (theatre_id, partner_theatre_id, name, zipcode, address, partner_id, location_id) 
	VALUES (3, 'INOX-111', 'E-square Cineplex', '111099', 'Subash chowk, Indiranagar', 3, 2);
INSERT INTO Theatre (theatre_id, partner_theatre_id, name, zipcode, address, partner_id, location_id) 
	VALUES (4, 'PVR-123', 'Elpro', '123456', 'Noida', 2, 3);

INSERT INTO Screen (screen_id, allocated_capacity, type, theatre_id) VALUES (1001, 200, 'IMAX',1);
INSERT INTO Screen (screen_id, allocated_capacity, type, theatre_id) VALUES (1002, 100, '3D',2);
INSERT INTO Screen (screen_id, allocated_capacity, type, theatre_id) VALUES (1003, 150, '2D',3);
INSERT INTO Screen (screen_id, allocated_capacity, type, theatre_id) VALUES (1004, 200, '2D',4);

INSERT INTO Event (event_id, partner_event_id, start_ts, movie_name, language, genre, duration, available_seat_count, screen_id)
	VALUES (12233, 'PVR-1001-0007', '2024-07-15T10:30:00','Top Gun: Maverick', 'English', 'Action', 120, 99, 1001);

INSERT INTO Tier (tier_id, type, price, event_id) VALUES (1, 'Silver', 220, 12233);
INSERT INTO Tier (tier_id, type, price, event_id) VALUES (2, 'Gold', 280, 12233);
INSERT INTO Tier (tier_id, type, price, event_id) VALUES (3, 'Platinum', 320, 12233);

INSERT INTO Offer (offer_id, name, description, tnc, available_from, expires_on, discount) 
	VALUES (1, '50% discount on 3rd ticket', '50% discount on 3rd ticket', 'Can be availed once per user', '2024-07-01T00:00:00', '2024-08-15T23:59:00', 200);
INSERT INTO Offer (offer_id, name, description, tnc, available_from, expires_on, discount) 
	VALUES (2, '20% discount on afternoon tickets', '20% discount on afternoon tickets', 'Can be availed once per user', '2024-06-01T00:00:00', '2024-09-30T23:59:00', 100);

INSERT INTO Map_Theatre_Offer (theatre_id, offer_id) VALUES (1,1);
INSERT INTO Map_Theatre_Offer (theatre_id, offer_id) VALUES (1,2);
INSERT INTO Map_Theatre_Offer (theatre_id, offer_id) VALUES (2,1);