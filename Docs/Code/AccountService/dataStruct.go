package main

type User struct {
	Id         int    `json:"id"`
	Username   string `json:"username"`
	Password   string `json:"password"`
	Permission int    `json:"permission"`
}

type Store struct {
	Id        int     `json:"id"`
	Storename string  `json:"storename"`
	Phone     string  `json:"phone"`
	Userid    int     `json:"username"`
	Address   string  `json:"address"`
	Longitude float64 `json:"longitude"`
	Lagitude  float64 `json:"lagitude"`
}

type Stores []Store
type Users []User
