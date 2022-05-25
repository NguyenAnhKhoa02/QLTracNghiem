create database ManageQuiz
use  ManageQuiz
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

select s.Id, s.FullName,s.Birthday,f.NameField
from Students s, Fields f
where s.IdField = f.Id and s.Id = '234'

create table Fields(
	Id char(10) not null,
	NameField nvarchar(MAX)
)
alter table Fields add constraint PK_Fields primary key (Id)
insert into Fields (Id,NameField) values('CNTT',N'Công nghệ thông tin');

select * from Fields

create table Lectures(
	Id varchar(10) not null,
	FullName nvarchar(MAX),
	IdField char(10),
	Position nvarchar(MAX)
)
alter table Lectures add constraint PK_Lectures primary key (Id)
alter table Lectures add constraint FK_Lectures_Fields foreign key (IdField) references Fields(Id)

select FullName 
from Lectures
where Id=1

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

select Id,NameSubject
from Subjects


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
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (16,'2',N'Với bộ quản lý trình bày BorderLayout không nhất thiết các vùng phải có chứa các component','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (17,'2',N'Chọn các component có phát sinh action event','a','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (18,'2',N'Phương thức getContent() của lớp URL có kết quả trả về thuộc kiểu Object đúng hay sai?','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (19,'2',N'Bộ quản lý trình bày mặc định cho một khung chứa kiểu Panel là:','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (20,'2',N'Cách đặt tên nào sau đây không chính xác?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (21,'3',N'Chọn phát biểu đúng','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (22,'3',N'Chọn phát biểu đúng cho hàm khởi tạo:','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (23,'3',N'Lệnh str.charat(n) có tác dụng gì','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (24,'3',N'Giá trị mặc định của một biến kiểu char là: ','a','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (25,'3',N'Lệnh sau in ra cái gì? final int a = 6; a = 7; System.out.println("a=" + a)','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (26,'3',N'int[]a={1,2,3}; System.out.print("a[3]="+a[3]);, chương trình sẽ in ra gì?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (27,'3',N'int x = 10; double y = 0.0; System.out.println("KQ =" + (x/y); chương trình sẽ in ra gì?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (28,'3',N'int x = 10; int y = 5; if(x=y) System.out.println("x=" + x); chương trình có lỗi đúng hay không?','a','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (29,'3',N'int x = 1; boolean[] y = new boolean[3]; boolean foo = y[x]; kết quả foo nhận được là: ','d','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (30,'3',N'public class MyFrame implements ActionListener{ MyFrame(){} }, đoạn code không có lỗi đúng hay sai ','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (31,'4',N'Đoạn code nào không in ra số 13','b','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (32,'4',N'Đoạn code int x=-1; String y =x+3; System.out.println("x=" + x + "y=" + y); không có lỗi đúng hay sai?','b','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (33,'4',N'Hoạt động của Thread sẽ kết thúc khi','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (34,'4',N'Đâu không phải là thành phần trong cấu trúc lớp của Java','d','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (35,'4',N'int i=0; do{i += 1;} while(i==0); System.out.println("i="+i); là đoạn code không có lỗi.','a','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (36,'4',N'int i=0; int s=0; for(;;){if i==3 break; s=s+i; i++} là đoạn code không có lỗi','b','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (37,'4',N'Lệnh result=condition? value1 : value2 có nghĩa là gì?','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (38,'4',N'Để đảo giá trị của một biến boolean, ta dùng toán tử nào?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (39,'4',N'Nếu không khai báo từ khóa chỉ phạm vi truy cập, phạm vi truy cập của đối tượng là gì?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values (40,'4',N'Trong Java, kiểu char biểu diễn bộ mã code nào dưới đây?','b','LTJV','2','Options')
select * from Questions where Id=41
delete from Questions where Id=41

select Answer 
from Questions
where id = '5'

update Questions
set Content =N'int i=0; int s=0; for(;;){if i==3 break; s=s+i; i++;} là đoạn code không có lỗi'
where Id = 36

update Questions
set Answer = 'c'
where Id = 26

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
insert into YesNoQuestion(Id,Options) values (16,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (18,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (28,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (30,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (32,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (35,N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values (36,N'a.Đúng b.Sai')

delete from YesNoQuestion
create table OptionsQuestion(
	Id varchar(10) not null,
	Options nvarchar(MAX)
)

select Id,Answer
from Questions
where Id='1'

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
insert into OptionsQuestion (Id,Options) values (17,N'a. Button b. Label c. Checkbox d. Windows')
insert into OptionsQuestion (Id,Options) values (19,N'a. FlowLayout b. Borderlayout c. GridLayout d. GridBagLayout')
insert into OptionsQuestion (Id,Options) values (20,N'a. final b. dem c. _final d. $final')
insert into OptionsQuestion (Id,Options) values (21,N'a. Một lớp trừu tượng không thể chứa phương thức final b. Một lớp final không thể chứa các phương thức trừu tượng c. Cả hai đều đúng d. Cả hai đều sai')
insert into OptionsQuestion (Id,Options) values (22,N'a. Một lớp sẽ kế thừa các hàm khởi tạo từ lớp cha b. Trình biên dịch sẽ tự động tạo hàm khởi tạo mặc định nếu lớp không định nghĩa hàm khởi tạo c. Tất cả các hàm khởi tạo có kiểu trả về là void d. Tất cả các câu trên dều sai')
insert into OptionsQuestion (Id,Options) values (23,N'a. Lấy một ký tự bất kì trong chuỗi str b. Lấy độ dài chuỗi str c. Lấy ký tự có số chỉ mục n trong chuỗi k d. Không có lệnh này')
insert into OptionsQuestion (Id,Options) values (24,N'a. u0000 b. \uFFFF c. 0F d. 0x')
insert into OptionsQuestion (Id,Options) values (25,N'a. a = 6 b. a = 7 c. Lỗi dòng 3 d. Lỗi dòng 2')
insert into OptionsQuestion (Id,Options) values (26,N'a. a[3] = 3 b. null c. ArrayIndexOutOfBoundsException d. NullPoiterException')
insert into OptionsQuestion (Id,Options) values (27,N'a. KQ = 0 b. Lỗi biên dịch c. KQ = Infinity d. KQ = ')
insert into OptionsQuestion (Id,Options) values (29,N'a. 0 b. null c. true d. false ')
insert into OptionsQuestion (Id,Options) values (31,N'a. int x=13; System.out.println(x); b. int x=13; System.out.println("x"); c. int x=13; System.out.println("13"); d. int x=13; System.out.println(""+x);')
insert into OptionsQuestion (Id,Options) values (33,N'a. Thoát khỏi phương thức run với các điều kiện dừng cho thread trong phương thức b. Thread hoàn thành một công việc nào đó c. Gọi phương thức Delete() d. Gọi phương thức Exit()')
insert into OptionsQuestion (Id,Options) values (34,N'a. Tên lớp b. Thuộc tính c. Phương thức d. Biến')
insert into OptionsQuestion (Id,Options) values (37,N'a. Nếu condition là true thì result = value2, nếu condition là false thì result = value1 b. Nếu condition là true thì result = value1, nếu condition là false thì result = value2 c. Không có lệnh này d. Nếu condition là true thì result sẽ đảo giá trị của value1 và value2')
insert into OptionsQuestion (Id,Options) values (38,N'a. ! b. >> c. << d. !!')
insert into OptionsQuestion (Id,Options) values (39,N'a. Có thể truy cập các lớp từ trong cùng package b. Có thể truy cập đối tượng từ các lớp trong cùng package và lớp con nằm trong package c. Có thể truy cập đối tượng từ các phương thức khác trong lớp đó d. Có thể truy cập đối tượng từ bất kì vị trí nào của chương trình')
insert into OptionsQuestion (Id,Options) values (40,N'a. UTF-8 b. UTF-16 c. UTF-32 d. Tất cả đều sai')
select * from OptionsQuestion where Id =41
delete from OptionsQuestion where Id=41

delete from OptionsQuestion
update OptionsQuestion
set Options = N'a. KQ = 0 b. Lỗi biên dịch c. KQ = Infinity d. KQ = '
where Id = 27

create table Exam(
	Id nvarchar(10) not null,
	TimeName nvarchar(500) not null
)

alter table Exam add constraint PK_Exam primary key(Id)
alter table Exam add constraint FK_Exam_TimeExam foreign key (TimeName) references TimeExam(IdName)

insert into Exam (Id,TimeName) values (123,N'Kì thi kết thúc học phần 2022 - 2023')
select Id 
from Exam

delete from Exam
where Id = '886'

create table DetailExam(
	Id nvarchar(10) not null,
	IdQues varchar(10) not null
)

alter table DetailExam add constraint PK_IdDetailExam primary key (Id,IdQues)
alter table DetailExam add constraint FK_DetailExam_Exam foreign key (Id) references Exam(Id)
alter table DetailExam add constraint FK_DetailExam_Question foreign key (IdQues) references Questions(Id)
insert into DetailExam (Id,IdQues) values ('597','1')
select qs.Answer
from DetailExam de, Questions qs
where de.Id='994' and de.IdQues = qs.Id

delete from DetailExam
where Id = '533'

create table TimeExam(
	IdName nvarchar(500) not null,
	TimeName varchar(100) not null,
	TimeTest int
)

alter table TimeExam add constraint PK_TimeExam primary key (IdName)

insert into TimeExam (IdName,TimeName,TimeTest) values (N'Kì thi kết thúc học phần 2022 - 2023','HKII', 60)
 

select * from TimeExam

create table ExamPapers(
	IdStudent char(50) not null,
	IdExam nvarchar(10) not null,
	Id nvarchar(10) not null,
	NumberRight int,
	NumberWrong int,
	Mark float
)

alter table ExamPapers add constraint PK_ExamPapers primary key (Id)
alter table ExamPapers add constraint FK_ExamPapers_Exam foreign key (IdExam) references Exam(Id)
alter table ExamPapers add constraint FK_ExamPapers_Students foreign key (IdStudent) references Students (Id)

insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('123','103','32',3,7,8)
select * from ExamPapers

 

alter table DetailExamPaper drop constraint PK_DetailExamPaper

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ
where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = '4'

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ
<<<<<<< HEAD
where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '2'


		select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options 
        from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ 
        where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id
=======
where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '5'
order by qs.Id + 0 ASC

use  ManageQuiz

select qs.Id
from Questions qs, Levels lv
where lv.LvName =N'Nhận biết' and qs.LevelQues = lv.Id

select qs.Content, ynQ.Options
from Questions qs,YesNoQuestion ynQ
where  qs.Id = '1' and qs.Id = ynQ.Id

select qs.Id
from Questions qs,OptionsQuestion opQ
where  qs.Id = '9' and qs.Id = opQ.Id

select sbj.Id,sbj.NameSubject, sbj.Credit
from Questions qs, Subjects sbj
where  qs.Id = '9' and qs.IdSubject = sbj.Id

select qs.Content, ynQ.Options
from Questions qs, YesNoQuestion ynQ
where qs.Id = ynQ.Id and qs.Id = '10'
>>>>>>> af8d8aeddce60f1fbe5beaf5d3df7101eaab020d
