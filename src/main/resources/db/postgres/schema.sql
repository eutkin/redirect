CREATE TABLE sources (
  path        VARCHAR(400) PRIMARY KEY    NOT NULL,
  type        VARCHAR(30)                 NOT NULL,
  is_random   BOOLEAN                     NOT NULL,
  black_list  VARCHAR(400),
  black_url   VARCHAR(400)                NOT NULL,
  description VARCHAR(400)
);

CREATE TABLE redirect_log (
  id            UUID PRIMARY KEY                               NOT NULL,
  recirect_time TIMESTAMP                                      NOT NULL DEFAULT current_timestamp,
  ip            VARCHAR(400)                                   NOT NULL,
  redirect_from VARCHAR(400) REFERENCES sources (path)         NOT NULL,
  url           VARCHAR(400)                                   NOT NULL,
  guid          VARCHAR(400)
);

CREATE TABLE parameters (
  domain    VARCHAR(400) NOT NULL,
  src_name  VARCHAR(50)  NOT NULL,
  dest_name VARCHAR(50)  NULL,
  PRIMARY KEY (domain, src_name)
);

CREATE TABLE destinations (
  id          UUID PRIMARY KEY                                              NOT NULL,
  source_path VARCHAR(400)                                                  NOT NULL,
  domain      VARCHAR(400)                                                  NOT NULL,
  url         VARCHAR(400)                                                  NOT NULL,
  is_default  BOOLEAN DEFAULT FALSE                                         NOT NULL,
  FOREIGN KEY (source_path) REFERENCES sources (path)
);

CREATE TABLE dest_params (
  id       UUID PRIMARY KEY  NOT NULL,
  dest_id  UUID              NOT NULL,
  domain   VARCHAR(400)      NOT NULL,
  src_name VARCHAR(50)       NOT NULL,
  FOREIGN KEY (domain, src_name) REFERENCES parameters (domain, src_name),
  FOREIGN KEY (dest_id) REFERENCES destinations (id)
);

