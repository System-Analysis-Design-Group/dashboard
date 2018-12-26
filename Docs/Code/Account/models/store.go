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
