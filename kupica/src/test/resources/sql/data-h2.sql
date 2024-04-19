INSERT INTO `member` (`id`, `nickname`, `email_address`, `user_role`, `social_login_type`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'Juditha Targe','jtarge0@github.com','USER','KAKAO','2022-02-02','2023-05-28'),
    (2,'Lesya','lbottoner1@theatlantic.com','USER','KAKAO','2023-04-29','2023-09-17'),
    (3,'Nannette','ndrysdall2@cam.ac.uk','USER','KAKAO','2023-04-19','2024-02-27'),
    (4,'Zedekiah','zhartopp3@uiuc.edu','USER','KAKAO','2023-04-26','2024-01-15'),
    (5,'Dody','dminards4@chicagotribune.com','USER','KAKAO','2023-04-28','2024-02-17'),
    (6,'Emanuel','ecarter5@spotify.com','USER','KAKAO','2023-04-20','2024-03-01'),
    (7,'Zaccaria','zworwood6@simplemachines.org','USER','KAKAO','2023-04-21','2023-10-15'),
    (8,'Freeland','fde7@amazon.co.uk','USER','KAKAO','2023-04-21','2023-12-08'),
    (9,'Aurlie','avitler8@forbes.com','USER','KAKAO','2023-04-21','2024-02-04'),
    (10,'Joaquin','jdoldon9@studiopress.com','USER','KAKAO','2023-04-30','2024-03-23');

INSERT INTO `article` (`id`, `author_name`, `password`, `caption`, `member_id`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'La','2a$04$IBpZOxrkisq3i2/BQ3iTdePHa.0hh7ZTbl5rGVQffcqy8Tx4q.l5a','Adaptive analyzing initiative',NULL,'2023-02-04','2024-01-09'),
    (2,'Florenza','2a$04$rFsILrjDCgmiBovUYxs.AObeANrxikM419jkYUiOS.oVYDr113T0q','Fundamental 5th generation hardware',NULL,'2023-04-14','2024-04-16'),
    (3,'Juditha Targe','2a$04$m8EB6i3duosP7mFPXT/nduZc6kZCsP2Hh2Ri3ChUM/mMd0T3y9Wu6','Cross-platform tangible protocol',1,'2023-04-11','2024-02-17'),
    (4,'Juditha Targe','2a$04$dWIda.LBslXovrYT7KWTO.HgivC42c0V/3uu7QPVmnz3nIF2hUWiS','Sharable transitional installation',1,'2023-02-04','2023-09-24'),
    (5,'Marcie','2a$04$xie4MRSoE5MguIwY4XnxkebwUO6SoUGMOoT7vZ8U60foYAuPFRmX2','Multi-lateral zero tolerance solution',NULL,'2023-04-11','2023-11-15'),
    (6,'Juditha Targe','2a$04$n64yEgf1FkZVbxYvn5BCduLnBbjaZfXW6jlFcF8KZ737FVcz1rxEO','Optional human-resource moratorium',1,'2023-03-22','2023-10-23'),
    (7,'Lesya','2a$04$qB0ir0YT2bffhzdBcekoN.JxHn2RG3ciPyf9M2Y3tahK5JBllwJ7S','Diverse client-driven budgetary management',2,'2023-02-15','2023-11-15'),
    (8,'Nannette','2a$04$FFFMYSNC.aw0guTX1OdrOe/BwbVz0xuYDMjOfip9qYKSz5dmHAWq','Phased value-added groupware	',3,'2023-04-12','2023-11-11'),
    (9,'Waldon','2a$04$kCjUilfengjxXm9P7ty7BetwzVufcHVc7qnfjI11anEzwXWwiPBim','Operative solution-oriented access',NULL,'2023-04-06','2024-03-07'),
    (10,'Zedekiah','2a$04$tkwWVSs68LiVNeePOiYE9OpKbGg4.telvWLqCCBRzhBxyFYzV9LyS','Progressive regional conglomeration',4,'2023-03-11','2023-11-24'),
    (11,'Dody','2a$04$HHGETtn/6jyLRHNtpwGmlOKVlBQnhKb15dPtFEWtyJv1oJKf0Nz02','Self-enabling well-modulated internet solution',5,'2023-03-29','2024-02-24'),
    (12,'Ikey','2a$04$vhwYfVyCX1r1gvowNHW0JOmjlmAsJgWctOEa/E8dS82kY46Viqp.u','Up-sized fresh-thinking budgetary management',NULL,'2023-04-04','2024-03-02'),
    (13,'Emanuel','2a$04$ml0lZvyxysTAf8bI4/RJPO.nPH93oaRyN6mgNZANYYhcpSM7/SkLC','Robust secondary core',6,'2023-02-22','2024-04-15'),
    (14,'Zaccaria','2a$04$9iQnxO.Euf41fjBaHYJmOeWHz5IseOQMOxNvDPDOkdB9vFQfyZ0wq','Adaptive actuating local area network',7,'2023-02-21', '2024-04-13');

