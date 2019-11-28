package com.example.demo.controller.bankend.Log.LoginLog;

import com.example.demo.entity.sys.MemberLoginLog;
import com.example.demo.repository.sys.MemberLoginLogRepository;
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
import javax.servlet.http.HttpSession;

import static com.example.demo.module.Listener.SessionListener.onlineNum;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:56 PM
 * @description
 */
@RequestMapping("admin")
@Controller
public class MemberLoginLogController {
    @Autowired
    private MemberLoginLogRepository memberLoginLogRepository;
    private HttpSession session;
    @RequestMapping("/memberloginlog")
    public ModelAndView userlist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        start = start < 0 ? 0 : start;
// Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "categoryid","desc");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
// Pageable pageable = new PageRequest(start, limit, sort);
        Pageable pageable = PageRequest.of(start, limit,sort);

        Page<MemberLoginLog> page = memberLoginLogRepository.findAll(pageable);

// System.out.println(page.getNumber());
// System.out.println(page.getNumberOfElements());
// System.out.println(page.getSize());
// System.out.println(page.getTotalElements());
// System.out.println(page.getTotalPages());
// System.out.println(page.isFirst());
// System.out.println(page.isLast());


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getId());
        System.out.println(onlineNum);

        ModelAndView mav = new ModelAndView("systom/log/LoginLog/MemberLoginLog");
        mav.addObject("page", page);
        return mav;
    }
}
