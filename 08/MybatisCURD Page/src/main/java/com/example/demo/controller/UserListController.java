package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2/22/2019 8:56 PM
 */
@Controller
public class UserListController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/listall")
    public String listCategory(Model m, @RequestParam(value="start",defaultValue="0")int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        //1. 在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5。
        //2. 根据start,size进行分页，并且设置id 倒排序
        PageHelper.startPage(start,size,"id desc");
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        List<User> cs = userMapper.queryAll();
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<User> page = new PageInfo<>(cs);
        //5. 把PageInfo对象扔进model，以供后续显示
        m.addAttribute("page", page);
   //System.out.println(page..is.end.e.isIsLastPage().I.getLastPage().f.isIsFirstPage().getFirstPage().getLastPage().isIsFirstPage());
        //6. 跳转到listCategory.jsp
        return "list";
    }
}
