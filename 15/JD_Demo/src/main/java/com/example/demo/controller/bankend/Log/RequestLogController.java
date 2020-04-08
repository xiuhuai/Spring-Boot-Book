package com.example.demo.controller.bankend.Log;

import com.example.demo.entity.sys.RequestLog;
import com.example.demo.repository.sys.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo.module.Listener.SessionListener.onlineNum;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:56 PM
 * @description
 */
@RequestMapping("admin")
@Controller
public class RequestLogController {
    @Autowired
    private RequestLogRepository requestLogRepository;

    @RequestMapping("/requestlog")
    public ModelAndView userlist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        start = start < 0 ? 0 : start;
// Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "categoryid","desc");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
// Pageable pageable = new PageRequest(start, limit, sort);
        Pageable pageable = PageRequest.of(start, limit, sort);

        Page<RequestLog> page = requestLogRepository.findAll(pageable);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getId());
        System.out.println(onlineNum);

        ModelAndView mav = new ModelAndView("systom/log/RequestLog");
        mav.addObject("page", page);
        return mav;
    }
}
