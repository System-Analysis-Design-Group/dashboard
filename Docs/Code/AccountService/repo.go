package main

import "fmt"

var currentID int

var users Users //假的数据库

// init 随意初始化这个伪数据库
func init() { //随意初始化这个伪数据库
	RepoCreateUser(User{Username: "CustomerA"})
	RepoCreateUser(User{Username: "CustomerB"})
}

// RepoFindUser 假的数据库“查”操作
func RepoFindUser(id int) User {
	for _, t := range users {
		if t.Id == id {
			return t
		}
	}
	// return empty Todo if not found
	return User{}
}

// RepoCreateUser 假的数据库“增”操作
func RepoCreateUser(t User) User {
	currentID++
	t.Id = currentID
	users = append(users, t)
	return t
}

// RepoDestroyUser 假的数据库“删”操作
func RepoDestroyUser(id int) error {
	for i, t := range users {
		if t.Id == id {
			users = append(users[:i], users[i+1:]...)
			return nil
		}
	}
	return fmt.Errorf("Could not find Todo with id of %d to delete", id)
}
