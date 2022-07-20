-- create tables for ebs remotes
-- except for the bucket, quite similar to S3 remotes
CREATE TABLE EBS_REMOTES(
    UUID CHARACTER(36) NOT NULL,
    NAME VARCHAR(256) NOT NULL PRIMARY KEY,
    DSP_NAME VARCHAR(256) NOT NULL,
    FLAGS BIGINT NOT NULL,
    URL VARCHAR(256) NOT NULL,
    REGION VARCHAR(256) NOT NULL,
    AVAILABILITY_ZONE VARCHAR(256) NOT NULL,
    ACCESS_KEY BLOB NOT NULL,
    SECRET_KEY BLOB NOT NULL
);
