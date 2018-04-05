package com.qbao.store.controller.v2;

import com.qbao.store.controller.IBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 游客绑定
 *
 * @author hunsy
 */
@Controller
@RequestMapping(value = "v2/tourist")
public class V2TouristController extends IBaseController {


    /**
     * 绑定账号
     *
     * @return
     */
    @RequestMapping("/bind")
    public void bind(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/tourist/bind?needCard=true").forward(request, response);
    }

}
