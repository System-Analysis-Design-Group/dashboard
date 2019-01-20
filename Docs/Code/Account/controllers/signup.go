package controllers

import (
	"Account/models"
	"encoding/json"

	"github.com/astaxie/beego"
)

type SignupController struct {
	beego.Controller
}

func (c *SignupController) Post() {
	//从body里读出信息，放入newUser中
	newUser := models.User{}
	json.Unmarshal(c.Ctx.Input.RequestBody,
		&newUser)
	//判断用户名是否合法
	if newUser.Username == "" || newUser.Password == "" {
		response := Simple{500, "用户名或密码不能为空"}
		c.Data["json"] = &response
	} else {
		//判断此用户名是否已经存在
		isExisted := models.CheckIfUserExist(newUser.Username)
		if isExisted == true {
			response := Simple{
				500,
				"该用户名已存在"}
			c.Data["json"] = &response
		} else {
			userID, err := models.AddCustomer(&newUser)
			if err != nil {
				response := Simple{
					500,
					"failed"}
				c.Data["json"] = &response
			} else {
				//要在UserRole表里加入一个记录
				models.AddRole(userID, 0)
				response := Detail{Obj{
					newUser.Username,
					userID,
					"customer"},
					200,
					"ok"}
				c.Data["json"] = &response
			}
		}
	}
	c.ServeJSON()
}