INSERT INTO `comment` (`id`, `author_name`,	`content`,	`reply_target_comment_id`,	`password`,	`member_id`, `article_id`, `created_datetime`,	`updated_datetime`)
VALUES
    (1,'La','verne	Adaptive analyzing initiative',NULL,'$2a$04$IBpZOxrkisq3i2/BQ3iTdePHa.0hh7ZTbl5rGVQffcqy8Tx4q.l5a',NULL,1,'2023-02-04','2024-01-09'),
    (2,'Florenza','Fundamental 5th generation hardware',NULL,'$2a$04$rFsILrjDCgmiBovUYxs.AObeANrxikM419jkYUiOS.oVYDr113T0q',NULL,1,'2023-04-14','2024-04-16'),
    (3,'Bernie','Cross-platform tangible protocol',NULL,'$2a$04$m8EB6i3duosP7mFPXT/nduZc6kZCsP2Hh2Ri3ChUM/mMd0T3y9Wu6',NULL,1,'2023-04-11','2024-02-17'),
    (4,'Gabriela','Sharable transitional installation',NULL,'$2a$04$dWIda.LBslXovrYT7KWTO.HgivC42c0V/3uu7QPVmnz3nIF2hUWiS',NULL,1,'2023-02-04','2023-09-24'),
    (5,'Marcie','Multi-lateral zero tolerance solution',NULL,'$2a$04$xie4MRSoE5MguIwY4XnxkebwUO6SoUGMOoT7vZ8U60foYAuPFRmX2',NULL,1,'2023-04-11','2023-11-15'),
    (6,'Wilma','Optional human-resource moratorium',NULL,'$2a$04$n64yEgf1FkZVbxYvn5BCduLnBbjaZfXW6jlFcF8KZ737FVcz1rxEO',NULL,1,'2023-03-22','2023-10-23'),
    (7,'Darsie','Diverse client-driven budgetary management',NULL,'$2a$04$qB0ir0YT2bffhzdBcekoN.JxHn2RG3ciPyf9M2Y3tahK5JBllwJ7S',NULL,1,'2023-02-15','2023-11-15'),
    (8,'Florrie','Phased value-added groupware	',NULL,'2a$04$FFFMYSNC.aw0guTX1OdrOe/BwbVz0xuYDMjOfip9qYKSz5dmHAWq',NULL,1,'2023-04-12','2023-11-11'),
    (9,'Waldon','Operative solution-oriented access',NULL,'$2a$04$kCjUilfengjxXm9P7ty7BetwzVufcHVc7qnfjI11anEzwXWwiPBim',NULL,1,'2023-04-06','2024-03-07'),
    (10,'Hector','Progressive regional conglomeration',NULL,'$2a$04$tkwWVSs68LiVNeePOiYE9OpKbGg4.telvWLqCCBRzhBxyFYzV9LyS',NULL,1,'2023-03-11','2023-11-24'),
    (11,'Jordanna','Self-enabling well-modulated internet solution',1,'$2a$04$HHGETtn/6jyLRHNtpwGmlOKVlBQnhKb15dPtFEWtyJv1oJKf0Nz02',1,2,'2023-03-29','2024-02-24'),
    (12,'Ikey','Up-sized fresh-thinking budgetary management',11,'$2a$04$vhwYfVyCX1r1gvowNHW0JOmjlmAsJgWctOEa/E8dS82kY46Viqp.u',1,2,'2023-04-04','2024-03-02'),
    (13,'Stephanie','Robust secondary core',1,'$2a$04$ml0lZvyxysTAf8bI4/RJPO.nPH93oaRyN6mgNZANYYhcpSM7/SkLC',1,2,'2023-02-22','2024-04-15'),
    (14,'Nobie','Adaptive actuating local area network',1,'$2a$04$9iQnxO.Euf41fjBaHYJmOeWHz5IseOQMOxNvDPDOkdB9vFQfyZ0wq',1,2,'2023-03-06','2023-08-31'),
    (15,'Roman','Configurable zero administration synergy',2,'$2a$04$OXLSzIlMFaInUz2FJjIwM.gU/J8mEWzk7iAw2oeySWFzfcm1zDHzm',1,2,'2023-02-21','2023-09-03'),
    (16,'Winni','Implemented client-driven frame',12,'$2a$04$D0CWuyY2Ah9aWtkmf3fbEOWb0D4ChK7Qc.0gz4yfzvFhiAeVzIVm.',1,2,'2023-03-18','2023-09-09'),
    (17,'Kelbee','Configurable fresh-thinking artificial intelligence',2,'$2a$04$7xpJX2CPwhKN1MxObFgFPuBzOqyag8lsa.umvnyegZev.nz6I.H2q',2,3,'2023-04-03','2024-01-28'),
    (18,'Jolynn','Robust logistical orchestration',2,'$2a$04$gplWpdZmShZNJEl/Tn9zOOoMozdOlTso1Oon3i31uDsz7Pf1cxWVG',2,3,'2023-02-17','2024-04-09'),
    (19,'Bride','Profound background leverage',2,'$2a$04$2mTow0/Ia6mvRBgDdb/miu/xos2rx0kelsDqKxZcYlQTNf90VC3Mi',2,3,'2023-02-01','2024-03-01'),
    (20,'Julia','Focused intermediate firmware',14,'$2a$04$/vboQ3pEDQ2uLX4Ks1Szl.0cCEu7ilICcvWyGlJ.92HTHB5Rpw/We',2,3,'2023-04-09','2024-02-22'),
    (21,'Kippar','Function-based systematic orchestration',2,'$2a$04$QmzObuF4tS6vz9o3g4VzQ.m8PB42X8YiVyRTG2TWfDbjMHFm4yM1e',3,4,'2023-03-13','2023-12-12'),
    (22,'Jeff','Devolved modular secured line',6,'$2a$04$sTZj.6/ojV9AXm2i5hvQLOiTSRPSDn0xxA3QdgWnt80aYyqJzaLAi',3,4,'2023-02-28','2024-02-18'),
    (23,'Bartlet','Organized 24 hour definition',5,'$2a$04$TXX5onE4AZwSW6VtLnYvOeQtc6JD2rf0vCtBOkg64OZrOb2au0rxe',3,4,'2023-02-15','2023-09-26'),
    (24,'Faber','Right-sized tangible approach',22,'$2a$04$unbn2iYG7KxQi5niNkzX6ePi81o4VKmEJvzsfP7bKReVcUEK70JFe',3,4,'2023-03-01','2024-01-07'),
    (25,'Rochella','Streamlined client-server solution',3,'$2a$04$nHqStREjtlSyCKGXBjiJ6ed8woEV9pNxfrbFjs9kzRWSNA5C4XPla',4,5,'2023-04-06','2024-02-29'),
    (26,'Bastian','Enterprise-wide transitional policy',3,'$2a$04$xyfNw4DB5AmtgyK59sxf3ecyyScTKvMJ3pXuKwyQeiCdsVKpHyWya',4,5,'2023-03-05','2023-09-16'),
    (27,'Uriah','Multi-tiered cohesive synergy',3,'$2a$04$ZxoL/lfXVVlH0S6oePI7ZuF/2gUOtmCgKimEeosMd8OiXKiXUIAiu',NULL,3,'2023-04-16','2023-12-12'),
    (28,'Aguistin','Distributed multi-tasking internet solution',3,'$2a$04$GgKYXkR69YYSaLUlj.z5o.3RfBySy.hH/qtkZRK1x9mfQwtLy7uOm',NULL,3,'2023-03-31','2024-01-25'),
    (29,'Vickie','Centralized user-facing ability',11,'$2a$04$8sLMy6YwQJP.P7oOk8hGVOYzYKtcz83HH.NkkbcDqkFDrV4JHBAze',NULL,3,'2023-03-05','2024-02-27'),
    (30,'Cleon','Ameliorated global middleware',13,'$2a$04$36ajeRu3bOLQ5S94mSNfyuPF9RHoaooq1M4ha1NZFX.pnxdqty03q',NULL,3,'2023-03-05','2024-01-19');

