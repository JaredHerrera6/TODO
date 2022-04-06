package edu.towson.cosc435.Herrera.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.intellij.lang.annotations.JdkConstants

@Composable
fun AddNew(vm: AddTodoViewModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)

    ){
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "New Todo", fontSize = 30.sp)
        }
        //Row to ask for the title
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(value = vm.title.value,
                onValueChange = vm::setTitle,
                label = {
                    Text("Title")
                },
                singleLine = true,
                modifier = Modifier.padding(15.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
        }
        //TextField for the Contents
        Row(
            horizontalArrangement = Arrangement.Center
        ){
            TextField(value = vm.content.value, onValueChange = vm::setContent,
                label = {
                    Text("Contents")
                },
                singleLine = false,
                modifier = Modifier.padding(30.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
        }
        //Checkbox to check for Complete
        Row(
            horizontalArrangement = Arrangement.Center
        ){
            val checkedState = remember{ mutableSetOf(false)}
            Checkbox(checked = vm.complete.value, onCheckedChange = vm::setComplete,
                    modifier = Modifier.padding(16.dp))
            Text(text = "Completed", modifier = Modifier.padding(16.dp))
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp),
            ){
            Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE91E63))
                ) {
                Text(text = "Save", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddnewPreview(){
}
