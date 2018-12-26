package controllers

import (
	"Account/models"
	"log"

	"github.com/astaxie/beego"
)

type SigninController struct {
	beego.Controller
}

func (c *SigninController) Get() {
	// sess :=c.StartSession()
	// username :=sess.Get("username")
	// if username ==nil || username=""{
	// 	//已登录状态
	// }else{
	// 	//未登陆
	// }
	c.TplName = "login.html"
}

func (c *SigninController) Post() {
	//c.Ctx.WriteString(fmt.Sprint(c.Input()))
	username := c.GetString("username")
	password := c.GetString("password")
	var user models.User
	user.Username = username
	user.Password = password
	id, isOK := models.ValidateUser(user)
	if isOK == true {
		response := Detail{Obj{username, id, "default"}, 200, "ok"}
		c.Data["json"] = &response
		log.Println("登录成功")
	} else {
		response := Simple{500, "failed"}
		c.Data["json"] = &response
		log.Println("登录失败")
	}

	c.ServeJSON()
}
