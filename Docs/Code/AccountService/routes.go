package main

import (
	"net/http"
)

type Route struct {
	Name        string
	Method      string
	Pattern     string
	HandlerFunc http.HandlerFunc
}

type Routes []Route

var routes = Routes{
	Route{ //欢迎界面
		"Index",
		"GET",
		"/",
		Index,
	},
	Route{ //查看所有用户信息
		"Alluser",
		"GET",
		"/Accounts",
		Alluser,
	},
	Route{ //注册
		"userCreat",
		"POST",
		"/Accounts/Signup",
		UserCreate,
	},
	Route{ //登录
		"Signin",
		"POST",
		"/Accounts/Signin",
		Signin,
	},
	Route{ //获取某用户账号信息
		"UserGet",
		"GET",
		"/Accounts/Customer/{user_id}",
		UserGet,
	},
	Route{ //删除某用户账号信息
		"userDelete",
		"DELETE",
		"/Accounts/Customer/{user_id}",
		UserDelete,
	},
	Route{ //查看某个用户的权限
		"permissionGet",
		"GET",
		"/Accounts/Permission/{user_id}",
		PermissionGet,
	},
	Route{ //新增用户权限
		"permissionPost",
		"POST",
		"/Accounts/Permission/{user_id}",
		PermissionPost,
	},
	Route{ //删除某用户的权限
		"permissionDelete",
		"DELETE",
		"/Accounts/Permission/{user_id}",
		PermissionDelete,
	},
}
