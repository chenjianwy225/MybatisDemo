package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.service.user.IUserService;

public class BaseController {

	@Autowired
	protected IUserService userService;
}