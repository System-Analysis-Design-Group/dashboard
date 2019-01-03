package controllers

import (
	"Account/models"
	"encoding/json"
	"log"

	"github.com/astaxie/beego"
)

type StoreController struct {
	beego.Controller
}

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
		return
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

func (c *StoreController) Delete() {

	c.TplName = "index.tpl"
}

func (c *StoreController) Post() {
	// store := models.Store{}
	// store.Name = c.GetString("name")
	// store.Phone = c.GetString("phone")
	// store.StoreType = c.GetString("type")
	// store.UserID, _ = strconv.ParseInt(c.GetString("user_id"), 10, 64)
	// store.Address = c.GetString("address")
	// store.Longitude, _ = strconv.ParseFloat(c.GetString("longitude"), 64)
	// store.Latitude, _ = strconv.ParseFloat(c.GetString("latitude"), 64)
	gotStore := Store{}
	json.Unmarshal(c.Ctx.Input.RequestBody, &gotStore)
	store := models.Store{}
	store.Name = gotStore.Name
	store.Phone = gotStore.Phone
	store.StoreType = gotStore.Type
	store.UserID = gotStore.User_id
	store.Address = gotStore.Address
	store.Longitude = gotStore.Longitude
	store.Latitude = gotStore.Latitude

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
	c.ServeJSON()
}
