package edu.towson.cosc435.Herrera.todos

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

sealed class NavItem (var route:String){
    object AddNewTodo : NavItem("addNew")
    object Todos : NavItem("Todos")
}