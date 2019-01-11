package models

import (
	"log"

	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
)

func AddStore(s *Store) (int64, error) {
	o := orm.NewOrm()
	storeID, err := o.Insert(s)
	return storeID, err
}

// CheckIfUserExist 查看是否存在该商店名
func CheckIfStoreExist(storeName string) bool {
	o := orm.NewOrm()
	valiUser := Store{}
	valiUser.Name = storeName
	err := o.Read(&valiUser, "Name")
	if err != nil {
		return false
	} else {
		return true
	}
}

func DeleteStore(id int64) bool {
	o := orm.NewOrm()
	if num, err := o.Delete(&Store{StoreID: id}); err == nil && num != 0 {
		log.Println(num)
		return true
	} else {
		log.Println(err)
		return false
	}
}

func GetStores(t string) (int64, []Store) {
	o := orm.NewOrm()
	var store []Store
	num, err := o.Raw("select * from store where store_type = ?", t).QueryRows(&store)
	if err != nil {
		log.Println(err)
	}
	return num, store
}

func GetStoresifnull(t string) (int64, []Store) {
	o := orm.NewOrm()
	var store []Store
	num, err := o.Raw("select * from store").QueryRows(&store)
	if err != nil {
		log.Println(err)
	}
	return num, store
}
