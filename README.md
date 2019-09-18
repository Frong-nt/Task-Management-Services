# Task-Management-Services

## Task Management Microservice Architecture
<img src="Task Management Microservice Architecture.svg">

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
  "role": "String"
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
