package controllers

import (
	"log"
	"strings"

	"github.com/astaxie/beego"
	jwt "github.com/dgrijalva/jwt-go"
)

var (
	key []byte = []byte("mykey")
)

type LogoutController struct {
	beego.Controller
}

func (c *LogoutController) Get() {
	authString := c.Ctx.Input.Header("Authorization")
	kv := strings.Split(authString, " ")
	log.Println("header中获得的：", authString)
	log.Println("len(kv):", len(kv))
	if len(kv) != 2 || kv[0] != "Bearer" {
		response := Simple{500, "no token found"}
		c.Data["json"] = &response
	} else {
		tokenString := kv[1]
		token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
			return []byte("mykey"), nil
		})
		if err != nil {
			beego.Error("Parse token:", err)
			if ve, ok := err.(*jwt.ValidationError); ok {
				if ve.Errors&jwt.ValidationErrorMalformed != 0 {
					// That's not even a token
					response := Simple{500, "That's not even a token"}
					c.Data["json"] = &response
				} else if ve.Errors&(jwt.ValidationErrorExpired|jwt.ValidationErrorNotValidYet) != 0 {
					// Token is either expired or not active yet
					response := Simple{500, "That's not even a token"}
					c.Data["json"] = &response
				} else {
					// Couldn't handle this token
					response := Simple{500, "Couldn't handle this token"}
					c.Data["json"] = &response
				}

			} else {
				// Couldn't handle this token
				response := Simple{500, "Couldn't handle this token"}
				c.Data["json"] = &response
			}

		}
		// if token.Valid == false {
		// 	log.Println("ccccccccccccccccccccccccc")
		// 	beego.Error("Token invalid:", tokenString)
		// 	response := Simple{500, "Couldn't handle this token"}
		// 	c.Data["json"] = &response
		// }

		if err == nil && token.Valid {
			beego.Debug("Token:", token)
			response := Simple{200, "ok"}
			c.Data["json"] = &response
		}
	}
	c.ServeJSON()
}

// func CheckToken(token string) bool {
// 	_, err := jwt.Parse(token, func(*jwt.Token) (interface{}, error) {
// 		return key, nil
// 	})
// 	if err != nil {
// 		fmt.Println("parase with claims failed.", err)
// 		return false
// 	}
// 	return true
// }
