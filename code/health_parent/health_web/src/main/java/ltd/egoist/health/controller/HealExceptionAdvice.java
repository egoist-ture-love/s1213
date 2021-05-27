package ltd.egoist.health.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.Result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname HealExceptionAdvice
 * @Description TODO
 * @Date 2021/5/22 11:22
 * @Created by Lenovo
 */
@RestControllerAdvice
public class HealExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(HealthException.class);
    /**
     * 自定义异常
     */
    @ExceptionHandler(HealthException.class)
    public Result handleHealthException(HealthException he){
        return new Result(false,he.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error("出现异常");
        return new Result(false,"操作异常,联系管理员");
    }
}
