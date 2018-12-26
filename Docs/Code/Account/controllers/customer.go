package controllers

import (
	"Account/models"
	"log"
	"strconv"

	"github.com/astaxie/beego"
)

type CustomerController struct {
	beego.Controller
}

func (c *CustomerController) Get() {
	customerId := c.Ctx.Input.Param(":id")
	id, err0 := strconv.ParseInt(customerId, 10, 64)
	if err0 != nil {
		log.Println("用户输入ID不合法")
	} else {
		user := models.GetUserInfo(id)
		if user != nil {
			//成功查询
			log.Println(user)
			response := Detail{Obj{user.Username, user.UserID, "default"}, 200, "ok"}
			c.Data["json"] = &response
		} else {
			response := Simple{500, "failed"}
			c.Data["json"] = &response
		}
	}
	c.ServeJSON()
}

func (c *CustomerController) Delete() {
	customerId := c.Ctx.Input.Param(":id")
	id, err0 := strconv.ParseInt(customerId, 10, 64)
	if err0 != nil {
		log.Println("用户输入ID不合法")
	} else {
		isSuccess := models.DeleteUser(id)
		if isSuccess == true {
			response := Simple{200, "ok"}
			c.Data["json"] = &response
		} else {
			response := Simple{500, "failed"}
			c.Data["json"] = &response
		}
	}
	c.ServeJSON()
}
