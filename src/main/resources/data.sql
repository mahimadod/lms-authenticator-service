INSERT INTO roles ( name) VALUES
( 'ADMIN'),
( 'LIBRARIAN'),
( 'MEMBER'),
( 'GUEST'),
( 'SUPERADMIN');

INSERT INTO users (username, password, role_id) VALUES
( 'admin', 'adminpass', 1),
( 'librarian1', 'libpass1', 2),
( 'member1', 'mempass1', 3),
( 'guest1', 'guestpass', 4),
( 'superadmin', 'superpass', 5);
