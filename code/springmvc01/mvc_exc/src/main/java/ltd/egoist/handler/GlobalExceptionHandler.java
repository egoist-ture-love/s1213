package ltd.egoist.handler;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2021/5/4 16:53
 * @Created by Lenovo
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",e.getMessage());
        e.printStackTrace();
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
