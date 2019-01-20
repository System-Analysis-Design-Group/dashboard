package controllers

type Simple struct {
	Code    int    `json:"code"`
	Message string `json:"message"`
}

type Obj struct {
	Username string `json:"username"`
	Userid   int64  `json:"userid"`
	Rolename string `json:"rolename"`
}

type Obj2 struct {
	Username string  `json:"username"`
	Userid   int64   `json:"userid"`
	Rolename string  `json:"rolename"`
	Stores   []int64 `json:"store_id"`
}

type Detail struct {
	Obj     `json:"obj"`
	Code    int    `json:"code"`
	Message string `json:"message"`
}

type Detail2 struct {
	Obj2    `json:"obj"`
	Code    int    `json:"code"`
	Message string `json:"message"`
}

type Detail1 struct {
	Obj     `json:"obj"`
	Code    int    `json:"code"`
	Message string `json:"message"`
	Token   string `json:"token"`
}

type FoundStore struct {
	NumFound int64   `json:"numfound"`
	StoreId  []int64 `json:"storeId"`
}

type Found struct {
	FoundStore `json:"obj"`
	Code       int    `json:"code"`
	Message    string `json:"message"`
}

type PermissionFound struct {
	PermissionInside `json:"obj"`
	Code             int    `json:"code"`
	Message          string `json:"message"`
}

type PermissionInside struct {
	RoleID      int64    `json:"roleid"`
	RoleName    string   `json:"rolename"`
	Permissions []string `json:"permissions"`
}

type Store struct {
	Name      string  `json:"name"`
	Phone     string  `json:"phone"`
	Type      string  `json:"type"`
	User_id   int64   `json:"user_id"`
	Address   string  `json:"address"`
	Longitude float64 `json:"longitude"`
	Latitude  float64 `json:"latitude"`
}

type ReStore struct {
	Store   `json:"obj"`
	Code    int    `json:"code"`
	Message string `json:"message"`
}
