select count(*)
from customer c, orders o;

select *
from customer c, orders o
where c.cust_code = o.cust_code;

select *
from customer left join (select * from orders where ord_amount>1000) o on customer.cust_code = o.cust_code;

select *
from (select * from customer where grade > 2) c right join orders o on c.cust_code = o.cust_code;
select *
from (select * from customer where grade > 2) c inner join orders o on c.cust_code = o.cust_code;

select *
from customer full outer join orders o on customer.cust_code = o.cust_code;