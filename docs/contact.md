# Contac Api Spec

## Create Contact
Endpoint : POST /api/contacts
- Request Header :
- X-API-Token (Mandatory)

Request Body :
```json 
{
  "firstname" : "naze",
  "lastname" : "mbul",
  "email" : "mbul@example.com",
  "phone" : "0808080808"
}
```
Response Body(Succsess) :
```json 
{ "data" : {
  "id" : "random-string",
  "firstname": "naze",
  "lastname": "mbul",
  "email": "mbul@example.com",
  "phone": "0808080808"
}
}
```
Response Body(Failed) :
```json
{
  "errors" : "Email format invalid,..."
}
```

## Update Contact
Endpoint : PUT /api/contacts/{idContact}
- Request Header :
- X-API-Token (Mandatory)

Request Body :
```json
{
  "firstname" : "naze",
  "lastname" : "mbul",
  "email" : "mbul@example.com",
  "phone" : "0808080808"
}
```
Response Body(Succsess) :
```json
{
  "id" : "random-string",
  "firstname": "naze",
  "lastname": "mbul",
  "email": "mbul@example.com",
  "phone": "0808080808"
}
```
Response Body(Failed) :

```json
{
  "errors" : "Email format invalid,..." 
}
```
## Get Contact
Endpoint : GET /api/contacs/{idContact}
- Request Header :
- X-API-Token (Mandatory)


Response Body(Succsess) :

```json 
{ "data" : {
  "id" : "random-string",
  "firstname": "naze",
  "lastname": "mbul",
  "email": "mbul@example.com",
  "phone": "0808080808"
}
}
```

Response Body(Failed) :
```json
{
  "errors" : "contact is not found"
}
```


## Search Contact
Endpoint : GET /api/contacts
- Request Header :
- X-API-Token (Mandatory)

Request Param :
-name : String, contact firt name or last name, using like query
-phone : String, contact phone, using like query
-email : String, contact email, using like query
-page : Integer, start from 0. default 0 
-size : Integer, default 10 

Response Body(Succsess) :
```json
{
  "data": [
    {
    "id" : "random-string",
    "firstname": "naze",
    "lastname": "mbul",
    "email": "mbul@example.com",
    "phone": "0808080808"
    
  }
],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body(Failed) :

```json
{
  "errors" : "Unauthorized"
}
```
## Remove Contact
Endpoint : DELETE /api/contact/{idContact}

Response Body(Succsess) :
```json 
{ 
  "data" : "ok"
}
```
Response Body(Failed) :
```json
{
  "errors" : "Contact is not found"
}
```