INSERT INTO `article_like` (`id`, `ip_address`, `member_id`, `article_id`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'13.27.213.49/31',NULL,1,'2023-04-03','2024-04-09'),
    (2,NULL,1,1,'2023-02-19','2023-09-22'),
    (3,'14.175.150.32/26',NULL,1,'2023-04-02','2024-03-27'),
    (4,NULL,1,2,'2023-04-09','2023-09-05'),
    (5,'245.108.203.138/9',NULL,2,'2023-02-23','2024-04-18'),
    (6,NULL,1,2,'2023-03-26','2023-12-17'),
    (7,NULL,1,3,'2023-04-16','2024-04-17'),
    (8,NULL,1,3,'2023-04-04','2023-12-06'),
    (9,NULL,1,3,'2023-04-06','2023-12-24'),
    (10,NULL,1,3,'2023-03-31','2024-02-10'),
    (11,NULL,1,4,'2023-01-31','2023-09-05'),
    (12,'131.173.16.234/31',NULL,4,'2023-04-15','2024-01-10'),
    (13,NULL,1,5,'2023-02-18','2024-04-09'),
    (14,'97.82.201.19/27',NULL,6,'2023-04-07','2024-03-04'),
    (15,'13.36.57.241/28',NULL,6,'2023-03-28','2023-09-15'),
    (16,'35.207.94.236/3',NULL,6,'2023-03-16','2024-02-24'),
    (17,'235.45.29.206/29',NULL,7,'2023-02-27','2023-12-01'),
    (18,'235.2.70.33/2',NULL,7,'2023-02-26','2024-01-26'),
    (19,NULL,1,7,'2023-02-25','2024-03-06'),
    (20,NULL,1,7,'2023-04-16','2023-09-27');


