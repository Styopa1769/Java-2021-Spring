select count(*), cust_country
from customer
group by cust_country;

-- средний grade для каждой страны
select
from customer
group by ;

select count(*), cust_country
from customer
group by cust_country
having avg(grade) > 1.5;

-- вывести средний payment_amt для каждого grade только для покупателей из USA, средний receive_amt больше 7000
select *
from customer;
