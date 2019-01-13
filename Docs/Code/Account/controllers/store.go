package controllers

import (
	"Account/models"
	"encoding/json"
	"log"
	"strconv"

	"github.com/astaxie/beego"
)

type StoreController struct {
	beego.Controller
}

// SearchByUser 根据用户ID来搜索商店
func (c *StoreController) SearchByUser() {
	userid := c.Ctx.Input.Param(":userid")
	UserID, err := strconv.ParseInt(userid, 10, 64)
	if err != nil {
		response := Simple{500, "输入不合法"}
		c.Data["json"] = &response
	} else {
		num, store := models.SearchByUser(UserID)
		f := FoundStore{}
		f.NumFound = num
		k := int(num)
		for i := 0; i < k; i++ {
			f.StoreId = append(f.StoreId, store[i].StoreID)
		}
		response := Found{f, 200, "ok"}
		c.Data["json"] = &response
		c.ServeJSON()
	}
}

// SearchByID根据商店ID来搜索商店
func (c *StoreController) SearchByID() {
	storeid := c.Ctx.Input.Param(":storeid")
	StoreID, err := strconv.ParseInt(storeid, 10, 64)
	log.Println(StoreID)
	if err != nil {
		response := Simple{500, "输入不合法"}
		c.Data["json"] = &response
	} else {
		store := models.SearchByID(StoreID)
		reStore := Store{}
		reStore.Name = store.Name
		reStore.Phone = store.Phone
		reStore.Type = store.StoreType
		reStore.User_id = store.UserID
		reStore.Address = store.Address
		reStore.Longitude = store.Longitude
		reStore.Latitude = store.Latitude
		response := ReStore{reStore, 200, "ok"}
		c.Data["json"] = &response
		c.ServeJSON()
	}
}

// Get 根据类型搜索商店
func (c *StoreController) Get() {
	ttype := c.Ctx.Input.Param(":type")
	if ttype == "" {
		num, store := models.GetStoresifnull(ttype)
		log.Println(num, store)
		f := FoundStore{}
		f.NumFound = num
		k := int(num)
		for i := 0; i < k; i++ {
			f.StoreId = append(f.StoreId, store[i].StoreID)
		}
		response := Found{f, 200, "ok"}
		c.Data["json"] = &response
		c.ServeJSON()
		//c.TplName = "postStore.html"
		//return
	}
	num, store := models.GetStores(ttype)
	log.Println(num, store)
	f := FoundStore{}
	f.NumFound = num
	k := int(num)
	for i := 0; i < k; i++ {
		f.StoreId = append(f.StoreId, store[i].StoreID)
	}
	response := Found{f, 200, "ok"}
	c.Data["json"] = &response
	c.ServeJSON()
	//c.TplName = "postStore.html"
}

// Delete 根据商店ID，删除商店
func (c *StoreController) Delete() {
	StoreId := c.Ctx.Input.Param(":type")
	id, err0 := strconv.ParseInt(StoreId, 10, 64)
	if err0 != nil {
		response := Simple{500, "输入不合法"}
		c.Data["json"] = &response
	} else {
		isSuccess := models.DeleteStore(id)
		if isSuccess == true {
			response := Simple{200, "ok"}
			c.Data["json"] = &response
		} else {
			response := Simple{500, "删除出错，可能商店ID不存在"}
			c.Data["json"] = &response
		}
	}
	c.ServeJSON()
}

// Post 新增商家
func (c *StoreController) Post() {
	store := models.Store{}
	json.Unmarshal(c.Ctx.Input.RequestBody, &store)
	u := models.GetUserInfo(store.UserID)
	isExisted := models.CheckIfStoreExist(store.Name)
	if isExisted == true {
		response := Simple{500, "已经存在此店名"}
		c.Data["json"] = &response
	} else if u == nil {
		response := Simple{500, "没有此用户"}
		c.Data["json"] = &response
	} else {
		_, err := models.AddStore(&store)
		if err != nil {
			response := Simple{500, "failed"}
			c.Data["json"] = &response
		} else {
			response := Simple{200, "ok"}
			c.Data["json"] = &response
			ur := models.GetRole(store.UserID)
			if ur.RoleID == 0 {
				ur.RoleID = 1
				models.UpdateRole(ur)
			}
		}
	}
	c.ServeJSON()
}
