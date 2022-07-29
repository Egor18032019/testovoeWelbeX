drop table if exists testable cascade;
drop sequence if exists testable_sequence;

create sequence testable_sequence start 1 increment 1;
create table testable
(
    id       bigint PRIMARY KEY DEFAULT nextval('testable_sequence'),
    date     timestamp NOT NULL,
    name     TEXT      NOT NULL,
    quantity int       NOT NULL,
    distance bigint    NOT NULL
)
