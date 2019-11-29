# Task-Management-Services
## Presentation
https://docs.google.com/presentation/d/1PUROzBlXKMql83RF7t3B_xGAvfOf9IDjSlwGehSLm5k/edit?usp=sharing
## Task Management Microservice Architecture
<img src="Task Management Microservice Architecture.jpg" width ="85%">

## Feature
### Task Service
-	เพิ่มงาน 
-	ติดตามสถานะการทำงาน
-	ลบงาน
-	อัพเดตสถานะการทำงาน
-	แก้ไขงาน
-	เรียกดูงาน

### Email Service 
-	ส่งเมลแจ้งเตือน เมื่อมีการเปลี่ยนสถานะของ Task

### Member Service
-	เก็บข้อมูลผู้ใช่
-	ดึงข้อมูลผู้ใช้
-	ลบข้อมูลผู้ใช้
-	แก้ไขข้อมูลผู้ใช้

### Validate Working Time Service
-	ตรวจสอบชั่วโมงการทำงานของผู้ใช้ มีสองเคสในการตรวจสอบ เคสแรกคือตรวจสอบตามกฎหมายแรงงาน (ไม่เกิน 8 ชั่วโมงต่อวัน) และตรวจสอบตามที่ผู้ใช้เป็นคนกำหนดลิมิตการทำงานของตัวเอง

### Topic Service
-	แสดงความคิดเห็นได้
-	ลบความคิดเห็น
-	แก้ไขความคิดเห็น


### Endpoint
https://azure-task-manager-sop-azure-gateway.azuremicroservices.io/{SERVICE-NAME}

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
- POST /email/send ==> send notification email
- body
```json
{
  "recipientName": ["String"], 
  "taskName": "String", 
  "ListName": "String"
}
```

### Validation Service
- POST /validateTime ==> validate time that user select task per day
- body
```json
{
   "time": 0,
   "individualTime": 0
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
            "msg":"sorry but i dont knowssssss",
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
            "msg":"sorry but i dont knowssssss",
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
            "msg":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
```

- PUT /topic/{issueID}/{id}      => update specific comment
 ```json
  { 
            "msg":"sorry but i dont knowssssss",
            "by":"somebody",
            "dateTime":"2019-11-23T04:22:57.339572 "
         }
```
- DELETE /topic/{issueID}/{id}     => delete secific comment
- GET /topic/{issueID}/{id}     => return secific comment
 ```json
  { 
            "msg":"sorry but i dont knowssssss",
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
