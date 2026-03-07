# Addres api spec

## Create Address
Endpoint : POST /api/contacts/{idContact}/addresses

Request Header :
- X-API-Token (Mandatory)

Request Body :
```json
{
  "street" : "Jalan apa",
  "city" : "Kota",
  "province" : "Provinsi",
  "country" : "Negara",
  "postalcode" : "55555"
}
```
Responses Body (Success):
```json
{
  "data": {
    "id" : "randomstring",
    "street" : "Jalan apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalcode" : "55555"
  }
}
```
Responses Body (Failed):
```json
{
  "errors" : "Contacts is not founds"
}
```

## Update Addres
Endpoint : PUT /api/contact/{idContact}/addresses/{idAddress}

Request Header :
- X-API-Token (Mandatory)
Request Body :
```json
{
  "street" : "Jalan apa",
  "city" : "Kota",
  "province" : "Provinsi",
  "country" : "Negara",
  "postalcode" : "55555"
}
```
Responses Body (Success):
```json
{
  "data": {
    "id" : "randomstring",
    "street" : "Jalan apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalcode" : "55555"
  }
}
```
Responses Body (Failed):
```json
{
  "errors" : "Addresses is not founds"
}
```

## Get Addres
Endpoint : GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-Token (Mandatory)

Responses Body (Success):
```json
{
  "data": {
    "id" : "randomstring",
    "street" : "Jalan apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalcode" : "55555"
  }
}
```
Responses Body (Failed):
```json
{
  "errors" : "Addresses is not founds"
}
```

## Remove Addres
Endpoint : DELETE /api/contact/{idContacts}/addresses{idAddress}

Request Header :
- X-API-Token (Mandatory)

Responses Body (Success):
```json
{
  "data" : "ok"
}
```
Responses Body (Failed):
```json
{
  "errors" : "Addresses is not founds"
}
```

## LIST Addres
Endpoint : GET /api/contact/{idContact}/addresses

Request Header :
- X-API-Token (Mandatory)

Responses Body (Success):
```json
{
  "data": [
    {
    "id" : "randomstring",
    "street" : "Jalan apa",
    "city" : "Kota",
    "province" : "Provinsi",
    "country" : "Negara",
    "postalcode" : "55555"
    }
  ]
}
```
Responses Body (Failed):
```json
{
  "errors" : "Contact is not found"
}
```