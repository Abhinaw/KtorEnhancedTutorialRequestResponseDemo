# KtorEnhancedTutorialRequestResponseDemo

Here you can see live request and response that you are making through the postman . you can perform add,delete,update in the code below is the working example that i performed:

GET:

http://localhost:8081/todos
[
    {
        "id": 2,
        "title": "Record Video",
        "done": true
    },
    {
        "id": 3,
        "title": "Record Video #4",
        "done": true
    },
    {
        "id": 4,
        "title": "Record Video",
        "done": true
    },
    {
        "id": 5,
        "title": "Record Video",
        "done": true
    },
    {
        "id": 6,
        "title": "Record Video",
        "done": true
    },
    {
        "id": 7,
        "title": "Record Video",
        "done": true
    },
    {
        "id": 8,
        "title": "Record Video",
        "done": true
    }
]

POST:
http://localhost:8081/todos
request:
{
    "title": "Record Video",
    "done": true
}
response:
{
    "id": 8,
    "title": "Record Video",
    "done": true
}

PUT:
http://localhost:8081/todos/3

request:
{
    "title": "Record Video #4",
    "done": true
}

response:
200OK

DELETE:

http://localhost:8081/todos/1

request:
{
    "title": "Record Video",
    "done": true
}
response:
200OK
