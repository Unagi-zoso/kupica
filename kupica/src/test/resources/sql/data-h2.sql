INSERT INTO `member` (`id`, `nickname`, `email_address`, `user_role`, `social_login_type`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'Juditha Targe','jtarge0@github.com','MEMBER','KAKAO','2022-02-02','2023-05-28'),
    (2,'Lesya','lbottoner1@theatlantic.com','MEMBER','KAKAO','2023-04-29','2023-09-17'),
    (3,'Nannette','ndrysdall2@cam.ac.uk','MEMBER','KAKAO','2023-04-19','2024-02-27'),
    (4,'Zedekiah','zhartopp3@uiuc.edu','MEMBER','KAKAO','2023-04-26','2024-01-15'),
    (5,'Dody','dminards4@chicagotribune.com','MEMBER','KAKAO','2023-04-28','2024-02-17'),
    (6,'Emanuel','ecarter5@spotify.com','MEMBER','KAKAO','2023-04-20','2024-03-01'),
    (7,'Zaccaria','zworwood6@simplemachines.org','MEMBER','KAKAO','2023-04-21','2023-10-15'),
    (8,'Freeland','fde7@amazon.co.uk','MEMBER','KAKAO','2023-04-21','2023-12-08'),
    (9,'Aurlie','avitler8@forbes.com','MEMBER','KAKAO','2023-04-21','2024-02-04'),
    (10,'Joaquin','jdoldon9@studiopress.com','MEMBER','KAKAO','2023-04-30','2024-03-23');

INSERT INTO `anonymous_user` (`id`, `nickname`, `password`, `ip_address`, `user_role`, `created_datetime`)
VALUES
    (1,'test tiger1','2a$04$IBpZOLrjDCgmiBoxrkisq3i,BQ3iTdePHa.0hh7ZTbl5rGVQffcqy','14.175.150.32/26','ANONYMOUS','2023-04-02'),
    (2,'test tiger2','2a$04$rFsI2/BvUYxs.AObeANrxXm9P7fcHVc7qnfjI11anEzwXWwiPBioN.J','245.108.203.138/9','ANONYMOUS','2023-02-23'),
    (3,'test tiger3','2a$04$mFPXT/nduqB0ir0YT2bffhzdBcekoN.JxHn2RG3ciPyf9M2Y3tahK5JBl','97.82.201.19/27','ANONYMOUS','2023-04-07'),
    (4,'test tiger4','2a$04$dWTO.Hgi8EB6i3duosP7mUilfengjxnEzwXWwiPBim','13.36.57.241/28','ANONYMOUS','2023-03-28'),
    (5,'test tiger5','2a$04$.aw0guTX1LBslXovrYT7KWTO.HgivC42c0V/3uu7QPVmnz3nIF2','35.207.94.236/3','ANONYMOUS','2023-03-16'),
    (6,'test tiger6','2a$04$FFOdrOecekFMYSNCty7BetwzVufcHVc7qnfjI11anEzwXWwiPBioN.','235.45.29.206/29','ANONYMOUS','2023-02-27'),
    (7,'test tiger7','2a$04$kCjfjI11anEzwXWwiPBioN.JxWIda.LBslXovrYT7KWTO.HgivC42c0V/','235.2.70.33/2','ANONYMOUS','2023-02-26');

INSERT INTO `article` (`id`, `caption`, `anonymous_user_id`, `member_id`, `login_flag`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'Adaptive analyzing initiative',1,NULL,0,'2023-02-04','2024-01-09'),
    (2,'Fundamental 5th generation hardware',1,NULL,0,'2023-04-14','2024-04-16'),
    (3,'Cross-platform tangible protocol',NULL,1,1,'2023-04-11','2024-02-17'),
    (4,'Sharable transitional installation',NULL,1,1,'2023-02-04','2023-09-24'),
    (5,'Multi-lateral zero tolerance solution',2,NULL,0,'2023-04-11','2023-11-15'),
    (6,'Optional human-resource moratorium',NULL,1,1,'2023-03-22','2023-10-23'),
    (7,'Diverse client-driven budgetary management',NULL,2,1,'2023-02-15','2023-11-15'),
    (8,'Phased value-added groupware	',NULL,3,1,'2023-04-12','2023-11-11'),
    (9,'Operative solution-oriented access',3,NULL,0,'2023-04-06','2024-03-07'),
    (10,'Progressive regional conglomeration',NULL,4,1,'2023-03-11','2023-11-24'),
    (11,'Self-enabling well-modulated internet solution',NULL,5,1,'2023-03-29','2024-02-24'),
    (12,'Up-sized fresh-thinking budgetary management',3,NULL,0,'2023-04-04','2024-03-02'),
    (13,'Robust secondary core',NULL,6,1,'2023-02-22','2024-04-15'),
    (14,'Adaptive actuating local area network',NULL,7,1,'2023-02-21', '2024-04-13');

