package controllers

import (
	"Account/models"
	"encoding/json"
	"time"

	"github.com/astaxie/beego"
	"github.com/dgrijalva/jwt-go"
)

type SigninController struct {
	beego.Controller
}

func (c *SigninController) Post() {
	var user models.User
	json.Unmarshal(c.Ctx.Input.RequestBody, &user)
	id, isOK := models.ValidateUser(user)
	if isOK == true {
		ur := models.GetRole(id)
		var tmp string
		if ur.RoleID == 0 {
			tmp = "customer"
		} else if ur.RoleID == 1 {
			tmp = "owner"
		} else {
			tmp = "admin"
		}
		claims := make(jwt.MapClaims)
		claims["username"] = user.Username
		if tmp == "admin" {
			claims["admin"] = "true"
		} else {
			claims["admin"] = "false"
		}
		claims["exp"] = time.Now().Add(time.Hour * 480).Unix()
		token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
		tokenString, err := token.SignedString([]byte("mykey"))

		if err != nil {
			beego.Error("jwt.SignedString:", err)
		}
		response := Detail1{Obj{user.Username, id, tmp}, 200, "欢迎您" + user.Username, tokenString}
		c.Data["json"] = &response
		//log.Println("登录成功")
	} else {
		response := Simple{500, "用户名或密码错误"}
		c.Data["json"] = &response
		//log.Println("登录失败")
	}

	c.ServeJSON()
}
