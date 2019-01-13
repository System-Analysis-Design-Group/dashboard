package controllers

import (
	"Account/models"
	"strconv"

	"github.com/astaxie/beego"
)

type PermissionController struct {
	beego.Controller
}

func (c *PermissionController) Get() {
	userid, _ := strconv.ParseInt(c.Ctx.Input.Param(":id"), 10, 64)
	ur := models.GetRole(userid)
	if ur != nil {
		if ur.RoleID == 0 {
			response := PermissionFound{}
			response.PermissionInside.RoleID = 0
			response.PermissionInside.RoleName = "customer"
			tmp := []string{"comment_r", "comment_w", "menu_r", "order_r"}
			response.PermissionInside.Permissions = tmp
			response.Code = 200
			response.Message = "ok"
			c.Data["json"] = &response
		} else if ur.RoleID == 1 {
			tmp := []string{"comment_r", "comment_w", "menu_r", "menu_w", "order_r", "order_w"}
			response := PermissionFound{PermissionInside{1, "owner", tmp}, 200, "ok"}
			c.Data["json"] = &response
		} else {
			tmp := []string{"comment_r", "comment_w", "menu_r", "menu_w", "order_r", "order_w", "user_r", "user_w", "owner_r", "owner_w"}
			response := PermissionFound{PermissionInside{2, "admin", tmp}, 200, "ok"}
			c.Data["json"] = &response
		}
	} else {
		response := Simple{500, "failed"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}
