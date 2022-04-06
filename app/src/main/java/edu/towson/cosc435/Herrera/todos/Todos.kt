package edu.towson.cosc435.Herrera.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Todos(
    todos : List<Todo>,
    vm: AddTodoViewModel = viewModel(),
    onDelete: (Int) -> Unit,
    onToggle: (Int) -> Unit,

){
    Column() {
        LazyColumn(){
            itemsIndexed(todos){
                idx,todo -> TodoRow(idx = idx, todo = todo, vm, onDelete, onToggle )
            }
        }
    }
}