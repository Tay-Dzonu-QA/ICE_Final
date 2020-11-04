INSERT INTO `genre` (`id`,`name`,`description`) VALUES(1,'pop','pop music');
INSERT INTO `genre` (`id`,`name`,`description`) VALUES(2,'rock','rock music');

INSERT INTO `artist` (`id`,`name`) VALUES(1,'james');
INSERT INTO `artist` (`id`,`name`) VALUES(2,'john');

INSERT INTO `album` (`id`,`cover`,`name`,`artist_id`,`genre_id`) VALUES(1,'this cover','album 1',1,1);
INSERT INTO `album` (`id`,`cover`,`name`,`artist_id`,`genre_id`) VALUES(2,'this cover','album 2',2,1);
INSERT INTO `album` (`id`,`cover`,`name`,`artist_id`,`genre_id`) VALUES(3,'this cover','album 3',1,2);
INSERT INTO `album` (`id`,`cover`,`name`,`artist_id`,`genre_id`) VALUES(4,'this cover','album 4',2,2);
INSERT INTO `album` (`id`,`cover`,`name`,`artist_id`,`genre_id`) VALUES(5,'this cover','album 5',1,1);

INSERT INTO `track` (`id`,`duration`,`lyrics`,`name`,`album_id`) VALUES(1,5,'this is the lyrics','track 1',1);
INSERT INTO `track` (`id`,`duration`,`lyrics`,`name`,`album_id`) VALUES(2,5,'this is the lyrics','track 2',2);
INSERT INTO `track` (`id`,`duration`,`lyrics`,`name`,`album_id`) VALUES(3,5,'this is the lyrics','track 3',3);
INSERT INTO `track` (`id`,`duration`,`lyrics`,`name`,`album_id`) VALUES(4,5,'this is the lyrics','track 4',4);
INSERT INTO `track` (`id`,`duration`,`lyrics`,`name`,`album_id`) VALUES(5,5,'this is the lyrics','track 5',5);

INSERT INTO `user` (`id`,`name`,`password`,`username`) VALUES(1,'Choonz Master','rootroot','rootroot');

INSERT INTO `playlist` (`id`,`artwork`,`description`,`name`,`user_id`) VALUES(1,'my artwork','my playlist','my playlist',1);
INSERT INTO `playlist` (`id`,`artwork`,`description`,`name`,`user_id`) VALUES(2,'your artwork','your playlist','your playlist',1);
INSERT INTO `playlist` (`id`,`artwork`,`description`,`name`,`user_id`) VALUES(3,'third artwork','third playlist','third playlist',1);
INSERT INTO `playlist` (`id`,`artwork`,`description`,`name`,`user_id`) VALUES(4,'blank','BLANK FOR TESTING','test playlist',1);

INSERT INTO `track_playlists` (`tracks_id`,`playlists_id`) VALUES(1,1);
INSERT INTO `track_playlists` (`tracks_id`,`playlists_id`) VALUES(2,2);
INSERT INTO `track_playlists` (`tracks_id`,`playlists_id`) VALUES(3,3);
INSERT INTO `track_playlists` (`tracks_id`,`playlists_id`) VALUES(4,3);
INSERT INTO `track_playlists` (`tracks_id`,`playlists_id`) VALUES(5,3);
COMMIT;