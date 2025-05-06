DO
$$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'myx') THEN
        PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE myx');
    END IF;
END
$$;

\c myx

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts (
    id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL,
    content TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    content TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

insert into users (name, surname) 
    values
    ('Анна', 'Парамонова'),
    ('Анастасия', 'Волкова'),
    ('Вася', 'Гадов');

INSERT INTO posts (author_id, content)
VALUES
(1, 'Мои котики!'),
(2, 'Вышла погулять');

-- Вставка начальных данных в таблицу comments
INSERT INTO comments (post_id, user_id, content)
VALUES
(1, 3, 'Супер крутые!'),
(2, 2, 'Уже дома!');