package edu.towson.cosc435.Herrera.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.Herrera.todos.ui.theme.TodosTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.function.IntConsumer

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    MainScreen(navController)
                }
            }
        }
    }
}
@Composable
fun MainScreen(navController: NavHostController){
    val state = rememberScaffoldState(drawerState = DrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = state,
        //Top bar call the Toolbar Function
        topBar = { TopABar(scope = scope, scaffoldState = state )},
        drawerContent = {
            Drawer(scope = scope,  scaffoldState = state,navController = navController)
        },// top bar close
        floatingActionButton = {FAB( navController = navController) }
    ){
        Navigation(navController = navController)
    }
}

@Composable
fun TopABar(scope: CoroutineScope,scaffoldState: ScaffoldState){
    TopAppBar(
        title = {Text(text = "TODOS", fontSize = 20.sp )},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription ="" )
            }
        },// Navigation Icon end
        backgroundColor = Color(0xFFE91E63),
        contentColor = Color.White,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
            }
        }
    )
}

@Composable
fun Drawer(scope: CoroutineScope,scaffoldState: ScaffoldState , navController: NavController ){
    Column(
        modifier = Modifier
            .background(color= Color.White)
    ) {
        // This row contains the Pink box that contains the Todo
        Row() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE91E63))
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "TODOS",
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 40.dp)
                )
            }
        }
        // This row will contain the Add new option
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //When you click nav controller is call and goes to the Add new todo screen
                .clickable { navController.navigate(route = NavItem.AddNewTodo.route) }
                .padding(10.dp)
            , horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new Todo", tint = Color.DarkGray)
                Text(
                    text = "Add new Todo", color = Color.DarkGray, fontSize = 20.sp
                )
            }

        }
}




@Composable
fun FAB( navController: NavController ){

    FloatingActionButton(
        ////When you click nav controller is call and goes to the Add new todo screen
        onClick = { navController.navigate(route = NavItem.AddNewTodo.route)},
        backgroundColor = Color(0xFFE91E63),
        contentColor =  Color.White,

        ){
            Icon(Icons.Filled.Add, null)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodosTheme {
        MainScreen(navController = rememberNavController() )
    }
}
