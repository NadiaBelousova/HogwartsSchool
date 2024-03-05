-- liquibase formatted sql

-- changeset nbelousova:1
DROP INDEX student_name_index;
-- changeset nbelousova:2
create index student_name_index on student (name);
-- changeset nbelousova:3
create index faculty_nc_index on faculty (name,color);

