
#### Mongodb 创建数据库:

1.MongoDB开启用户认证:
   
   `mongod --auth`
   
   并创建了管理员用户admin:
   ```
   > use admin
   switched to db admin
   > db.createUser({user:"admin",pwd:"123456",roles:["root"]})
   Successfully added user: { "user" : "admin", "roles" : [ "root" ] }
   
   ```
   <br/>
   <br/>
   
  2.登录mongodb的admin数据库:
     
   `mongo admin`
      
   `db.auth("admin","123456")`
   
  或:`mongo 127.0.0.1:27017/admin -u admin -p 123456`
  
  <br/>
  <br/>
  
  3.创建一个测试连接的数据库命名为db_webflux_demo,并添加管理员用户到数据库:
  
  `use db_webflux_demo`
  
  `db.createUser( { user: "admin",  pwd: "123456",  roles: [ { role: "root", db: "admin" } ] } )`
  
  <br>
  <br/>
  
  4.配置好项目spring-webflux-mongodb-reactive-demo的application.yml文件的连接配置.
  
  <br/>
  <br/>
  
  (提示: Idea可以安装Mongodb Plugin来查看管理数据库, 在Docker中装MongoDB比较方便)