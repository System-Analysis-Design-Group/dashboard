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

// Post登录
func (c *SigninController) Post() {
	var user models.User
	//解析body里面的json
	json.Unmarshal(c.Ctx.Input.RequestBody, &user)
	//验证身份信息
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
	} else {
		response := Simple{500, "用户名或密码错误"}
		c.Data["json"] = &response
	}

	c.ServeJSON()
}
