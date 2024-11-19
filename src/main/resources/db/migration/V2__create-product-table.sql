CREATE TABLE products (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    quantity INT NOT NULL,
    min_quantity INT NOT NULL,
    category ENUM('eletronic', 'case', 'pellicle') NOT NULL
);