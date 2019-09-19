# Task-Management-Services

## Task Management Microservice Architecture
<img src="Task Management Microservice Architecture.svg">

## Feature
### Task Service
-	เพิ่มงาน 
-	ติดตามสถานะการทำงาน
-	ลบงาน
-	อัพเดตสถานะการทำงาน
-	แก้ไขงาน
-	เรียกดูงาน

### Email Service 
-	ส่งเมลแจ้งเตือน เมื่อสถานะ Task สำเร็จ

### Member Service
-	เก็บข้อมูลผู้ใช่
-	ดึงข้อมูลผู้ใช้
-	ลบข้อมูลผู้ใช้
-	แก้ไขข้อมูลผู้ใช้

### Validate Working Time Service
-	ตรวจสอบชั่วโมงการทำงานของผู้ใช้

### Topic Service
-	แสดงความคิดเห็นได้
-	ลบความคิดเห็น
-	แก้ไขความคิดเห็น

## Entity model
### Task
-	Task name 
-	Status
-	Assign member
-	ชั่วโมงที่จะใช้ในการทำงานนี้
-	Tag
-	Deadline
-	Department
-	*subtask

### Member
-	Name
-	Surname
-	Role
-	Email
-	ชั่วโมงการทำงาน (นับจากที่รับงานต่อวัน)
-	Assign ชั่วโมงงานที่สามารถทำได้ต่อวัน
### Topic
-	ความคิดเห็น
-	ชื่อผู้แสดงความคิดเห็น


## API
### API GATEWAY

```json
{
  "tasks": [],
  "emails": {},
  "members": [],
  "validate":{}
}
```


### Task Service

```json
{
  "task": "String",
  "status": true,
}
```

### Member Service

```json
{
  "firstName": "String",
  "lastName": "String",
  "role": "String",
  "email":"String"
}
```
### Email Service

```json
{
  "notify":{}
}
```

### Validation Service

```json
{
  "isValidate":true
}
```
