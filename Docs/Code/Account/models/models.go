package models

import (
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
)

func init() {
	orm.RegisterDriver("mysql", orm.DRMySQL)
	orm.RegisterDataBase("default", "mysql", beego.AppConfig.String("mysqluser")+":"+beego.AppConfig.String("mysqlpass")+"@tcp(127.0.0.1:3306)/account?charset=utf8", 30)
	//orm.RegisterModel(new(User), new(Role), new(Permission), new(Address), new(RolePermission), new(Store), new(UserRole))
	orm.RegisterModel(new(User), new(Store), new(Role), new(Permission), new(RolePermission), new(UserRole))
	orm.RunSyncdb("default", false, true)
}

type User struct {
	UserID   int64 `orm:"pk;auto"`
	Username string
	Password string
	Avator   string
}

type Store struct {
	StoreID   int64   `orm:"pk;auto"`
	UserID    int64   `json:"user_id"`
	Name      string  `json:"name"`
	Phone     string  `json:"phone"`
	StoreType string  `json:"type"`
	Address   string  `json:"address"`
	Longitude float64 `json:"longitude"`
	Latitude  float64 `json:"latitude"`
}

type Role struct {
	RoleName string
	RoleID   int64 `orm:"pk"`
}

type Permission struct {
	Name  string
	PerID int64 `orm:"pk"`
}

// type Address struct {
// 	Name      string
// 	Longitude float64
// 	Latitude  float64
// 	AddID     int64 `orm:"pk"`
// }

type RolePermission struct {
	Id int64 `orm:"pk"`
}

type UserRole struct {
	UserID int64 `orm:"pk"`
	RoleID int64
}
