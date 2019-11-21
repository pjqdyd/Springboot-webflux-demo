package com.pjqdyd.controller.advide;

import com.pjqdyd.exception.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**   
 * @Description:  [参数校验异常切面]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@ControllerAdvice
public class ParamsValidAdvice {

    /**
     * 异常捕获后的响应
     * @param e
     * @return
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleValidException(WebExchangeBindException e){
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    /**
     * 把异常转换为字符串
     * @param ex
     * @return
     */
    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
        //reduce是将字符串集合数组, 转换为字符串, 并在每个元素间换行
    }

    /**
     * 名称校验异常捕获后的响应
     * @param e
     * @return
     */
    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleNameException(CheckException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