INSERT INTO `photo_download_source` (`id`, `photo_resolution`, `file_source`, `file_byte_size`, `download_count`, `article_id`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'920x480','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAJcSURBVDjLjZPfS5NRHMYHQn9AIeRd0za9KEMi7U4oiIZRVFSUVJvbHEyo1GXrB8XsYpdDQlGz8j4giggdxUSTaS7kzkUCmFtbS3CBRzMEzzQYv65BqOn9wrm1rcraH79B9d6/GihmdahRl+SzBPcVmPWosLwsXwATafiTtytwgRrw+Y16u1GTVMJthAMHZUim83yL5EjqL1Rig5nA8xOJW82TynFBAaDQTpQtw8jlw9h8IgUfr8fPp8PLMvC6/XCZGvB2c4KPHZq+Mm005nvgL4zSSnbWXu/ardJLd+lKfR1Ky8V91XrS6KETTud6Tv/BxVpxDzfgUo/AAAAAElFTkSuQmCC',101000,44,1,'2023-03-13','2023-09-11'),
    (2,'1420x780','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAJLSURBVDjLpZNNiI1hFMd/773vjJlxR2iMKUyZSfItHxlJaQyxYCcbJYqlpR1R2Ck7ZIDMaHqIgZkLlIXz8K2ZRzpX8fPLMOFiGWRtJobvctbefC6wo7Vs6g55eyNz9STAyiB41ENCU0hIcwwxnwdicL8WZ2IRqIpX8sjzC11MDT6iKcfhhl/eIGk+9hzq+eCBUOdYl6woGzZfZi693j11MXT3bacnq6dPBt5zN1Xg2RPz2LVTpL//caVZwrZhs7eloG39z57ZfuXc/YG+H9A18kki1psyTV2jZ2zj3/2vwEmE35dgxn8EgAAAABJRU5ErkJggg==',14000,15,1,'2023-02-05','2024-03-01'),
    (3,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAFuSURBVDjLpZPdS8JgFIf9F6UoieiDogKllUNhM4IIKYhCRAqlIkqKmYVGERZFUZB9AhlrxKBUVUVqtUqPddqNdA0vie3XwHzLigTKzf0Z40uXsJw+BwG53Nsy8e0KE8oC73SIfQED9jY+6yzAqwGUUjg+DY6FfwA5i1AjZKvAWgAAAAASUVORK5CYII=',155000,15,1,'2023-02-08','2023-11-15'),
    (4,'920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAI9SURBVDjLjZM7aFNRGMdTA+Jg8UFx0qGDi4PgoERE6qCIUJxEwcFOdhPEdhB0FVQsmNZvwQgzeO8GovF1MFZqRHaMHo8nh0wS/RxyF6VbDabi4IgDLTJEE6hnqDGcZQZ2A9amsJVJMlHu92+ZbPZRNYvITAKOWsymUbaAvT7mknXgcSmPANbJfYGKT7gXGRNUAUgrzmdzq/sG263gPFxXDI41yA2+V+5aII=',100000,1,2,'2023-03-24','2024-02-12'),
    (5,'120x80','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAQAAAC1+jfqAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAEYSURBVBgZBcHPio5hGAfg6/2+R980k6wmJgsJ5U/ZOAqbSc2GnXOwUg7BESgLUeIQ1GSslCC',100000,10,2,'2023-02-22','2024-02-01'),
    (6,'192x100','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAK7SURBVDjLpVNLT1NBGD1z7+2DR2poS0uR1tBKhNBGKyaYGDSpkZ0LXZogK/fs2LlSdODI/f9sPhHUA6nRkvlUpPKNhBg0kf5kOhUCIcDju9Xi+y2Szy+Tz29vbQHejH3MovpGrncXZoBIQQ5HK5GxzH2ex2OwRBINVq9bJAq552OBxIJBLweDyIRCKgrYC2gs7OTiSTSczPz8PlciEWi2F3dxflctnM0vrWha8HClLF5eP8bbVCla6rK198AAAAASUVORK5CYII=',101100,7,2,'2023-02-14','2023-11-19'),
    (7,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAH3SURBVDjLhZNJjxJhEIb7T8xVw78g6fh75mJGY/SqTjRGTXTcbu7xxtItPew2YxMgqZ+JxZNqfTKUY/u4gbMq+1220kEgnEYrENiECTV5YXiwVIPP71Hc9Or+ORdAjNkHit1WqRC0QikTWIwHa0xuMxb5rNZhj+6OLk9Bpef7mN92f38JyBVpBms0kuEAgEbIiQzWavsB2t0WjEm76ZOTyWD/FWvYtFe4GNFoVAyHwxZdIrdr6kx8B9Nzyxa73W6H/YjbjiMYDIp+v9+iS1x9YhKz/Jp4J4BCURQne2mLDqxer28V7wVQyLLs9Hq9FoldLpdj6yn/73dlU51MfGlX/R/5GCirExPTUwAAAABJRU5ErkJggg==',1004400,11,3,'2023-02-07','2023-10-23'),
    (8,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAIsSURBVDjLpZPNbhJRFMf/w9fwNUiVlZWPncSmqemm7FjZaGJcsdCkS17AJ8AH6BvUZlWwSyoQg2n883j0CbT1OplCyurq6xoOKvrQsCWOt4cvhMxtgI4BkDgQDG47HTWSk82nks3e1Vdzb6rwqYrKiIN/GBsPRW8wtlJd35ixl5JJNJVKvVIf0XpP0/KH+kfCIKHK9sATFgZ3dfuosH9gJGNIx8Po9hOAVdEathK4O6rrGX+sjBV8rtfrmEwmMAwDwZBON1ATkFoZ627m36trfQMoFosHnU7neSuQmCC',13000,11,4,'2023-02-25','2024-04-17'),
    (9,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/bYLG0I/FgXnbEzDfJlDV5S0PuXBJs1/pWJ2ZZ4WuczFbAJBT2TxP4qMLKWYA4vdETMtD6PMPB8Uu9MtPXLFGG6XcTVNRa2vQoMeeOuSF7DQVaDmepq+ha+ewQHl1YRv3jAr2jJaBrYEhUzXYdYqGEnpeJ3rGx7IGTrpD0KyAWMG07xwNgX5Gph6u+CJ11myyGqc3zvFz4w2grW/H9j/f+Qn6r94u36IRBwAAAABJRU5ErkJggg==',100000,10,5,'2023-02-19','2024-02-14'),
    (10,'2420x1440','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8nZ2gKYGPB4PTCYT+vr6Rv9NrrXb7XM8z4O9UqmE8nO73RgbG0tTAgwODsaVSmWrVCrdXRZW04XLNpstFAgEUCgUsLm5KRiwSSQSSKfTCIfDUKlUUCgUTSKRqJZpGXI1nVfBYFBAZgKGzZbz+TwYjc+mRcjSiXui2+2VgxME5MRFkawy4LWWGpjQ0sm3oQW9IgG+oC13Mc+bgG3543lr4+PXO2YmAdGuJYByxdENIik8kkIl4DlvUdyEVfgPiawSnrkeHFSATewKo66bI8PFEnGHjFouF0by/yRqOAv0GTk4k1uF9fwTotLvOjDWnP+b/jvURPGQBveIRP9xvuCAZ8S0uN70brHOnuEkpjhfKfR8Fz/chFHgvpae9FekKD8CWB6yA/teC6j/wy3Dt8SLgj0NxcY792dVgrFnOj7rkJggg==',330000,15,6,'2023-04-04','2024-03-22'),
    (11,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8mzICxJA9Z7j0HUOZkKHxnQwTYdKrGlizUxWS35ECBibremyopiKGAwX4Ia51omFL/Z0zk2f9DEW+L08hRaHHZFYFq1bqjC/kMbuxiqElSXsbapGKJJCe0sNAvNJdO2qM/dEhqrKIHlnVcogg0g8ayqLAxGRiSGsa57h0rRtU3cB1nuo5Bt+g40NCLdzPP4drnxrOhYVhVjYHZOD5/T2BbvR0f/QtmhznnJaYpxBajsEkbcaLtoil4recBXn4bpLMXsBZVlghkjbr/5lvy/96BZHoR08oEvirjkHsfof/NBVRWbIAk2QxpNa/RddVx07l90w33/iNore9A6KcPE1PjiMylbkurfc4k0k90hWAniJs3OHlPkf8A2YeC/G6HEpkAAAAASUVORK5CYII=',101200,15,7,'2023-03-02','2024-04-18'),
    (12,'2420x1440','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8vn9fqyulSGyU8pAGeVEDISTWZbVuQQenIvFYvKgXm/A5uBX5gtOQNt2JZmSZXQkEDV6vV5Uq9VWZkLQlzgts7Pd7KLZhyoQzISDRJAYipBulpa5JzK7uIsOXf+7glavmAwWBIn+AZndVcEO6UGXq8AlkCQiM2NuoqeF4oFM4ahoFAIADfkZ+o1RpynKJ27ftX+I4awLU7e90fj0KlSq/iSs9ms0N8GhPRaPSMceIkgsGwnI6435ocxLlUCv71p7Dqn1Hj8dtVFe9WUFYObmMmk0ly4ANu/e4y9f16gwHPBuJhG1rXJ2xXHKx9UYndYPeUf13npeHY2M7Wxm2VKHGisjJHTQ3mncnfQiCLpXc8FWIAAAAASUVORK5CYII=',130000,5,7,'2023-02-28','2024-01-22'),
    (13,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8YuNo65xc6aj5yBe/zZcLRNHDuUPD/ALD7CNqvjTon+UUPqYnI3iLSY3eIbuoQO4zBKXRnC1SO/qvXQOHMWP1jrFWTlnxm5veNbaOTxszEKWUvn8uJRqGegb2nqKuHAXaUyBxjjd+3l1P09h4tmRPRfC6ymjzpv2t12zzAaKskDfwvM4TT40hUx23JsmxfnpnnN8hkV5Jdug5+bMsvF5i+dXzBHcQOqbZeFm86CwioolIlnhvBy1VItYwSzzxCrY9KnY7B80wDd0846d15iVPEqmoDpD6K2jnUVpHgPajgpgVNfFRqqK2htg4qAOzOS9zsIERVEiQuoMlcEyIhaAIaolJHrY8kVdRWwZjfTjlSNVg0KaHJbNPt3DwsqaK28kNCgISTAMEvgEZSTurFRXEt4yGeo5oG8UB7UBsCEeo0X+NYbBCoRhLOA4Lw4sTlgxtRdmHw/CYII=',130000,5,8,'2023-04-03','2023-09-24'),
    (14,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8oX97ZrXgZRW9MxnV2Am//jxQwXd2cixgeICqsSCt7f3f3yBhpwmQPjz589UTge2trb/sQUWsq0fPnxgxBoLA5qZANTo8jopO/z6AAAAAElFTkSuQmCC',100000,10,9,'2023-03-09','2024-03-24'),
    (15,'2420x1440','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/eDAFjjzEziXwEGo/Gu3eXixaFQiM/OMAzW19d5kNVmw3uTSfFHgMFgUFpIACfmgrmMnJj0zrfAGbOxAcP0tO83gHVgoI3S6xElgkAgAJ/Px4s9pJW1tTU4HA7YCJzzj01O4heAp7W1LTg0hNjUFLY6O7FpMICmaVAUBRdph2wBy8vLPJBsBik8yG14Hlm5pFFki3S1wdWLIavpgZ0RQW8paWgiopAE4C0/QxGPt7HyOIDbnBQ66+gWy/jAFuCntTUuNd5GLI6GGhpAE0CAVOAXCvEpPx+nmg9H7+mk6NBJeHHHuORnBTtr1KSkHBjIyHi1WFDAuoXCbVtu7va7rKyYNj39LAlUXlDnoUt3mc/Mndw3P4PdF+l2fHycJjFR9Cg5WfEwKalak5Cwf+cfCVYRC3Blfz9VnP8rovbZoQ8oWiIAAAAASUVORK5CYII=',100110,11,9,'2023-04-16','2024-03-01'),
    (16,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8lUFHMyjDJ+t7jKjLb0SAtMhjzRCHfsEq4kI/rGZiweuDF36o4DDBw3G5KfW1CrYnBsgB+ugR6ZApsbBBDOcOckITMxU89RfBRx08+uRyblpt8VVhViDAnm4FlSRecUKRJMRTJM54UCKOSZ3mYsZaPDmiQY06xrSknIOCfbMCqjh15BGW0BlGVuaGXJDYd5USAs/sSWW552iztpe+7WcjTV+VB29DDOtDxBacER6IxCpzooGEKzQSQlpqDD/w6dgWYa1VTawuPyW0hq/d2Jo+nlkphcD3W1cEIXGR5NqzUKaNRtum4eP1gSXJR1dI21oG2ydXFCxWfh9mZ6dW1sGxhrrs0QoqgqVqkvVeJwF2ObZg4+BdjR9exNSNewavca+K5CYII=',100000,0,10,'2023-03-17','2023-12-28'),
    (17,'2420x1440','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8XJJCSF0tVrRyWRCx+Mxnc1mVBRFimCayWRoPB4Pa7gdRoJgzuFwnPt8PjCbzTCfzwEJAIlAlmVAIIxGI6hUKtDr9WCz2byNxWIcw+78SSTl9/s9VqsVMAPmAOv1WhVNLxaLkE6nwel0gsvlYr15pROgU5jnebVW5qwoik7A9FKpxPqj6q1WCz0Oh3wJj1g5Xdh78Zt8O4/AHktQ/fHN6jWP4qSRD5sPeVoNHqC53c4Z47Nm/VE2Sjw09uFW+6bcOA5UAlz/WsHXh2UiEvL57Oq5ZvsNyKLICFmyXRwAAAAASUVORK5CYII=',100440,1,1,'2023-03-02','2024-03-11'),
    (18,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8SwIAritViuI4zhLJpsGMtl3u93/JaPT6RJQggsXL8s/l4MnJw+j11sVdsOPYZVGjD+IE6XiGN68foWjlePCzmuigFE5+O68T9sUlKLZTuLZ1tfW8ODWKWH86L8Hq91/5ZpVwFKZlTcWS+PQWkOR6dT4nQFMYhkrMyfl3aRnoFkBfROAhuM4W0OHDogivbtnZBm23IX6vX6bQK5Onv6zDnPK+Dli6d/qOZP6Hxm6f/0v13KRmufhwC1Wm2CSvZrbu48Rj2BJRU5ErkJggg==',102200,5,12,'2023-01-31','2024-01-30'),
    (19,'2420x1440','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8m8vLEZCYN6q1yH098PWapTT+M8EDeNBvV6/X752HWpIQt3jgcFGVxT4Sr8RnplFiHIOJFi7d7fgaJra2d0t+ehs2zZcgYkYnz0eVbs8Urixh0BgSSsrK0OmaU6Hw+GIQh1rtRonYBBFEV6vlyOTyWzquj6WSCQ+7tKARk12dXVFZFmGpmmw6Az/ZnSbtdWlORfaBckXD15ize5c+kFjh0+y0Kf2HznqcXFxU2fz4dAILBLA0EQIKsV5DeymEqP829Tr8e3qFj4szUGu6E8Fd3dmqVRqiNZJkqhxQicTl564TJhfRlrRBXWM0kIEhfB84dlG8i8v6tBqmkd4owAAAABJRU5ErkJggg==',120000,11,13,'2023-03-14','2024-02-02'),
    (20,'1920x1080','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8YqADQBG/22nY0Y1sEZ3QXQaiHTEPAmRHYHg6CfqJWdqSGs0VgbegVIFWxfblfu8AIe6KIc31pIGE0R+hlQKkmAHJKdHkmAjsDG4lmsB0Qrz93hQn27XewHLw9mUyzZGemfIpFlILgsxLXmMHrlo8k1HAI1EFeBNg9AUnOW8gKUfz50iOwuB6YsLgkL+XshMp1Y/lkJdcZRnqpKPLzhHtBtysD5ymh6dbwM9qqHfwFkstvM6p5gOuM0eu4eQuslGVqubrcY0B8sSfDZfzY7y1+GFr2LXPhamoSj3PN6kn0LNtV1giRkFkYBiN5DlDzr3IppCfLX/Adx6qZAbVKVlUKkqUFRcQmL7QRquwvrHlr7f/TvSlgDF43s9La2tRK5CYII=',100230,1,14,'2023-02-05','2024-01-29');

INSERT INTO `hashtag` (`id`, `tag_name`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'#SEJONG','2023-02-20','2024-03-20'),
    (2,'#SEOUL','2023-03-04','2024-03-22'),
    (3,'#PARK','2023-02-09','2024-03-27'),
    (4,'#MUSEUM','2023-04-06','2024-03-16'),
    (5,'#MARKET','2023-02-18','2024-02-06'),
    (6,'#RESTAURANT','2023-04-16','2023-10-01'),
    (7,'#BEAUTIFUL','2023-03-10','2023-11-12'),
    (8,'#BAND','2023-04-08','2023-09-25'),
    (9,'#LOVE','2023-03-11','2024-02-04'),
    (10,'#GIRLFRIEND','2023-04-06','2024-02-29'),
    (11,'#CHRISTMAS','2023-02-10','2024-04-09'),
    (12,'#TEST','2023-04-17','2024-01-26'),
    (13,'#welcome','2023-02-13','2024-02-21'),
    (14,'#meme','2023-03-09','2024-02-17');

INSERT INTO `article_hashtag` (`id`, `article_id`, `hashtag_id`, `created_datetime`, `updated_datetime`)
VALUES
    (1,1,1,'2023-02-20','2024-03-20'),
    (2,1,2,'2023-03-04','2024-03-22'),
    (3,1,3,'2023-02-09','2024-03-27'),
    (4,2,4,'2023-04-06','2024-03-16'),
    (5,2,5,'2023-02-18','2024-02-06'),
    (6,2,6,'2023-04-16','2023-10-01'),
    (7,3,7,'2023-03-10','2023-11-12'),
    (8,3,8,'2023-04-08','2023-09-25'),
    (9,3,9,'2023-03-11','2024-02-04'),
    (10,4,10,'2023-04-06','2024-02-29'),
    (11,4,11,'2023-02-10','2024-04-09'),
    (12,4,12,'2023-04-17','2024-01-26'),
    (13,5,13,'2023-02-13','2024-02-21'),
    (14,5,14,'2023-03-09','2024-02-17');
