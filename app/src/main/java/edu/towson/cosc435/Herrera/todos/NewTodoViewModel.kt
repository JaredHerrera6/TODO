package edu.towson.cosc435.Herrera.todos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class AddTodoViewModel:ViewModel() {
    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> =_title
    private val _content : MutableState<String> = mutableStateOf("")
    val content: State<String> = _content
    private val _complete : MutableState<Boolean> = mutableStateOf(false)
    val complete : State<Boolean> = _complete
    private val _duedate : MutableState<String> = mutableStateOf("")
    val duedate : State<String> = _duedate
    fun setTitle(title: String){
        _title.value =title
    }
    fun setDueDate(duedate: String){
        _duedate.value = duedate
   }
    fun setContent (content: String){
        _content.value = content
    }
    fun setComplete(complete:Boolean){
       _complete.value = complete
    }
}