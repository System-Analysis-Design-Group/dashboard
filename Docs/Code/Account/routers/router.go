package routers

import (
	"Account/controllers"

	"github.com/astaxie/beego"
)

func init() {
	beego.Router("/Accounts/Signin", &controllers.SigninController{})
	beego.Router("/Accounts/Signup", &controllers.SignupController{})
	beego.Router("/Accounts/Customer/?:id", &controllers.CustomerController{})
	beego.Router("/Accounts/Stores/?:type", &controllers.StoreController{})
	beego.Router("/Accounts/Permission/?:id", &controllers.PermissionController{})
	beego.Router("/Accounts/Stores/ByUser/?:userid", &controllers.StoreController{}, "get:SearchByUser")
	beego.Router("/Accounts/Stores/ById/?:storeid", &controllers.StoreController{}, "get:SearchByID")
	beego.Router("Accounts/Avator/?:userid", &controllers.FileController{})
	beego.Router("Accounts/logout", &controllers.LogoutController{})
}
