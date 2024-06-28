CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY,
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(50) NOT NULL
);

CREATE TABLE boardstate (
    boardstate NVARCHAR(9) NOT NULL,
    ActivePlayer INT NOT NULL
);

CREATE TABLE playerslogged (
    NoOfPlayers INT,
    userID INT FOREIGN KEY REFERENCES Users(id)
);

drop table playerslogged
drop table boardstate

insert into Users values('edy', 'edy')
insert into Users values('edi', 'edi')

select * from playerslogged
delete from playerlogged
select * from Users
select * boardstate