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
insert into Lectures (Id,FullName,IdField,Position) values('2',N'Lê Văn B','CNTT',N'Giảng viên');
insert into Lectures (Id,FullName,IdField,Position) values('3',N'Nguyễn Văn C','CNTT',N'Trưởng khoa');

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

insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (1,'1',N'File chứa mã nguồn java sau khi được biên dịch có đuôi là gì?','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (2,'2',N'Java platform gồm mấy thành phần?','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (3,'1',N'Java Virtual Machine là gì?','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (4,'1',N'Java chạy trên hệ điều hành nào sau đây:','d','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (5,'1',N'Hãy nhận xét phát biểu: Một lớp trong java có thể có nhiều lớp cha.','b','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (6,'1',N'Ngôn ngữ lập trình Java cung cấp các chức năng nào sau đây?','d','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (7,'1',N'Chú thích: /*abc là đúng hay hai','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (8,'1',N'Cách đặt tên: 2word là đúng hay sai','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (9,'1',N'Tên hàm tạo của lớp','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (10,'1',N'Java chỉ chạy được trên hệ điều hành Window, đúng hay sai?','b','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (11,'1',N'Từ khóa new kết hợp với hàm tạo để tạo ra đối tượng','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (12,'2',N'Để khai báo lớp Xedap1 kế thừa lớp Xedap phải làm như thế nào?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (13,'2',N'Để sử dụng giao diện Xedap cho lớp Xedap1, ta làm thế nào?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (14,'2',N'Với giá trị nào của x, biểu thức trả về giá trị true (x thuộc kiểu int). x%3 == 0','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (15,'2',N'Nếu String name = "TP HCM"; thì câu lệnh name.length(); sẽ trả về bao nhiêu?','b','LTJV','1','Options')
	



update Questions
set Content = N'Nếu String name = "TP HCM"; thì câu lệnh name.length(); sẽ trả về bao nhiêu?'
where Id = 15

update Questions
set Answer = 'b'
where Id = 15

delete from Questions

create table Levels(
	Id varchar(10) not null,
	LvName nvarchar(MAX)
)

alter table Levels add constraint PK_Levels primary key(Id)

insert into Levels(Id,LvName) values ('1',N'Dễ')
insert into Levels(Id,LvName) values ('2',N'Trung bình')
insert into Levels(Id,LvName) values ('3',N'Khó')
insert into Levels(Id,LvName) values ('4',N'Xuất sắc')

update Levels
set LvName = N'Vận dụng cao'
where Id = 4

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
insert into YesNoQuestion(Id,Options) values (5,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (7,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (8,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (10,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (11,N'a.Đúng b.Sai')


create table OptionsQuestion(
	Id varchar(10) not null,
	Options nvarchar(MAX)
)

alter table OptionsQuestion add constraint PK_OptionsQuestion primary key (Id)
alter table OptionsQuestion add constraint FK_OptionsQuestion_Question foreign key (Id) references Questions(Id)

insert into OptionsQuestion (Id,Options) values (1,N'a. .java b. .class c. .jav d. .exe')
insert into OptionsQuestion (Id,Options) values (2,N'a.1 b.2 c.3 d.4')
insert into OptionsQuestion (Id,Options) values (3,N'a. Là một thành phần của Java platform dùng để đọc mã bytecode trong file .class b. Là chương trình biên dịch của java dùng để biên dịch file nguồn java thành mã bytecode c. Là chương trình chạy cho java d. Tất cả các đáp án đều đúng')
insert into OptionsQuestion (Id,Options) values (4,N'a. Microsoft Windows b. Linux c. Sun Solaris OS d. Tất cả các đáp án đều đúng.')
insert into OptionsQuestion (Id,Options) values (6,N'a. Giao diện lập trình ứng dụng b. Bộ công cụ giao diện người dùng c. Thư viện tích hợp d. Tất cả các đáp án đều đúng.')
insert into OptionsQuestion (Id,Options) values (9,N'a. Là một tên bất kì b. Cần trùng với tên lớp c. Cần trùng với tên lớp, trước hàm tạo thêm ký tự (~) d. Tất cả đều sai')
insert into OptionsQuestion (Id,Options) values (12,N'a. class Xedap1 extend Xedap {} b. public classs Xedap1 extend Xedap {} c. class Xedap1 extends Xedap {} d. Tất cả đều sai.')
insert into OptionsQuestion (Id,Options) values (13,N'a. class Xedap1 implement Xedap {} b. public class Xedap1 implement Xedap {} c. class Xedap1 implements Xedap {} d. public class Xedap1 extends Xedap {}')
insert into OptionsQuestion (Id,Options) values (14,N'a. 2 b. 7 c. 4 d. 9')
insert into OptionsQuestion (Id,Options) values (15,N'a. 5 b. 6 c. 7 d. 8')


delete from OptionsQuestion
update OptionsQuestion
set Options = N'a. 5 b. 6 c. 7 d. 8'
where Id = 15

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ
where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = '4'

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ
where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '2'
