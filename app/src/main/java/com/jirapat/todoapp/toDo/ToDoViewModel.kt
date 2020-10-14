package com.jirapat.todoapp.toDo

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.jirapat.todoapp.database.Activity
import com.jirapat.todoapp.database.ActivityDAO
import com.jirapat.todoapp.databinding.FragmentToDoBinding
import kotlinx.coroutines.*

class ToDoViewModel(
    val database: ActivityDAO,
    private val binding: FragmentToDoBinding,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val activity = database.get()

//    val activityString = Transformations.map(activity) {
//        activity -> formatActivity(activity)
//    }
//
//    private fun formatActivity(contact: List<Activity>): Spanned {
//        val sb = StringBuilder()
//        sb.apply {
//            contact.forEach {
//                append("Activity : ")
//                append(it.activityName)
//                append("<br>")
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
//        } else {
//            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun insert(_activity: Activity) {
        withContext(Dispatchers.IO) {
            database.insert(_activity)
        }
    }
    fun onActivityAdd() {
        uiScope.launch {
            val newContact = Activity()
            newContact.activityName = binding.inputActivity.text.toString()
            insert(newContact)
        }
    }


    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onActivityClear(){
        uiScope.launch {
            clear()
        }
    }

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)