# user api spec
## Register user
- endpoint : POST /api/users
- Request body : 
```json
{
  "username":"naze",
  "password" : "jaya",
  "name" : "furnaze"
}
```

Respons Body (Success) :
```json
{
  "data" : "ok"
}
```
Respons Body (Failed) : 
```json
{
  "erorrs" : "Username must not blank,??  "
}
```
# login user 
- endpoint : POST /api/auth/login

- Request body :
```json
{
  "username":"naze",
  "password" : "jaya"
}
```

Respons Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expireAt" : 234234234
  }
}
```
Respons Body (Failed, 401) :
```json
{
  "erorrs" : "Username or password wrong "
}
```
# get user

- endpoint : GET /api/users/current
Request Header :
- X-API-Token (Mandatory)

Respons Body (Success) :
```json
{
  "data" : {
    "username" : "naze",
    "name" : "furnaze"
  }
}
```
Respons Body (Failed, 401) :
```json
{
  "erorrs" : "Unauthorized"
}
```

# update user

- endpoint : PATCH /api/users/current
- X-API-Token (Mandatory)
Request body :
```json
{
    "name" : "furnaze", //only for updatename
  "password" : "new password" //only for update password
  }

```


Respons Body (Success) :
```json
{
  "data" : {
    "username" : "naze",
    "name" : "furnaze"
  }
}
```
Respons Body (Failed, 401) :
```json
{
  "erorrs" : "Unauthorized"
}
```
# logout user 
- endpoint : DELETE /api/auth/logout
- Request Header : 
- X-API-Token (Mandatory) 
```json
{
  "data" : "ok"
}
```