# Task-Management-Services

## Task Management Microservice Architecture
<img src="Task Management Microservice Architecture.svg" width ="45%">

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
### List Servicr
### Borad Service

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




### Task Service

```json
{
  "task": "String",
  "status": true,
}
```

### Member Service
- GET /member/{firstname} ==> return firstname in database 
```json
{
  "firstName":"String",
  "lastName": "String",
  "role": "String",
  "hours":"String",
  "email":"String"
}
```

- POST /member/update/{id} ==> Update id in database
```json
{
  "firstName":"String",
  "lastName": "String",
  "role": "String",
  "hours":"String",
  "email":"String"
}
```

- POST /member/delete/{id} ==> Delete id in database


- GET /members ==> get all member in database
```json
[
  {
    "firstName":"String",
    "lastName": "String",
    "role": "String",
    "hours":"String",
    "email":"String"
  }
]
```

- POST /member/add ==> Create member in database 
- body
```json
{
  "firstName":"String",
  "lastName": "String",
  "role": "String",
  "hours":"String",
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
### Topic Service
- GET /topic/      => return all topic
```json
[ 
   { 
      "issueID":"1",
      "issueName":"need help pleassss",
      "status":"pending",
      "description":"My task is getiing error",
      "by":"Someonee",
      "dateTime":"2019-11-23T04:21:53.449987",
      "comments":[ 
         { 
            "id": "1",
            "message":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
      ]
   }
]
```
- GET /topic/{issueID}     => return specific topic
```json
{ 
      "issueID":"1",
      "issueName":"need help pleassss",
      "status":"pending",
      "description":"My task is getiing error",
      "by":"Someonee",
      "dateTime":"2019-11-23T04:21:53.449987",
      "comments":[ 
         { 
            "id": "1",
            "message":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
      ]
   }
   ```
- DELETE /topic/{issueID}
- POST /topic/       => insert new topic
```json
 { 
      "issueName":"need help pleassss",
      "status":"pending",
      "description":"My task is getiing error",
      "by":"Someonee",
      "dateTime":"2019-11-23T04:21:53.449987",
      "comments":[]
   }
```
 - PUT /topic/{issueID}    => update specific topic 
 ```json
 { 
      "issueName":"need help pleassss",
      "status":"pending",
      "description":"My task is getiing error",
      "by":"Someonee",
      "dateTime":"2019-11-23T04:21:53.449987",
      "comments":[]
   }
```
- POST /topic/{issueID}/     => insert comment
 ```json
  { 
            "message":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
```

- PUT /topic/{issueID}/{id}      => update specific comment
 ```json
  { 
            "message":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
```
- DELETE /topic/{issueID}/{id}     => delete secific comment
- GET /topic/{issueID}/{id}     => return secific comment
 ```json
  { 
            "message":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
```


## Member group
|   | ชื่อ  | นามสกุล  | GitHub username  | รหัสนักศึกษา  | หน้าที่  |
|---|---|---|---|---|---|
|   | กวิสรา  | บัณเย็น  | zelotype  | 60070002  | Developer  |
|   | คาซูยา | โคมัทซึ  | yakung  | 60070007  | Developer  |
|   |  ณัฐวุฒิ | เตชะศรีบูรพา  | Frong-nt  | 60070025  | Developer  |
|   | ศุภกิตติ์  | รอดทอง  | KurokoChu  | 60070099  | Developer  |
