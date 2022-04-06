package edu.towson.cosc435.Herrera.todos

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoRow (
    idx :Int,
    todo:Todo,
    vm: AddTodoViewModel = viewModel(),
    onDelete: (Int) -> Unit,
    onToggle: (Int) -> Unit
){
    var (showDialog,setShowDialog) = remember{ mutableStateOf(false)}

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            //This is where the long click for delete will go

            modifier = Modifier
                .combinedClickable(
                    onLongClick = {
                        setShowDialog(true)
                    }
                ) {

                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            DeleteDialog(onDelete = onDelete, idx = idx,setShowDialog,showDialog)
            Column(
                modifier = Modifier
                    .weight(2f)){
                Text(todo.title, fontSize = 25.sp)
                Text(todo.Content, fontSize = 15.sp)
                Text(todo.duedate,fontSize = 10.sp, modifier = Modifier.padding(bottom = 5.dp))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Checkbox(checked = todo.complete, onCheckedChange = {onToggle(idx)})
            }

        }
    }
}

@Composable
fun DeleteDialog(onDelete: (Int) -> Unit,idx:Int,setShowDialog:(Boolean)->Unit, showDialog:Boolean){
    if(showDialog){
        AlertDialog(onDismissRequest = {},
            title = {
                Text(text = "Confirm?")
            },
            confirmButton = {
                Button(onClick = {Close(onDelete = onDelete, idx = idx,setShowDialog)}) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {}
            ,
            text = {Text(text = "Are you sure you want to delete?")}
            )

    }

}
fun Close(onDelete: (Int) -> Unit,idx:Int,setShowDialog:(Boolean)->Unit){
    setShowDialog(false)
    onDelete(idx)
}