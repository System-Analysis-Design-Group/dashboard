package models

import (
	"fmt"
	"log"

	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
)

// AddCustomer 向用户表中增加一个用户
func AddCustomer(u *User) (int64, error) {
	o := orm.NewOrm()
	userID, err := o.Insert(u)
	return userID, err
}

// ValidateUser 验证用户的登录信息
func ValidateUser(u User) (int64, bool) {
	o := orm.NewOrm()
	valiUser := User{}
	valiUser.Username = u.Username
	err := o.Read(&valiUser, "Username")
	if err != nil || valiUser.Password != u.Password {
		return -1, false
	} else {
		return valiUser.UserID, true
	}
}

// CheckIfUserExist 查看是否存在该用户名
func CheckIfUserExist(username string) bool {
	o := orm.NewOrm()
	valiUser := User{}
	valiUser.Username = username
	err := o.Read(&valiUser, "Username")
	if err != nil {
		return false
	} else {
		return true
	}
}

func GetUserInfo(id int64) *User {
	o := orm.NewOrm()
	u := User{UserID: id}
	err := o.Read(&u)
	if err == orm.ErrNoRows {
		fmt.Println("查询不到")
		return nil
	} else if err == orm.ErrMissPK {
		fmt.Println("找不到主键")
		return nil
	} else {
		return &u
	}
}

func DeleteUser(id int64) bool {
	o := orm.NewOrm()
	if num, err := o.Delete(&User{UserID: id}); err == nil {
		log.Println(num)
		if num == 0 {
			return false
		}
		return true
	} else {
		log.Println(err)
		return false
	}
}
