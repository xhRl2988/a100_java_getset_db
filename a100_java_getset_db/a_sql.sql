select * from emp_master;

create table sungil_jumsu_tbl (
	hakbun	number(5),
	kor		number(3),
	eng		number(3),
	math	number(3),
	sum		number(3),
	avg		number(5,2),
	rank	number(3)
);
	
desc sungil_jumsu_tbl;
drop table sungil_jumsu_tbl;

select * from sungil_jumsu_tbl; 