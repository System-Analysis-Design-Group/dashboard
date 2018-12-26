package controllers

type Simple struct {
	Code    int    `json:"code"`
	Message string `json:"message"`
}

type Obj struct {
	Username string `json:"username"`
	Userid   int64  `json:"userid`
	Rolename string `rolename:"rolename"`
}

type Detail struct {
	Obj     `json:"obj"`
	Code    int    `json:"code"`
	Message string `json:"message"`
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
