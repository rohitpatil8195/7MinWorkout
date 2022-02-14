package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityFinishAcitivityBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishAcitivity : AppCompatActivity() {

    private var binding:ActivityFinishAcitivityBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishAcitivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinish)
        if(supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarFinish?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.bFinish?.setOnClickListener {
           finish()
        }

        val historyDao = (application as WorkOutApp).db.historyDao()

        addDateToDatabase(historyDao)
    }

     private fun addDateToDatabase(historyDao: HistoryDao){
         val c = Calendar.getInstance()
         val dateTime = c.time
         val sdf = SimpleDateFormat("dd MMM yyyy HH:mm a",Locale.getDefault())
          val date = sdf.format(dateTime)

          lifecycleScope.launch{
              historyDao.insert(HistoryEntity(date))
          }
     }
}