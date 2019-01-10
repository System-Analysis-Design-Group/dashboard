package controllers

import (
	"Account/models"
	"encoding/json"
	"log"
	"time"

	"github.com/astaxie/beego"
	"github.com/dgrijalva/jwt-go"
)

type SigninController struct {
	beego.Controller
}

func (c *SigninController) Post() {
	//c.Ctx.WriteString(fmt.Sprint(c.Input()))
	// username := c.GetString("username")
	// password := c.GetString("password")
	gotUser := User{}
	json.Unmarshal(c.Ctx.Input.RequestBody, &gotUser)
	log.Println(gotUser)
	var user models.User
	user.Username = gotUser.Username
	user.Password = gotUser.Password
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
		claims["username"] = gotUser.Username
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
		response := Detail1{Obj{gotUser.Username, id, tmp}, 200, "ok", tokenString}
		c.Data["json"] = &response
		log.Println("登录成功")
	} else {
		response := Simple{500, "failed"}
		c.Data["json"] = &response
		log.Println("登录失败")
	}

	c.ServeJSON()
}
