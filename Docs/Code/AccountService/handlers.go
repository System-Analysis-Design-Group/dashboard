package main

import (
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

// Index 绑定'/'
func Index(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, "Welcome!")
}

// Alluser 查看所有用户信息 绑定'/Accounts'  method=GET
func Alluser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)
	if err := json.NewEncoder(w).Encode(users); err != nil {
		panic(err)
	}
	// TODO
}

// UserGet 绑定 '/Accounts/Customer/{user_id}'  method= GET
func UserGet(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	var user_id int
	var err error
	if user_id, err = strconv.Atoi(vars["user_id"]); err != nil {
		panic(err)
	}
	todo := RepoFindUser(user_id)
	if todo.Id > 0 {
		w.Header().Set("Content-Type", "application/json; charset=UTF-8")
		w.WriteHeader(http.StatusOK)
		if err := json.NewEncoder(w).Encode(todo); err != nil {
			panic(err)
		}
		return
	}
	// If we didn't find it, 404
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusNotFound)
	if err := json.NewEncoder(w).Encode(jsonErr{Code: http.StatusNotFound, Text: "Not Found"}); err != nil {
		panic(err)
	}
}

// UserDelete 绑定'/Accounts/Customer/{user_id}'   method= DELETE
func UserDelete(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	var user_id int
	var err error
	if user_id, err = strconv.Atoi(vars["user_id"]); err != nil {
		panic(err)
	}
	if err = RepoDestroyUser(user_id); err != nil {
		panic(err)
	}

}

// UserCreate 绑定'/Accounts/Customer'    method=POST
func UserCreate(w http.ResponseWriter, r *http.Request) {
	var user User
	body, err := ioutil.ReadAll(io.LimitReader(r.Body, 1048576))
	if err != nil {
		panic(err)
	}
	if err := r.Body.Close(); err != nil {
		panic(err)
	}
	if err := json.Unmarshal(body, &user); err != nil {
		w.Header().Set("Content-Type", "application/json;   charset=UTF-8")
		w.WriteHeader(422) // unprocessable entity
		if err := json.NewEncoder(w).Encode(err); err != nil {
			panic(err)
		}
	}

	t := RepoCreateUser(user)
	w.Header().Set("Content-Type", "application/json;   charset=UTF-8")
	w.WriteHeader(http.StatusCreated)
	if err := json.NewEncoder(w).Encode(t); err != nil {
		panic(err)
	}
	//todo
}

// PermissionGet 绑定'/Accounts/Permission/{user_id}'   method=GET
func PermissionGet(w http.ResponseWriter, r *http.Request) {
	//todo
}

// PermissionPost 绑定'/Accounts/Permission/{user_id}'   method=POST
func PermissionPost(w http.ResponseWriter, r *http.Request) {
	//todo
}

// PermissionDelete 绑定'/Accounts/Permission/{user_id}'
func PermissionDelete(w http.ResponseWriter, r *http.Request) {
	//todo
}
