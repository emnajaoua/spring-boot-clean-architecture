CREATE TABLE stocks (
                        id UUID PRIMARY KEY,
                        date DATE NULL,
                        symbol TEXT NULL,
                        volume BIGINT NULL,
                        variation DOUBLE PRECISION NULL,
                        spread DOUBLE PRECISION NULL,
                        high DOUBLE PRECISION NULL,
                        low DOUBLE PRECISION NULL
);
