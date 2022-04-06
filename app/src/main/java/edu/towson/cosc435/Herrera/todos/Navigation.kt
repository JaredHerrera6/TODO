package edu.towson.cosc435.Herrera.todos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

//@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController){
    val addtodoViewModel: AddTodoViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = NavItem.Todos.route
    ){
        composable(
            route = NavItem.Todos.route
        ){
            var origtodos = (0..10).map{index ->

                val date = Calendar.getInstance().time.toString()
                Todo("Todo $index", "Contents $index", false,date)}
            var todos by remember{ mutableStateOf(origtodos)}
            Todos(todos,addtodoViewModel,
                onDelete = { idx  ->
                    todos = todos.subList(0,idx) + todos.subList(idx + 1, todos.size)
                },
                onToggle = { idx ->
                    todos = todos.mapIndexed{index , todo ->
                        if(idx == index){
                            todo.copy(complete = !todo.complete)
                        }
                        else
                            todo
                    }
                    origtodos = todos
                })
        }
        composable(
            route = NavItem.AddNewTodo.route
        ){

            AddNew(addtodoViewModel)
        }
    }
}