
#### SSE (Server Send Event)的实现代码

```
    //测试SSE, 实现响应式数据(在传统的spring mvc下)
    @GetMapping("/sseSayHi")
    private void sseSayHi(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");

        for (int i = 0; i < 5; i++){
            response.getWriter().write("event:me\n"); //指定事件标识
            response.getWriter().write("data:" + i + "\n");
            response.getWriter().flush();
            Thread.sleep(1000);
        }
    }

    //h5前端代码
    <script type="text/javascript">
     var sse = new EventSource("http://localhost:8080/sseSayHi");
         sse.onmessage = function(e){
             console.log("message:", e.data);
         }
         sse.addEventListener("me", function(e){
             if(e.data == 4){
                 sse.close(); //当data: 4时关闭sse, 否则会自动重连
             }
         });
    </script>    

   ```