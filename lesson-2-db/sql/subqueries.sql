select *
from customer
where cust_code in (select cust_code from orders);

-- вывести покупателей у которых есть заказы с ORD_AMOUNT < 500 с помощью подзапросов
select *
from customer
where ;