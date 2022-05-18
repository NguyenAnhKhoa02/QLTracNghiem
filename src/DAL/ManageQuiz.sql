create database ManageQuiz
use ManageQuiz
create table Students(
	Id char(50) not null,
	FullName nvarchar(MAX),
	Birthday date,
	IdField char(10) not null
);

alter table Students add constraint PK_Students primary key (Id)
alter table Students add constraint FK_Students_Fields foreign key (IdField) references Fields(Id)

insert into Students(Id,FullName,Birthday,IdField) values ('234',N'Nguyễn Văn B','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('123',N'Nguyễn Văn A','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('345',N'Nguyễn Văn C','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('456',N'Nguyễn Văn C','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('567',N'Nguyễn Văn D','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('678','Nguyễn Văn E','01-01-2002','CNTT');

create table Fields(
	Id char(10) not null,
	NameField nvarchar(MAX)
)
alter table Fields add constraint PK_Fields primary key (Id)
insert into Fields (Id,NameField) values('CNTT',N'Công nghệ thông tin');

create table Lectures(
	Id varchar(10) not null,
	FullName nvarchar(MAX),
	IdField char(10),
	Position nvarchar(MAX)
)
alter table Lectures add constraint PK_Lectures primary key (Id)
alter table Lectures add constraint FK_Lectures_Fields foreign key (IdField) references Fields(Id)

insert into Lectures (Id,FullName,IdField,Position) values('1',N'Trần Văn A','CNTT',N'Giảng viên');

create table Subjects(
	Id varchar(10) not null,
	NameSubject nvarchar(MAX),
	Credit int
)

alter table Subjects add constraint PK_Subjects primary key (Id)
insert into Subjects(Id,NameSubject,Credit) values('LTJV',N'Lập trình Java',4);

create table Questions(
	Id varchar(10) not null,
	LevelQues varchar(10),
	Content nvarchar(MAX),
	Answer nvarchar(10),
	IdSubject varchar(10),
	IdLecture varchar(10),
	TypeQues varchar(100),
)

alter table Questions add constraint PK_Questions primary key (Id)
alter table Questions add constraint FK_Questions_Subjects foreign key (IdSubject) references Subjects(Id);
alter table Questions add constraint FK_Questions_Lectures foreign key (Idlecture) references Lectures(Id);
alter table Questions add constraint FK_Questions_Levels foreign key (LevelQues) references Levels(Id)

insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture,TypeQues) values (1,'1','abc','a','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (2,'1','bcd','b','LTJV','1','Options')

create table Levels(
	Id varchar(10) not null,
	LvName nvarchar(MAX)
)

alter table Levels add constraint PK_Levels primary key(Id)

insert into Levels(Id,LvName) values ('1',N'Dễ')
insert into Levels(Id,LvName) values ('2',N'Trung bình')
insert into Levels(Id,LvName) values ('3',N'Khó')
insert into Levels(Id,LvName) values ('4',N'Xuất sắc')

create table StudentsLogin(
	userStudent char(50) not null,
	pass varchar(100)
)

alter table StudentsLogin add constraint PK_StudentsLogin primary key(userStudent)
alter table StudentsLogin add constraint FK_StudentsLogon_Students foreign key (userStudent) references Students(Id)

insert into StudentsLogin(userStudent,pass) values ('123','01012002')

create table YesNoQuestion(
	Id varchar(10) not null,
	Options nvarchar(MAX)
)

alter table YesNoQuestion add constraint PK_YesNoQuestion primary key (Id)
alter table YesNoQuestion add constraint FK_YesNoQuestion_Question foreign key(Id) references Questions(Id)
insert into YesNoQuestion(Id,Options) values (1,N'a.Đúng b.Sai')

create table OptionsQuestion(
	Id varchar(10) not null,
	Options nvarchar(MAX)
)

alter table OptionsQuestion add constraint PK_OptionsQuestion primary key (Id)
alter table OptionsQuestion add constraint FK_OptionsQuestion_Question foreign key (Id) references Questions(Id)

insert into OptionsQuestion (Id,Options) values (2,N'a.a b.b c.c d.d')

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ
where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = '2'

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ
where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '2'
