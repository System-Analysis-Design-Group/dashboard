package controllers

import (
	"log"
	"os"

	"github.com/astaxie/beego"
)

type FileController struct {
	beego.Controller
}

//上传图片
func (c *FileController) Post() {
	//获取上传的文件
	//从form-data中读数据
	f, _, err := c.GetFile("avator")
	customerId := c.Ctx.Input.Param(":userid")
	//上传成功
	if err == nil {
		//文件目录
		path := "./upload/avator/" + customerId + ".jpg"
		//关闭上传的文件，不然的话会出现临时文件不能清除的情况
		f.Close()
		//存文件
		err1 := c.SaveToFile("avator", path)
		if err1 != nil {
			log.Println(err1)
			response := Simple{
				501,
				"failed"}
			c.Data["json"] = &response
		} else {
			response := Simple{
				200,
				"ok"}
			c.Data["json"] = &response
		}

	} else { //上传失败
		f.Close()
		response := Simple{
			500,
			"failed"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}

//下载图片
func (c *FileController) Get() {
	//获取用户id
	customerId := c.Ctx.Input.Param(":userid")
	//拼凑文件名
	isExist := Exists("./upload/avator/" + customerId + ".jpg")
	//文件是否存在
	if isExist == true {
		c.Ctx.Output.Download("./upload/avator/" + customerId + ".jpg")
		response := Simple{
			200,
			"ok"}
		c.Data["json"] = &response
	} else {
		response := Simple{
			500,
			"此用户没有上传头像"}
		c.Data["json"] = &response
	}
	c.ServeJSON()
}

// Exists判断文件是否存在
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
