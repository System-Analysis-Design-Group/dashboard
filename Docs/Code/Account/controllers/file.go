package controllers

import (
	"log"
	"os"

	"github.com/astaxie/beego"
)

type FileController struct {
	beego.Controller
}

func (c *FileController) Post() {
	//获取上传的文件
	f, _, err := c.GetFile("avator")
	customerId := c.Ctx.Input.Param(":userid")
	log.Println(customerId)
	if err == nil {
		path := "./upload/avator/" + customerId + ".jpg" //文件目录
		f.Close()                                        //关闭上传的文件，不然的话会出现临时文件不能清除的情况
		err1 := c.SaveToFile("avator", path)             //存文件
		if err1 != nil {
			log.Println(err1)
			response := Simple{501, "failed"}
			c.Data["json"] = &response
		} else {
			response := Simple{200, "ok"}
			c.Data["json"] = &response
		}

	} else {
		f.Close()
		response := Simple{500, "failed"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}

func (c *FileController) Get() {
	customerId := c.Ctx.Input.Param(":userid")
	isExist := Exists("./upload/avator/" + customerId + ".jpg")
	if isExist == true {
		c.Ctx.Output.Download("./upload/avator/" + customerId + ".jpg")
		response := Simple{200, "ok"}
		c.Data["json"] = &response
	} else {
		response := Simple{500, "此用户没有上传头像"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}

func Exists(path string) bool {
	_, err := os.Stat(path)
	if err != nil {
		if os.IsExist(err) {
			return true
		}
		return false
	}
	return true
}
