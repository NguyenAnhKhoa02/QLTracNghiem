create database ManageQuiz
use  ManageQuiz
create table Students(
	Id char(50) not null,
	FullName nvarchar(MAX),
	Birthday date,
	IdField char(10) not null
);
DROP DATABASE ManageQuiz
alter table Students add constraint PK_Students primary key (Id)
alter table Students add constraint FK_Students_Fields foreign key (IdField) references Fields(Id)
select * from Students
delete from Students
insert into Students(Id,FullName,Birthday,IdField) values ('234',N'Nguyễn Văn B','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('123',N'Nguyễn Văn A','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('345',N'Nguyễn Văn C','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('456',N'Nguyễn Văn C','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('567',N'Nguyễn Văn D','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('678',N'Nguyễn Văn E','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('789',N'Nguyễn Văn F','02-07-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('891',N'Nguyễn Văn G','01-05-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('628',N'Nguyễn Văn H','01-01-2002','CNTT');
insert into Students(Id,FullName,Birthday,IdField) values ('618',N'Nguyễn Văn I','01-01-2002','CNTT');

select Id
from Students

create table Fields(
	Id char(10) not null,
	NameField nvarchar(MAX)
)
alter table Fields add constraint PK_Fields primary key (Id)
insert into Fields (Id,NameField) values('CNTT',N'Công nghệ thông tin');

<<<<<<< HEAD

'234',N'Nguyễn Văn B','01-01-2002','CNTT'
nút xem bài thi
nút làm bài thi
nút xem kết quả

select * from Students
select * from Fields
 
=======
select * from Fields
>>>>>>> 8da57441668ab3ef1ad7f3f1a5af2db212caca59

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
insert into Subjects(Id,NameSubject,Credit) values('LTJV',N'Ngôn ngữ lập trình Java',4);
insert into Subjects(Id,NameSubject,Credit) values('LTPT',N'Ngôn ngữ lập trình Python',3);
insert into Subjects(Id,NameSubject,Credit) values('MNM',N'Hệ điều hành mã nguồn mở',4);

update Subjects
set NameSubject = N'Ngôn ngữ lập trình Java'
where Id = 'LTJV'

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

select qs.Id
from Questions qs, Levels lv
where lv.LvName = N'Nhận biết' and lv.Id = qs.LevelQues

alter table Questions add constraint PK_Questions primary key (Id)
alter table Questions add constraint FK_Questions_Subjects foreign key (IdSubject) references Subjects(Id);
alter table Questions add constraint FK_Questions_Lectures foreign key (Idlecture) references Lectures(Id);
alter table Questions add constraint FK_Questions_Levels foreign key (LevelQues) references Levels(Id)

insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT1','1',N'Câu hỏi','Câu trả lời (a,b,c,d)','Mã môn (PT)','Mã giảng viên ra đề (1,2 hoặc 3)','Options-Yes/no')
delete from Questions --
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT1','1',N'Nhận xét này đúng hay sai: Python là ngôn ngữ mã nguồn mở.','a','LTPT','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT2','1',N'Các khối code (khối lệnh của hàm, vòng lặp,...) trong Python được xác định?','b','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT3','1',N'Nội dung của chú thích sẽ được trình thông dịch bỏ qua, đúng hay sai?','a','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT4','1',N'Khẳng định nào là đúng về chú thích trong Python?','c','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT5','1',N'Phép toán nào có thể được dùng để so sánh hai biến ?','a','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT6','1',N'Câu lệnh nào sau đây in ra kiểu dữ liệu của biến x?','a','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT7','1',N'Câu lệnh nào sau đây được dùng để gán giá trị cho x = 1, y = 2, z = 3 ?','c','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT8','1',N'print(0b1010). Kết quả của câu lệnh này là?','a','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT9','1',N'print(type(hex(15))). Kết quả của câu lệnh này là?','b','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT10','1',N'Python là một ngôn ngữ không phân biệt kiểu chữ HOA, chữ thường. Nhận xét này đúng hay sai?','b','LTPT','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT11','2',N'print(type(type(float)))','a','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT12','2',N'Tên biến có thể chứa dấu gạch dưới "_". Nhận xét này đúng hay sai','a','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT13','2',N'Biến L trong đoạn L = "[5:2,10:[1,2]" sau là kiểu dữ liệu Tuple. Đúng hay sai?','b','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT14','2',N'Lệnh nào dùng để nhập dữ liệu từ bàn phím trong Python?','c','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT15','2',N'Kết quả của lệnh print(100 >= 10**2) là False. Đúng hay sai?','b','LTPT','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT16','2',N'Kết quả của lệnh print(10//3,10%3,10/3,sep=" , ")','a','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT17','2',N' Trong Python, hàm replace() được dùng để thay thế một phần của chuỗi. Đúng hay sai?','a','LTPT','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT18','2',N'Trong Python, hàm nào được dùng để chuyển đổi các ký tự in thường sang in hoa ?','d','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT19','2',N'Output của lệnh print("Hello World"[::-1]) dưới đây là:','d','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT20','2',N'Trong Python, hàm split() được dùng để kết hợp các chuỗi. Đúng hay sai?','b','LTPT','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT21','3',N'Kiểu dữ liệu nào sau đây chứa các phần tử có thứ tự, có thể được thay đổi và cho phép trùng nhau?','d','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT22','3',N'Kết quả của hàm print(len(["hello", 2, 4, 6])) là gì?','a','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT23','3',N'Điểm khác biệt giữa tuple và list là: Tuple chứa các phần tử có thứ tự, list chứa các phần tử không có thứ tự.','b','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT24','3',N'Đâu không phải là kiểu dữ liệu tiêu chuẩn trong Python?','c','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT25','3',N'Hàm có thể được khai báo ở Trong Class. Đúng hay sai?','a','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT26','3',N'Hàm nào sau đây là hàm tích hợp sẵn trong Python?','d','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT27','3',N'Kết quả của câu lệnh print(round(4.567)) là gì?','b','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT28','3',N'Đoạn code f = open("test.txt") có ý nghĩa gì?','b','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT29','3',N'Ý nghĩa của hàm __init__() trong Python là Khởi tạo một lớp để sử dụng. Đúng hay sai?','b','LTPT','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT30','3',N'Khẳng định: Bạn có thể thay đổi cách các toán tử hoạt động trong Python. Đúng hay sai?','a','LTPT','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT31','4',N'Từ khóa nào được sử dụng để bắt đầu hàm?','c','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT32','4',N'Hàm có thể được khai báo ở đâu?','d','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT33','4',N'Đâu là lợi thế của việc sử dụng hàm trong Python?','d','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT34','4',N'Mở file với chế độ mode " a " có ý nghĩa gì?','c','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT35','4',N'Mở file với chế độ mode " wb " có ý nghĩa gì?','a','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT36','4',N'Ý nghĩa của hàm __init__() trong Python là gì?','b','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT37','4',N'Kiểu dữ liệu nào sau đây là DICTIONARY?','c','LTPT','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT38','4',N'Kiểu dữ liệu nào sau đây là LIST?','d','LTPT','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT39','4',N'Kiểu dữ liệu nào sau đây là TUPLE?','a','LTPT','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTPT40','4',N'Kiểu dữ liệu nào sau đây là SET?','b','LTPT','1','Options')
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM1','1',N'Phần quan trọng nhất của hệ điều hành Linux là','c','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM2','2',N'Số phiên bản kernel của Linux có gì đặc biệt','d','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM3','1',N'Khi cài RedHat Linux ở chế độ nào thì các partion DOS bị xoá hết','d','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM4','1',N'Kiến trúc Kernel Linux là:','d','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM5','1',N'Tác giả của phiên bản hệ điều hành Linux đầu tiên là Pascal','b','MNM','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM6','1',N'Để liệt kê các file có trong thư mục hiện hành ta dùng lệnh: ','a','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM7','1',N'Để liệt kê đầy đủ thông tin của các file có trong thư mục hiện hành theo ta dùng lệnh ls -l','a','MNM','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM8','1',N'Để liệt kê các file ẩn trong thư mục hiện hành theo ta dùng lệnh ls -b. Sai hay đúng?','b','MNM','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM9','1',N'Để chuyển sang một thư mục khác ta dùng lệnh','b','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM10','1',N'Một user có username là sinhvien và home directory của anh ta là /home/sinhvien. Để trở về home dir của anh ta 1 cách nhanh nhất, anh ta phải dùng lệnh cd','b','MNM','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM11','1',N'Để chép một file /tmp/hello.txt vào thư mục /tmp/hello/ ta phải làm lệnh cp tmp/hello.txt /tmp/hello. Đúng hay sai?','a','MNM','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM12','2',N'Để xem nội dung một tập tin văn bản trong Linux ta có thể dùng lệnh nào sau đây','d','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM13','2',N'Để đọc nội dung một đĩa CD trong Linux ta phải làm thế nào ?','a','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM14','2',N'Làm thế nào để đọc một đĩa mềm trong Linux','a','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM15','2',N'Tập tin nào chứa các mount point mặc định khi hệ thống boot lên:','c','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM16','2',N'Tập tin chứa thông tin các file system đang được mount là /etc/modules.conf','a','MNM','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM17','2',N'Tập tin sau đây có thuộc tính như thế nào? -rwx--x--x hello.sh','d','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM18','2',N'Để thiết lập thuộc tính cho một tập tin hello.sh với các yêu cầu sau: Chủ sở hữu được quyền đọc ghi, nhóm được đọc, thực thi, other chỉ đọc thì ta làm lệnh chmod 654','a','MNM','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM19','2',N'Tập tin có dấu chấm “.” Phía trước có đặc tính gì đặc biệt: Ví dụ: .hello.txt','a','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM20','2',N'Lệnh nào cho phép ta tạo một account user mới trên hệ thống','b','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM21','3',N'Tập tin /etc/passwd chứa thông tin gì của users hệ thống','b','MNM','2','Options') --chuyen thanh cau 22 tren web
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM22','3',N'Tập tin /etc/shadow chứa thông tin gì của users hệ thống','c','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM23','3',N'Trong hệ thống Linux user nào có quyền cao nhất','b','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM24','3',N'Hệ thống Linux có mấy Run Level chính','b','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM25','3',N'Run level nào là shutdown và halt hệ thống','b','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM26','3',N'Run level nào là reboot hệ thống','a','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM27','3',N'Run level nào làm hệ thống chạy full mode with X window','d','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM28','3',N'Ở run level 1 hệ thống không đòi hỏi ta phải nhập username password để login. Đúng hay sai?','a','MNM','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM29','3',N'Chương trình soạn thảo văn bản nào là phổ biến nhất trong hđh Linux','a','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM30','3',N'Chương trình soạn thảo kword không sử dụng giao diện đồ hoạ','b','MNM','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM31','4',N'Emacs là một chương trình','a','MNM','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM32','4',N'Để xem các tiến trình hiện có trong hệ thống Linux ta dùng lệnh ps. Đúng hay sai?','a','MNM','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM33','4',N'Để xem chi tiết các tiến trình đang chạy trong hệ thống ta dùng lệnh ps với các tham số nào sau đây?','b','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM34','4',N'Mỗi tiến trình chạy trong hệ thống Linux được đặc trưng bởi:','a','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM35','4',N'Tham số PPIUD dùng để chỉ Parent process ID','a','MNM','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM36','4',N'Để dừng một tiến trình ta dùng lệnh nào stop. Đúng hay sai?','b','MNM','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM37','4',N'Điều gì xảy ra với một tiến trình khi ta kill tiến trình cha của nó','b','MNM','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM38','4',N'Để kill hết các tiến trình có tên là vi ta dùng lệnh nào?','c','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM39','4',N'Để thiết lập địa chỉ IP cho một máy Linux ta sử dụng lệnh nào trong các lệnh sau đây?','b','MNM','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('MNM40','4',N'Để xem trạng thái các port đang mở của một máy Linux ta sử dụng lệnh nào trong clệnh sau đây','c','MNM','2','Options')
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV1','1',N'File chứa mã nguồn java sau khi được biên dịch có đuôi là gì?','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV2','2',N'Java platform gồm mấy thành phần?','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV3','1',N'Java Virtual Machine là gì?','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV4','1',N'Java chạy trên hệ điều hành nào sau đây:','d','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV5','1',N'Hãy nhận xét phát biểu: Một lớp trong java có thể có nhiều lớp cha.','b','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV6','1',N'Ngôn ngữ lập trình Java cung cấp các chức năng nào sau đây?','d','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV7','1',N'Chú thích: /*abc là đúng hay sai','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV8','1',N'Cách đặt tên: 2word là đúng hay sai','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV9','1',N'Tên hàm tạo của lớp','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV10','1',N'Java chỉ chạy được trên hệ điều hành Window, đúng hay sai?','b','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV11','1',N'Từ khóa new kết hợp với hàm tạo để tạo ra đối tượng','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV12','2',N'Để khai báo lớp Xedap1 kế thừa lớp Xedap phải làm như thế nào?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV13','2',N'Để sử dụng giao diện Xedap cho lớp Xedap1, ta làm thế nào?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV14','2',N'Với giá trị nào của x, biểu thức trả về giá trị true (x thuộc kiểu int). x%3 == 0','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV15','2',N'Nếu String name = "TP HCM"; thì câu lệnh name.length(); sẽ trả về bao nhiêu?','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV16','2',N'Với bộ quản lý trình bày BorderLayout không nhất thiết các vùng phải có chứa các component','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV17','2',N'Chọn các component có phát sinh action event','a','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV18','2',N'Phương thức getContent() của lớp URL có kết quả trả về thuộc kiểu Object đúng hay sai?','a','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV19','2',N'Bộ quản lý trình bày mặc định cho một khung chứa kiểu Panel là:','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV20','2',N'Cách đặt tên nào sau đây không chính xác?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV21','3',N'Chọn phát biểu đúng','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV22','3',N'Chọn phát biểu đúng cho hàm khởi tạo:','b','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV23','3',N'Lệnh str.charat(n) có tác dụng gì','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV24','3',N'Giá trị mặc định của một biến kiểu char là: ','a','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV25','3',N'Lệnh sau in ra cái gì? final int a = 6; a = 7; System.out.println("a=" + a)','d','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV26','3',N'int[]a={1,2,3}; System.out.print("a[3]="+a[3]);, chương trình sẽ in ra gì?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV27','3',N'int x = 10; double y = 0.0; System.out.println("KQ =" + (x/y); chương trình sẽ in ra gì?','c','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV28','3',N'int x = 10; int y = 5; if(x=y) System.out.println("x=" + x); chương trình có lỗi đúng hay không?','a','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV29','3',N'int x = 1; boolean[] y = new boolean[3]; boolean foo = y[x]; kết quả foo nhận được là: ','d','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV30','3',N'public class MyFrame implements ActionListener{ MyFrame(){} }, đoạn code không có lỗi đúng hay sai ','b','LTJV','2','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV31','4',N'Đoạn code nào không in ra số 13','b','LTJV','3','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV32','4',N'Đoạn code int x=-1; String y =x+3; System.out.println("x=" + x + "y=" + y); không có lỗi đúng hay sai?','b','LTJV','3','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV33','4',N'Hoạt động của Thread sẽ kết thúc khi','a','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV34','4',N'Đâu không phải là thành phần trong cấu trúc lớp của Java','d','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV35','4',N'int i=0; do{i += 1;} while(i==0); System.out.println("i="+i); là đoạn code không có lỗi.','a','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV36','4',N'int i=0; int s=0; for(;;){if i==3 break; s=s+i; i++} là đoạn code không có lỗi','b','LTJV','1','Yes/No')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV37','4',N'Lệnh result=condition? value1 : value2 có nghĩa là gì?','b','LTJV','1','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV38','4',N'Để đảo giá trị của một biến boolean, ta dùng toán tử nào?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV39','4',N'Nếu không khai báo từ khóa chỉ phạm vi truy cập, phạm vi truy cập của đối tượng là gì?','a','LTJV','2','Options')
insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('LTJV40','4',N'Trong Java, kiểu char biểu diễn bộ mã code nào dưới đây?','b','LTJV','2','Options')
 
select qs.Id, qs.TypeQues
from Questions qs, Levels lv
where qs.IdSubject = 'LTJV' and lv.LvName=N'Nhận biết' and qs.LevelQues = lv.Id

select Id from Questions where Id=41
delete from Questions where Id=42

select TypeQues from Questions where Id  = 'LTJV1'

select Answer 
from Questions
where id = '5'

update Questions
set IdSubject = 'MNM'
where Id = 'MNM34'

update Questions
set Answer = 'c'
where Id = 26

delete from Questions

create table Levels(
	Id varchar(10) not null,
	LvName nvarchar(MAX)
)
alter table Levels add constraint PK_Levels primary key(Id)
delete from Levels
insert into Levels(Id,LvName) values ('1',N'Dễ')
insert into Levels(Id,LvName) values ('2',N'Trung bình')
insert into Levels(Id,LvName) values ('3',N'Khó')
insert into Levels(Id,LvName) values ('4',N'Xuất sắc')
select * from Levels
update Levels
set LvName = N'Vận dụng cao'
where Id = 4

create table StudentsLogin(
	userStudent char(50) not null,
	pass varchar(100)
)

alter table StudentsLogin add constraint PK_StudentsLogin primary key(userStudent)
alter table StudentsLogin add constraint FK_StudentsLogon_Students foreign key (userStudent) references Students(Id)

select * from StudentsLogin

insert into StudentsLogin(userStudent,pass) values ('123','01012002')
insert into StudentsLogin(userStudent,pass) values ('234','01012002')
insert into StudentsLogin(userStudent,pass) values ('345','01012002');
insert into StudentsLogin(userStudent,pass) values ('456','01012002');
insert into StudentsLogin(userStudent,pass)values ('567','01012002');
insert into StudentsLogin(userStudent,pass)values ('678','01012002');
insert into StudentsLogin(userStudent,pass)values ('789','01012002');
insert into StudentsLogin(userStudent,pass)values ('891','01012002');
insert into StudentsLogin(userStudent,pass)values ('628','01012002');
insert into StudentsLogin(userStudent,pass)values ('618','01012002');

delete from StudentsLogin

select * from StudentsLogin

create table LectureLogin(
	userLecture varchar(10) not null,
	pass varchar(100)
)

alter table LectureLogin add constraint PK_LectureLogin primary key(userLecture)
alter table LectureLogin add constraint FK_LectureLogon_Lecture foreign key (userLecture) references Lectures(Id)

<<<<<<< HEAD
<<<<<<< HEAD
insert into LectureLogin(userLecture,pass) values ('1','123')
insert into LectureLogin(userLecture,pass) values ('2','123')
insert into LectureLogin(userLecture,pass) values ('3','123')

create table YesNoQuestion(
	Id varchar(10) not null,
	Options nvarchar(MAX)
)


alter table YesNoQuestion add constraint PK_YesNoQuestion primary key (Id)
alter table YesNoQuestion add constraint FK_YesNoQuestion_Question foreign key(Id) references Questions(Id)

insert into YesNoQuestion(Id,Options) values ('LTJV5',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV7',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV8',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV10',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV11',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV16',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV18',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV28',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV30',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV32',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV35',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTJV36',N'a.Đúng b.Sai')
------
insert into YesNoQuestion(Id,Options) values ('MNM5',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM7',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM8',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM10',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM11',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM16',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM18',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM28',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM30',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM32',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM35',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('MNM36',N'a.Đúng b.Sai')
-----
insert into YesNoQuestion(Id,Options) values ('LTPT1',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT3',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT10',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT12',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT13',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT15',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT17',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT20',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT23',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT25',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT29',N'a.Đúng b.Sai')
insert into YesNoQuestion(Id,Options) values ('LTPT30',N'a.Đúng b.Sai')


delete from YesNoQuestion
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

select * from OptionsQuestion
delete from Questions

1 3 10 12 13 15 20 23 25 29 30
2 4 5 6 7 8 9 11 14 16 17 18 19 21 22 24 26 27 28 31 -> 40

insert into OptionsQuestion (Id,Options) values ('LTPT2',N'a. Dấu ngoặc nhọn { } b. Canh lề c. Dầu ngoặc đơn ( ) d. Dấu ngoặc vuông [ ]')
insert into OptionsQuestion (Id,Options) values ('LTPT4',N'a. Python sử dụng kí tự // để bắt đầu một chú thích b. Python sử dụng kí tự % để bắt đầu một chú thích c. Python sử dụng kí tự # để bắt đầu một chú thích  d. Python dùng ““ ”” (2 cặp nháy đôi) hoặc ‘‘ ’’(2 cập nháy đơn) để viết chú thích trên nhiều dòng]')
insert into OptionsQuestion (Id,Options) values ('LTPT5',N'a. == b. ** c. // d. =')
insert into OptionsQuestion (Id,Options) values ('LTPT6',N'a. print(type(x)) b. print(type[x]) c. print(typeof[x]) d. print(typeof(x))')
insert into OptionsQuestion (Id,Options) values ('LTPT7',N'a. x,y,z = 1;2;3 b. x;y;z = 1;2;3 c. x,y,z = 1,2,3 d. x;y;z = 1,2,3')
insert into OptionsQuestion (Id,Options) values ('LTPT8',N'a. 10 b. 0b1010 c.  1010 d. "0b1010"')
insert into OptionsQuestion (Id,Options) values ('LTPT9',N'a. <class "int"> b. <class "str"> c. 0xf d. "0xf"')
insert into OptionsQuestion (Id,Options) values ('LTPT11',N'a. <class "type"> b. <class "float"> c. Có xuất hiện lỗi Error d. None')
insert into OptionsQuestion (Id,Options) values ('LTPT14',N'a. cin b. scanf() c. input() d. read()')
insert into OptionsQuestion (Id,Options) values ('LTPT16',N'a. 3 , 1 , 3.3333333333333335 b. 1 , 3 , 3.3333333333333335 c. 3.3333333333333335 , 1 , 3 d. 3 , 3.3333333333333335 , 1')
insert into OptionsQuestion (Id,Options) values ('LTPT18',N'a. toUpperCase() b. uppercase() c. upperCase() d. upper()')
insert into OptionsQuestion (Id,Options) values ('LTPT19',N'a. Có xuất hiện lỗi Error b. Hello Worl c. d d. dlroW olleH')
insert into OptionsQuestion (Id,Options) values ('LTPT21',N'a. SET b. DICTIONARY c. .jav d. LIST')
insert into OptionsQuestion (Id,Options) values ('LTPT22',N'a. 4 b. 3 c. 8 d. None')
insert into OptionsQuestion (Id,Options) values ('LTPT24',N'a. List b. Dictionary c. Class d. Tuple')
insert into OptionsQuestion (Id,Options) values ('LTPT26',N'a. cos() b. sqrt() c. factorial() d. print()')
insert into OptionsQuestion (Id,Options) values ('LTPT27',N'a. List b. Dictionary c. Class d. Tuple')
insert into OptionsQuestion (Id,Options) values ('LTPT28',N'a. 4.5 b. 5 c. 4 d. 4.6')
insert into OptionsQuestion (Id,Options) values ('LTPT31',N'a. Mở file test.txt được phép đọc và ghi vào file b. Mở file test.txt và chỉ được phép đọc files c. Mở file test.txt và được phép ghi đè vào file d. Mở file test.txt và được phép ghi tiếp vào file')
insert into OptionsQuestion (Id,Options) values ('LTPT32',N'a. fun b. define c. def d. function')
insert into OptionsQuestion (Id,Options) values ('LTPT33',N'a. Trong module b. Trong Class c. Trong một hàm khác d. Tất cả các phương án trên')
insert into OptionsQuestion (Id,Options) values ('LTPT34',N'a. Tránh việc phải lặp lại code thực thi những tác vụ tương tự nhau. b. Phân tách các vấn đề phức tạp thành các phần đơn giản hơn c. Code rõ ràng, dễ quản lý hơn d. Tất cả các đáp án đều đúng')
insert into OptionsQuestion (Id,Options) values ('LTPT35',N'a. Mở file ở chế độ chỉ được phép đọc.  b. Mở file ở chế độ ghi.  c. Mở file chế độ ghi tiếp vào cuối file. d. Mở file để đọc và ghi')
insert into OptionsQuestion (Id,Options) values ('LTPT36',N'a. Mở file để ghi. b.  Mở file để đọc và ghi c. Mở file để ghi cho dạng nhị phân.  d. Mở file để đọc và ghi cho dạng nhị phân')
insert into OptionsQuestion (Id,Options) values ('LTPT37',N'a. ("apple", "banana", "cherry") b. {"apple", "banana", "cherry"} c. {"name": "apple", "color": "green"} d. ["apple", "banana", "cherry"]')
insert into OptionsQuestion (Id,Options) values ('LTPT38',N'a. ("apple", "banana", "cherry") b. {"apple", "banana", "cherry"} c. {"name": "apple", "color": "green"} d. ["apple", "banana", "cherry"]')
insert into OptionsQuestion (Id,Options) values ('LTPT39',N'a. ("apple", "banana", "cherry") b. {"apple", "banana", "cherry"} c. {"name": "apple", "color": "green"} d. ["apple", "banana", "cherry"]')
insert into OptionsQuestion (Id,Options) values ('LTPT40',N'a. ("apple", "banana", "cherry") b. {"apple", "banana", "cherry"} c. {"name": "apple", "color": "green"} d. ["apple", "banana", "cherry"]')
--------------------------
insert into OptionsQuestion (Id,Options) values ('MNM1',N'a. File System b. Services c. Kernel d. Shell')
insert into OptionsQuestion (Id,Options) values ('MNM2',N'a. Số chẵn là phiên bản ổn định b. Số lẻ là phiên bản thử nghiệm c. Không quan trọng d. Câu a và b đúng')
insert into OptionsQuestion (Id,Options) values ('MNM3',N'a. Workstation b. Server c. Workstation and Server d. Tất cả các đáp án đều sai')
insert into OptionsQuestion (Id,Options) values ('MNM4',N'a. Static b. Microkernel c. Distributed d. Monolithic')
insert into OptionsQuestion (Id,Options) values ('MNM6',N'a. lệnh ls b. lệnh df c.lệnh du d. lệnh cp')
insert into OptionsQuestion (Id,Options) values ('MNM9',N'a. Lệnh cdir b. Lệnh cd c. Lệnh mkdir d. Lệnh dir')
insert into OptionsQuestion (Id,Options) values ('MNM12',N'a. cat b. less c.more d. Tất cả đều đúng.')
insert into OptionsQuestion (Id,Options) values ('MNM13',N'a. Phải mount trước b. eject cdrom c. cd /mnt/cdrom d. Không đọc được CD rom')
insert into OptionsQuestion (Id,Options) values ('MNM14',N'a. mount /dev/fd0 b. mount /dev/cdrom c. Không cần làm gì cả, chỉ việc đọc từ ổ a d. Không cần làm gì cả, chỉ việc đọc từ ổ b')
insert into OptionsQuestion (Id,Options) values ('MNM15',N'a. /etc/mtab b. /etc/mount.conf c. /etc/fstab d. /etc/modules.conf')
insert into OptionsQuestion (Id,Options) values ('MNM17',N'a. 077 b. 644 c. 755 d. 711')
insert into OptionsQuestion (Id,Options) values ('MNM19',N'a. Tập tin ẩn b. Thực thi c. Không thấy được với lệnh ls d. Tất cả các đáp án đều sai')
insert into OptionsQuestion (Id,Options) values ('MNM20',N'a. Lệnh adduser b. Lệnh useradd c. lệnh passwd d. Câu a và b đúng')
insert into OptionsQuestion (Id,Options) values ('MNM21',N'a. Chứa profile của người dùng b. Chứa uid,gid, home directory, shell c. Chứa password của người dùng d. Chứa tập shadow của người dùng')
insert into OptionsQuestion (Id,Options) values ('MNM22',N'a. Chứa profile của người dùng b. Chứa uid,gid, home directory, shell c. Chứa password của người dùng d. Chứa login name')
insert into OptionsQuestion (Id,Options) values ('MNM23',N'a. User administrator b. User root c. User admin d. User có UID=0')
insert into OptionsQuestion (Id,Options) values ('MNM24',N'a. Có 7 Run Level b. Có 6 Run Level c. Có 5 Run Level d. Có 4 Run Level')
insert into OptionsQuestion (Id,Options) values ('MNM25',N'a. Level 6 b. Level 0 c. Level 3 d. Level 5')
insert into OptionsQuestion (Id,Options) values ('MNM26',N'a. Level 6 b. Level 0 c. Level 3 d. Level 5')
insert into OptionsQuestion (Id,Options) values ('MNM27',N'a. Level 6 b. Level 0 c. Level 3 d. Level 5')
insert into OptionsQuestion (Id,Options) values ('MNM29',N'a. Level 1 b. Level 3 c. Level 5 d. Không level nào ')
insert into OptionsQuestion (Id,Options) values ('MNM31',N'a. soạn thảo văn bản b. Công cụ lập trình c. Email client d. Tất cả các tính năng trên')
insert into OptionsQuestion (Id,Options) values ('MNM33',N'a. Lệnh ls b. Lệnh ps c. Lệnh cs d. Lệnh ds')
insert into OptionsQuestion (Id,Options) values ('MNM34',N'a. -ef b. -ax c. -axf d. Cả 3 đáp án đều đúng')
insert into OptionsQuestion (Id,Options) values ('MNM37',N'a. PID b. PUID c. PGUID d. GUID')
insert into OptionsQuestion (Id,Options) values ('MNM38',N'a. Properly process ID b. Parent process ID c. Papa Process ID d. Không cái nào đúng')
insert into OptionsQuestion (Id,Options) values ('MNM39',N'a. stop b. kill c. shutdown d. halt')
insert into OptionsQuestion (Id,Options) values ('MNM40',N'a. Không có gì ảnh hưởng b. Tiến trình con sẽ chết theo c. Chương trình sẽ đọc lại file cấu hình d. Sẽ có một tiến trình con mới sinh ra')
----------------------------------
insert into OptionsQuestion (Id,Options) values ('LTJV1',N'a. .java b. .class c. .jav d. .exe')
insert into OptionsQuestion (Id,Options) values ('LTJV2',N'a. 1 b. 2 c. 3 d. 4')
insert into OptionsQuestion (Id,Options) values ('LTJV3',N'a. Là một thành phần của Java platform dùng để đọc mã bytecode trong file .class b. Là chương trình biên dịch của java dùng để biên dịch file nguồn java thành mã bytecode c. Là chương trình chạy cho java d. Tất cả các đáp án đều đúng')
insert into OptionsQuestion (Id,Options) values ('LTJV4',N'a. Microsoft Windows b. Linux c. Sun Solaris OS d. Tất cả các đáp án đều đúng.')
insert into OptionsQuestion (Id,Options) values ('LTJV6',N'a. Giao diện lập trình ứng dụng b. Bộ công cụ giao diện người dùng c. Thư viện tích hợp d. Tất cả các đáp án đều đúng.')
insert into OptionsQuestion (Id,Options) values ('LTJV9',N'a. Là một tên bất kì b. Cần trùng với tên lớp c. Cần trùng với tên lớp, trước hàm tạo thêm ký tự (~) d. Tất cả đều sai')
insert into OptionsQuestion (Id,Options) values ('LTJV12',N'a. class Xedap1 extend Xedap {} b. public classs Xedap1 extend Xedap {} c. class Xedap1 extends Xedap {} d. Tất cả đều sai.')
insert into OptionsQuestion (Id,Options) values ('LTJV13',N'a. class Xedap1 implement Xedap {} b. public class Xedap1 implement Xedap {} c. class Xedap1 implements Xedap {} d. public class Xedap1 extends Xedap {}')
insert into OptionsQuestion (Id,Options) values ('LTJV14',N'a. 2 b. 7 c. 4 d. 9')
insert into OptionsQuestion (Id,Options) values ('LTJV15',N'a. 5 b. 6 c. 7 d. 8')
insert into OptionsQuestion (Id,Options) values ('LTJV17',N'a. Button b. Label c. Checkbox d. Windows')
insert into OptionsQuestion (Id,Options) values ('LTJV19',N'a. FlowLayout b. Borderlayout c. GridLayout d. GridBagLayout')
insert into OptionsQuestion (Id,Options) values ('LTJV20',N'a. final b. dem c. _final d. $final')
insert into OptionsQuestion (Id,Options) values ('LTJV21',N'a. Một lớp trừu tượng không thể chứa phương thức final b. Một lớp final không thể chứa các phương thức trừu tượng c. Cả hai đều đúng d. Cả hai đều sai')
insert into OptionsQuestion (Id,Options) values ('LTJV22',N'a. Một lớp sẽ kế thừa các hàm khởi tạo từ lớp cha b. Trình biên dịch sẽ tự động tạo hàm khởi tạo mặc định nếu lớp không định nghĩa hàm khởi tạo c. Tất cả các hàm khởi tạo có kiểu trả về là void d. Tất cả các câu trên dều sai')
insert into OptionsQuestion (Id,Options) values ('LTJV23',N'a. Lấy một ký tự bất kì trong chuỗi str b. Lấy độ dài chuỗi str c. Lấy ký tự có số chỉ mục n trong chuỗi k d. Không có lệnh này')
insert into OptionsQuestion (Id,Options) values ('LTJV24',N'a. u0000 b. \uFFFF c. 0F d. 0x')
insert into OptionsQuestion (Id,Options) values ('LTJV25',N'a. a = 6 b. a = 7 c. Lỗi dòng 3 d. Lỗi dòng 2')
insert into OptionsQuestion (Id,Options) values ('LTJV26',N'a. a[3] = 3 b. null c. ArrayIndexOutOfBoundsException d. NullPoiterException')
insert into OptionsQuestion (Id,Options) values ('LTJV27',N'a. KQ = 0 b. Lỗi biên dịch c. KQ = Infinity d. KQ = ')
insert into OptionsQuestion (Id,Options) values ('LTJV29',N'a. 0 b. null c. true d. false ')
insert into OptionsQuestion (Id,Options) values ('LTJV31',N'a. int x=13; System.out.println(x); b. int x=13; System.out.println("x"); c. int x=13; System.out.println("13"); d. int x=13; System.out.println(""+x);')
insert into OptionsQuestion (Id,Options) values ('LTJV33',N'a. Thoát khỏi phương thức run với các điều kiện dừng cho thread trong phương thức b. Thread hoàn thành một công việc nào đó c. Gọi phương thức Delete() d. Gọi phương thức Exit()')
insert into OptionsQuestion (Id,Options) values ('LTJV34',N'a. Tên lớp b. Thuộc tính c. Phương thức d. Biến')
insert into OptionsQuestion (Id,Options) values ('LTJV37',N'a. Nếu condition là true thì result = value2, nếu condition là false thì result = value1 b. Nếu condition là true thì result = value1, nếu condition là false thì result = value2 c. Không có lệnh này d. Nếu condition là true thì result sẽ đảo giá trị của value1 và value2')
insert into OptionsQuestion (Id,Options) values ('LTJV38',N'a. ! b. >> c. << d. !!')
insert into OptionsQuestion (Id,Options) values ('LTJV39',N'a. Có thể truy cập các lớp từ trong cùng package b. Có thể truy cập đối tượng từ các lớp trong cùng package và lớp con nằm trong package c. Có thể truy cập đối tượng từ các phương thức khác trong lớp đó d. Có thể truy cập đối tượng từ bất kì vị trí nào của chương trình')
insert into OptionsQuestion (Id,Options) values ('LTJV40',N'a. UTF-8 b. UTF-16 c. UTF-32 d. Tất cả đều sai')
delete from OptionsQuestion

select * from OptionsQuestion where Id =42
delete from OptionsQuestion where Id=42


delete from OptionsQuestion
update OptionsQuestion
set Options = N'a. KQ = 0 b. Lỗi biên dịch c. KQ = Infinity d. KQ = '
where Id = 27

create table Exam(
	Id nvarchar(10) not null,
	TimeName nvarchar(500) not null,
	Semester varchar(100) not null
)

alter table Exam add constraint PK_Exam primary key(Id)
alter table Exam add constraint FK_Exam_TimeExam foreign key (TimeName,Semester) references TimeExam(IdName,TimeName)

insert into Exam (Id,TimeName,Semester) values (214,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (242,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (255,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (310,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (372,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (444,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (575,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (609,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (694,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (830,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (852,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')
insert into Exam (Id,TimeName,Semester) values (858,N'Kì thi kết thúc học phần 2022 - 2023',N'HKI')
insert into Exam (Id,TimeName,Semester) values (941,N'Kì thi kết thúc học phần 2022 - 2023',N'HKII')

delete from DetailExamPaper
delete from ExamPapers
use ManageQuiz
select *
from Exam e, DetailExam d
where e.Id = d.Id

delete from DetailExam
delete from Exam
where Id = '886'

create table DetailExam(
	Id nvarchar(10) not null,
	IdQues varchar(10) not null
)
select * from DetailExam
delete from DetailExam
delete from DetailExamPaper --
alter table DetailExam add constraint PK_IdDetailExam primary key (Id,IdQues)
alter table DetailExam add constraint FK_DetailExam_Exam foreign key (Id) references Exam(Id)
alter table DetailExam add constraint FK_DetailExam_Question foreign key (IdQues) references Questions(Id)
insert into DetailExam (Id,IdQues) values ('310','MNM20')
insert into DetailExam (Id,IdQues) values ('310','MNM21')
insert into DetailExam (Id,IdQues) values ('310','MNM26')
insert into DetailExam (Id,IdQues) values ('310','MNM32')
insert into DetailExam (Id,IdQues) values ('310','MNM38')
insert into DetailExam (Id,IdQues) values ('310','MNM5')
insert into DetailExam (Id,IdQues) values ('310','MNM7')
insert into DetailExam (Id,IdQues) values ('372','LTPT15')
insert into DetailExam (Id,IdQues) values ('372','LTPT17')
insert into DetailExam (Id,IdQues) values ('372','LTPT19')
insert into DetailExam (Id,IdQues) values ('372','LTPT2')
insert into DetailExam (Id,IdQues) values ('372','LTPT21')
insert into DetailExam (Id,IdQues) values ('372','LTPT25')
insert into DetailExam (Id,IdQues) values ('372','LTPT27')
insert into DetailExam (Id,IdQues) values ('372','LTPT3')
insert into DetailExam (Id,IdQues) values ('372','LTPT36')
insert into DetailExam (Id,IdQues) values ('372','LTPT38')
insert into DetailExam (Id,IdQues) values ('444','LTJV1')
insert into DetailExam (Id,IdQues) values ('444','LTJV10')
insert into DetailExam (Id,IdQues) values ('444','LTJV11')
insert into DetailExam (Id,IdQues) values ('444','LTJV13')
insert into DetailExam (Id,IdQues) values ('444','LTJV15')
insert into DetailExam (Id,IdQues) values ('444','LTJV22')
insert into DetailExam (Id,IdQues) values ('444','LTJV24')
insert into DetailExam (Id,IdQues) values ('444','LTJV26')
insert into DetailExam (Id,IdQues) values ('444','LTJV31')
insert into DetailExam (Id,IdQues) values ('444','LTJV32')
insert into DetailExam (Id,IdQues) values ('575','MNM14')
insert into DetailExam (Id,IdQues) values ('575','MNM15')
insert into DetailExam (Id,IdQues) values ('575','MNM17')
insert into DetailExam (Id,IdQues) values ('575','MNM26')
insert into DetailExam (Id,IdQues) values ('575','MNM28')
insert into DetailExam (Id,IdQues) values ('575','MNM29')
insert into DetailExam (Id,IdQues) values ('575','MNM32')
insert into DetailExam (Id,IdQues) values ('575','MNM34')
insert into DetailExam (Id,IdQues) values ('575','MNM4')
insert into DetailExam (Id,IdQues) values ('575','MNM7')
insert into DetailExam (Id,IdQues) values ('609','LTJV10')
insert into DetailExam (Id,IdQues) values ('609','LTJV14')
insert into DetailExam (Id,IdQues) values ('609','LTJV18')
insert into DetailExam (Id,IdQues) values ('609','LTJV19')
insert into DetailExam (Id,IdQues) values ('609','LTJV22')
insert into DetailExam (Id,IdQues) values ('609','LTJV28')
insert into DetailExam (Id,IdQues) values ('609','LTJV3')
insert into DetailExam (Id,IdQues) values ('609','LTJV31')
insert into DetailExam (Id,IdQues) values ('609','LTJV39')
insert into DetailExam (Id,IdQues) values ('609','LTJV4')
insert into DetailExam (Id,IdQues) values ('694','MNM13')
insert into DetailExam (Id,IdQues) values ('694','MNM15')
insert into DetailExam (Id,IdQues) values ('694','MNM17')
insert into DetailExam (Id,IdQues) values ('694','MNM27')
insert into DetailExam (Id,IdQues) values ('694','MNM30')
insert into DetailExam (Id,IdQues) values ('694','MNM34')
insert into DetailExam (Id,IdQues) values ('694','MNM4')
insert into DetailExam (Id,IdQues) values ('694','MNM40')
insert into DetailExam (Id,IdQues) values ('694','MNM7')
insert into DetailExam (Id,IdQues) values ('694','MNM8')
insert into DetailExam (Id,IdQues) values ('830','MNM12')
insert into DetailExam (Id,IdQues) values ('830','MNM15')
insert into DetailExam (Id,IdQues) values ('830','MNM18')
insert into DetailExam (Id,IdQues) values ('830','MNM23')
insert into DetailExam (Id,IdQues) values ('830','MNM26')
insert into DetailExam (Id,IdQues) values ('830','MNM3')
insert into DetailExam (Id,IdQues) values ('830','MNM30')
insert into DetailExam (Id,IdQues) values ('830','MNM31')
insert into DetailExam (Id,IdQues) values ('830','MNM36')
insert into DetailExam (Id,IdQues) values ('830','MNM7')
insert into DetailExam (Id,IdQues) values ('852','LTPT11')
insert into DetailExam (Id,IdQues) values ('852','LTPT13')
insert into DetailExam (Id,IdQues) values ('852','LTPT14')
insert into DetailExam (Id,IdQues) values ('852','LTPT20')
insert into DetailExam (Id,IdQues) values ('852','LTPT22')
insert into DetailExam (Id,IdQues) values ('852','LTPT29')
insert into DetailExam (Id,IdQues) values ('852','LTPT3')
insert into DetailExam (Id,IdQues) values ('852','LTPT33')
insert into DetailExam (Id,IdQues) values ('852','LTPT39')
insert into DetailExam (Id,IdQues) values ('852','LTPT9')
insert into DetailExam (Id,IdQues) values ('858','LTJV11')
insert into DetailExam (Id,IdQues) values ('858','LTJV13')
insert into DetailExam (Id,IdQues) values ('858','LTJV14')
insert into DetailExam (Id,IdQues) values ('858','LTJV17')
insert into DetailExam (Id,IdQues) values ('858','LTJV27')
insert into DetailExam (Id,IdQues) values ('858','LTJV30')
insert into DetailExam (Id,IdQues) values ('858','LTJV35')
insert into DetailExam (Id,IdQues) values ('858','LTJV36')
insert into DetailExam (Id,IdQues) values ('858','LTJV4')
insert into DetailExam (Id,IdQues) values ('858','LTJV8')
insert into DetailExam (Id,IdQues) values ('941','LTJV10')
insert into DetailExam (Id,IdQues) values ('941','LTJV12')
insert into DetailExam (Id,IdQues) values ('941','LTJV17')
insert into DetailExam (Id,IdQues) values ('941','LTJV2')
insert into DetailExam (Id,IdQues) values ('941','LTJV22')
insert into DetailExam (Id,IdQues) values ('941','LTJV23')
insert into DetailExam (Id,IdQues) values ('941','LTJV34')
insert into DetailExam (Id,IdQues) values ('941','LTJV36')
insert into DetailExam (Id,IdQues) values ('941','LTJV4')
insert into DetailExam (Id,IdQues) values ('941','LTJV7')

select distinct e.Id
from DetailExam de, Questions qs, Exam e, Subjects sb
where de.Id = e.Id and qs.Id = de.IdQues and qs.IdSubject = sb.Id and sb.Id='LTJV'

select * 
from Exam e, Subjects s, DetailExam d
where e.Id = d.Id and d.IdQues 

select *
from DetailExam d

<<<<<<< HEAD
select * from DetailExam where Id = '29'
=======

>>>>>>> 8da57441668ab3ef1ad7f3f1a5af2db212caca59
delete from DetailExam
where Id = '787'
delete from Exam 
where Id='787'

create table TimeExam(
	IdName nvarchar(500) not null,
	TimeName varchar(100) not null,
	TimeTest int
)

alter table TimeExam add constraint PK_TimeExam primary key (IdName, TimeName)

insert into TimeExam (IdName,TimeName,TimeTest) values (N'Kì thi kết thúc học phần 2022 - 2023','HKII', 60)
 insert into TimeExam (IdName,TimeName,TimeTest) values (N'Kì thi kết thúc học phần 2022 - 2023','HKI', 60)
 use ManageQuiz
select distinct  e.Id, s.NameSubject , e.Semester
from Exam e, DetailExam d,Questions q,Subjects s
where e.Id = d.Id and d.IdQues = q.Id and q.IdSubject = s.Id

create table ExamPapers(
	IdStudent char(50) not null,
	IdExam nvarchar(10) not null,
	Id nvarchar(10) not null,
	NumberRight int,
	NumberWrong int,
	Mark float
)

select * from Exam

alter table ExamPapers add constraint PK_ExamPapers primary key (Id)
alter table ExamPapers add constraint FK_ExamPapers_Exam foreign key (IdExam) references Exam(Id)
alter table ExamPapers add constraint FK_ExamPapers_Students foreign key (IdStudent) references Students (Id)
select * from ExamPapers
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','255','878',10,0,10)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','444','827',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','694','218',9,1,9)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','242','629',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','858','471',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','694','635',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','242','991',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','858','641',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','694','939',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','255','597',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','444','917',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','694','648',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','372','395',4,6,4)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','444','544',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','694','723',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','372','647',10,0,0)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','858','398',9,1,0)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','694','326',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','372','844',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','858','254',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','694','399',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','372','865',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','858','990',4,6,4)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','694','267',3,7,3)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','372','646',10,0,10)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','858','123',9,1,9)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','694','986',8,2,8)

delete from ExamPapers

select * from ExamPapers where IdStudent = 891
update ExamPapers
set IdExam = '372'
where IdExam = '214' and IdStudent ='891' and Id = 844

insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','214','727',10,0,10)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','941','824',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('234','310','999',9,1,9)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','214','712',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','852','511',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('345','310','271',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','214','593',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','310','665',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('456','852','383',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','214','858',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','310','699',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('567','852','757',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','214','353',4,6,4)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','310','266',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('678','852','284',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','214','329',10,0,0)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','310','344',9,1,0)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('789','852','122',8,2,8)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','214','877',7,3,7)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','310','741',6,4,6)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('891','214','495',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','372','485',5,5,5)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','310','942',4,6,4)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('628','852','358',3,7,3)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','214','879',10,0,10)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','941','738',9,1,9)
insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('618','310','336',8,2,8)


select distinct IdExam
from ExamPapers e, DetailExamPaper d, Questions q, Subjects s, TimeExam t, Exam ex
where e.IdStudent = 234 and e.Id = d.Id and q.IdSubject = s.Id and d.IdQues = q.Id and s.Id = 'LTJV' and e.IdExam = ex.Id and ex.Semester = t.TimeName and t.TimeName = 'HKI'

select * from DetailExamPaper where Id = 740
select * from ExamPapers where IdStudent = 123



update ExamPapers
set IdExam = '214'
where IdExam = '372'

select IdExam
from ExamPapers e, Exam ex, DetailExam d, Questions q, Subjects s
where IdStudent = 234 and e.IdExam = ex.Id and ex.Semester = 'HKII' and ex.Id = d.Id and q.IdSubject = s.Id and s.NameSubject = 'LTJV'

select distinct e.Id 
from Exam e, DetailExam d, Questions q, Subjects s
where e.Id = d.Id and d.IdQues = q.Id and q.IdSubject = s.Id and s.Id = 'MNM' and e.Semester = 'HKII'

select * from Exam
delete Exam where Id=85

delete DetailExam where Id = 779
delete Exam where Id = 779

select Id from Exam where Semester = 'HKII'
select * from DetailExam
select * from ExamPapers where IdStudent = '123'
delete from DetailExamPaPer where Id = 452
select * from DetailExamPaper
delete from ExamPapers where Id = 452
delete from ExamPapers
where Id = 383

select distinct e.Id, s.NameSubject
from Exam e, DetailExam de, Subjects s, Questions q
where Semester = 'HKII' and e.Id = de.Id and de.IdQues = q.Id and q.IdSubject = s.Id 
 
select distinct AVG(ep.Mark)
from ExamPapers ep, Exam e, DetailExam de, Questions q, Subjects s, TimeExam t
where ep.IdExam = e.Id and e.Id = de.Id and de.IdQues = q.Id and s.Id = q.IdSubject and s.Id ='LTJV' and ep.IdStudent = '234' and e.Semester = t.TimeName and t.TimeName = 'HKI'

select AVG(Mark) 
from ExamPapers ep, Exam e, TimeExam t
where IdStudent = '678' and ep.IdExam = e.Id and e.Semester = t.TimeName and t.TimeName = 'HKI'

select distinct ep.Mark 
from ExamPapers ep, Exam e, DetailExam de, Questions q, Subjects s, TimeExam t
where ep.IdExam = e.Id and e.Id = de.Id and de.IdQues = q.Id and s.Id = q.IdSubject and s.Id = 'LTJV'  and ep.IdStudent = '123' and e.Semester = t.TimeName and t.TimeName = 'HKI'

select distinct IdExam
from ExamPapers e, DetailExamPaper d, Questions q, Subjects s, TimeExam t, Exam ex
where e.IdStudent = '123' and e.Id = d.Id and q.IdSubject = s.Id and d.IdQues = q.Id and s.Id ='LTJV' and e.IdExam = ex.Id and ex.Semester = t.TimeName and t.TimeName = 'HKII'


<<<<<<< HEAD


create table DetailExamPaper(
	Id nvarchar(10) not null,
	IdQues varchar(10),
	Answer nvarchar,
)


alter table DetailExamPaper add constraint FK_DetailExamPaper_ExamPapaers foreign key (Id) references ExamPapers(Id)
alter table DetailExamPaper add constraint FK_DetailExamPaper_Questions foreign key (IdQues) references Questions(Id)
=======
select * from DetailExamPaper
select * from ExamPapers
select * from DetailExam
 drop table DetailExamPaper

 drop table ExamPapers

select distinct IdExam
from ExamPapers e, DetailExamPaper d, Questions q, Subjects s
where e.IdStudent = '123' and e.Id = d.Id and q.IdSubject = s.Id and s.Id = 'LTPT'

select distinct AVG(Mark)
from ExamPapers e, DetailExamPaper d, Questions q, Subjects s
where e.IdStudent = '123' and e.Id = d.Id and q.IdSubject = s.Id and s.Id = 'LTPT'

select distinct e.Id, s.NameSubject
from Exam e, DetailExam d, Questions q, Subjects s
where e.Id = d.Id and d.IdQues = q.Id and q.IdSubject = s.Id 

>>>>>>> 8da57441668ab3ef1ad7f3f1a5af2db212caca59
alter table DetailExamPaper drop constraint PK_DetailExamPaper

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ
where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = '4'

select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options
from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ
<<<<<<< HEAD
=======
<<<<<<< HEAD
where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '2'


		select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options 
        from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ 
        where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id
=======
>>>>>>> 8da57441668ab3ef1ad7f3f1a5af2db212caca59
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

