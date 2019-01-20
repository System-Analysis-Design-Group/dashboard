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
			ur := models.GetRole(id)
			var tmp string
			if ur.RoleID == 0 {
				tmp = "customer"
			} else if ur.RoleID == 1 {
				tmp = "owner"
			} else {
				tmp = "admin"
			}
			//查找该用户的商店
			response := Detail2{}
			if tmp == "owner" {
				_, store := models.SearchByUser(id)
				response.Obj2.Username = user.Username
				response.Obj2.Userid = user.UserID
				response.Obj2.Rolename = tmp
				response.Code = 200
				response.Message = "ok"
				for _, item := range store {
					response.Obj2.Stores = append(response.Obj2.Stores, item.StoreID)
				}
			} else {
				response = Detail2{
					Obj2{user.Username, user.UserID, tmp, nil},
					200,
					"ok"}
			}
			c.Data["json"] = &response
		} else {
			response := Simple{
				500,
				"failed"}
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
			response := Simple{
				200,
				"ok"}
			c.Data["json"] = &response
		} else {
			response := Simple{
				500,
				"failed"}
			c.Data["json"] = &response
		}
	}
	c.ServeJSON()
}
