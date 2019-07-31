package com.demo.controller.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.common.MD5Utils;
import com.demo.common.ParamUtils;
import com.demo.common.ResponseUtils;
import com.demo.common.UUIDUtils;
import com.demo.controller.BaseController;
import com.demo.model.user.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@RequestMapping(value = "single", method = RequestMethod.GET)
	@ResponseBody
	public Object single(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String userId = ParamUtils.getStringDefault(request, "userId", "");

			User user = userService.findById(userId);
			return ResponseUtils.writeSuccess(user, true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.writeFail();
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		try {
			int pageNo = ParamUtils.getIntegerDefault(request, "pageNo", 1);
			int pageSize = ParamUtils.getIntegerDefault(request, "pageSize", 1);

			PageHelper.startPage(pageNo, pageSize);
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("sex", "1");
			List<User> list = userService.findAll(params);
			PageInfo<User> pageInfo = new PageInfo<User>(list);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			return ResponseUtils.writeSuccess(map, true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.writeFail();
		}
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = new User();
			user.setKeyId(UUIDUtils.getUUID());
			user.setUserName("henry");
			user.setUserPassword(MD5Utils.encoderToMD5("123456"));
			user.setMobile("13984039128");
			user.setNickName("亨利");
			user.setCreateDate(Calendar.getInstance().getTime());

			userService.save(user);
			return ResponseUtils.writeSuccess("添加成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.writeFail();
		}
	}
}