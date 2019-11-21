
#### Springboot webflux mongodb reactive使用

1. 创建[Mongodb](mongodb.md)数据库.

2. 启动本应用:
     
```
 -使用http请求工具保存一条数据:
 
 POST: http://localhost:8080/user/save
 Content-Type: application/json
 Body: {
        "name": "pjqdyd",
        "age": 18
       }
       
 返回的响应:
 {
     "id": "5dd656228b6d8c6025d023e3",
     "name": "pjqdyd",
     "age": 18
 }
 
 -查询接口:
 GET: http://localhost:8080/user/all
 GET: http://localhost:8080/user/stream/all

 -删除用户:
  DELETE: http://localhost:8080/user/delete/{userId}

 -更新接口:
  PUT: Http://localhost:8080/user/update/{userId}
  Body: {
            "name": "小张",
            "age": 30
        }

  -

```