INSERT INTO `comment` (`id`, `content`,	`reply_target_comment_id`, `anonymous_user_id`, `member_id`, `article_id`, `login_flag`, `created_datetime`, `updated_datetime`)
VALUES
    (1,'verne	Adaptive analyzing initiative',NULL,1,NULL,1,0,'2023-02-04','2024-01-09'),
    (2,'Fundamental 5th generation hardware',NULL,2,NULL,1,0,'2023-04-14','2024-04-16'),
    (3,'Cross-platform tangible protocol',NULL,3,NULL,1,0,'2023-04-11','2024-02-17'),
    (4,'Sharable transitional installation',NULL,NULL,1,1,1,'2023-02-04','2023-09-24'),
    (5,'Multi-lateral zero tolerance solution',NULL,1,NULL,1,0,'2023-04-11','2023-11-15'),
    (6,'Optional human-resource moratorium',NULL,2,NULL,1,0,'2023-03-22','2023-10-23'),
    (7,'Diverse client-driven budgetary management',NULL,NULL,2,1,1,'2023-02-15','2023-11-15'),
    (8,'Phased value-added groupware	',NULL,NULL,3,1,1,'2023-04-12','2023-11-11'),
    (9,'Operative solution-oriented access',NULL,NULL,4,1,1,'2023-04-06','2024-03-07'),
    (10,'Progressive regional conglomeration',NULL,NULL,4,1,1,'2023-03-11','2023-11-24'),
    (11,'Self-enabling well-modulated internet solution',1,NULL,1,2,1,'2023-03-29','2024-02-24'),
    (12,'Up-sized fresh-thinking budgetary management',11,NULL,1,2,1,'2023-04-04','2024-03-02'),
    (13,'Robust secondary core',1,NULL,1,2,1,'2023-02-22','2024-04-15'),
    (14,'Adaptive actuating local area network',1,NULL,1,2,1,'2023-03-06','2023-08-31'),
    (15,'Configurable zero administration synergy',2,NULL,1,2,1,'2023-02-21','2023-09-03'),
    (16,'Implemented client-driven frame',12,NULL,1,2,1,'2023-03-18','2023-09-09'),
    (17,'Configurable fresh-thinking artificial intelligence',2,NULL,2,3,1,'2023-04-03','2024-01-28'),
    (18,'Robust logistical orchestration',2,NULL,2,3,1,'2023-02-17','2024-04-09'),
    (19,'Profound background leverage',2,NULL,2,3,1,'2023-02-01','2024-03-01'),
    (20,'Focused intermediate firmware',14,NULL,2,3,1,'2023-04-09','2024-02-22'),
    (21,'Function-based systematic orchestration',2,NULL,3,4,1,'2023-03-13','2023-12-12'),
    (22,'Devolved modular secured line',6,NULL,3,4,1,'2023-02-28','2024-02-18'),
    (23,'Organized 24 hour definition',5,NULL,3,4,1,'2023-02-15','2023-09-26'),
    (24,'Right-sized tangible approach',22,NULL,3,4,1,'2023-03-01','2024-01-07'),
    (25,'Streamlined client-server solution',3,NULL,4,5,1,'2023-04-06','2024-02-29'),
    (26,'Enterprise-wide transitional policy',3,NULL,4,5,1,'2023-03-05','2023-09-16'),
    (27,'Multi-tiered cohesive synergy',3,4,NULL,3,0,'2023-04-16','2023-12-12'),
    (28,'Distributed multi-tasking internet solution',3,4,NULL,3,0,'2023-03-31','2024-01-25'),
    (29,'Centralized user-facing ability',11,5,NULL,3,0,'2023-03-05','2024-02-27'),
    (30,'Ameliorated global middleware',13,6,NULL,3,0,'2023-03-05','2024-01-19');

INSERT INTO `article_like` (`id`, `ip_address`, `member_id`, `article_id`, `login_flag`, `created_datetime`)
VALUES
    (1,'13.27.213.49/31',NULL,1,0,'2023-04-03'),
    (2,NULL,1,1,1,'2023-02-19'),
    (3,'14.175.150.32/26',NULL,1,0,'2023-04-02'),
    (4,NULL,1,2,1,'2023-04-09'),
    (5,'245.108.203.138/9',NULL,2,0,'2023-02-23'),
    (6,NULL,1,2,1,'2023-03-26'),
    (7,NULL,1,3,1,'2023-04-16'),
    (8,NULL,1,3,1,'2023-04-04'),
    (9,NULL,1,3,1,'2023-04-06'),
    (10,NULL,1,3,1,'2023-03-31'),
    (11,NULL,1,4,1,'2023-01-31'),
    (12,'131.173.16.234/31',NULL,4,0,'2023-04-15'),
    (13,NULL,1,5,1,'2023-02-18'),
    (14,'97.82.201.19/27',NULL,6,0,'2023-04-07'),
    (15,'13.36.57.241/28',NULL,6,0,'2023-03-28'),
    (16,'35.207.94.236/3',NULL,6,0,'2023-03-16'),
    (17,'235.45.29.206/29',NULL,7,0,'2023-02-27'),
    (18,'235.2.70.33/2',NULL,7,0,'2023-02-26'),
    (19,NULL,1,7,1,'2023-02-25'),
    (20,NULL,1,7,1,'2023-04-16');


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
