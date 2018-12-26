package controllers

import (
	"Account/models"

	"github.com/astaxie/beego"
)

type SignupController struct {
	beego.Controller
}

func (c *SignupController) Get() {
	c.TplName = "signup.html"
}

func (c *SignupController) Post() {
	newUser := models.User{}
	newUser.Username = c.GetString("username")
	newUser.Password = c.GetString("password")

	userID, err := models.AddCustomer(&newUser)
	if err != nil {
		response := Simple{500, "failed"}
		c.Data["json"] = &response
	} else {
		//要在UserRole表里加入一个记录
		models.AddRole(userID, 0)
		response := Detail{Obj{newUser.Username, userID, "default"}, 200, "ok"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}
