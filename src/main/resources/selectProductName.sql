SELECT product_name
FROM netology.orders
         JOIN netology.customers ON netology.orders.customer_id = netology.customers.id
WHERE LOWER(netology.customers.name) = :name;