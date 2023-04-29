CREATE TABLE PRODUCT(
    product_id BIGINT NOT NULL,
    sequence BIGINT NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY (product_id)
) as select * from CSVREAD('./src/main/resources/files/product.csv', 'product_id,sequence', 'charset=UTF-8 fieldSeparator=,');

CREATE TABLE SIZE(
    size_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    back_soon BIT NOT NULL,
    special BIT NOT NULL,

    CONSTRAINT pk_size PRIMARY KEY (size_id)
) as select * from CSVREAD('./src/main/resources/files/size.csv', 'size_id,product_id,back_soon,special', 'charset=UTF-8 fieldSeparator=,');

CREATE TABLE STOCK(
    size_id BIGINT NOT NULL,
    quantity INT NOT NULL,

    CONSTRAINT pk_stock PRIMARY KEY (size_id)
) as select * from CSVREAD('./src/main/resources/files/stock.csv','size_id,quantity', 'charset=UTF-8 fieldSeparator=,');