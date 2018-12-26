package models

import (
	"fmt"

	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
)

func GetRole(userid int64) *UserRole {
	o := orm.NewOrm()
	ur := UserRole{UserID: userid}
	err := o.Read(&ur)
	if err == orm.ErrNoRows {
		fmt.Println("查询不到")
		return nil
	} else if err == orm.ErrMissPK {
		fmt.Println("找不到主键")
		return nil
	} else {
		return &ur
	}
}

func AddRole(userid, roleid int64) bool {
	ur := UserRole{userid, roleid}
	o := orm.NewOrm()
	_, err := o.Insert(&ur)
	if err != nil {
		return false
	} else {
		return true
	}

}

func UpdateRole(ur *UserRole) bool {
	o := orm.NewOrm()
	_, err := o.Update(ur)
	if err != nil {
		return false
	} else {
		return true
	}
}
