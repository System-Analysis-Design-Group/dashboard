package main

import (
	_ "Account/models"
	_ "Account/routers"

	"github.com/astaxie/beego"
)

func main() {
	beego.Run()
}
