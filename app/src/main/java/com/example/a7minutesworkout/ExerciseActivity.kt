package com.example.a7minutesworkout

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExcerciseBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
     private var binding:ActivityExcerciseBinding?=null

    private var restTimer:CountDownTimer?=null
    private var restProgress = 0

    private var excerciseTimer:CountDownTimer?=null
    private var excerciseProgress = 0

    private var excerciseList:ArrayList<ExcerciseModel>?=null
    private var currentExcercisePosition = -1

     private var tts: TextToSpeech ?= null
    private var player:MediaPlayer ?= null


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tts = TextToSpeech(this,this)
        setSupportActionBar(binding?.toolbarExcercise)
        if(supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        excerciseList =Constants.defaultExcerciseList()

        binding?.toolbarExcercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()


    }

    private fun setupRestView(){
        try{
           var soundURI =Uri.parse("android.resource://com.example.a7minutesworkout/"+R.raw.press_start)
            player =MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping =false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility =View.VISIBLE
        binding?.tvExexciseName?.visibility =View.INVISIBLE
        binding?.flExcerciseView?.visibility =View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE


        if(restTimer !=null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

      private fun setupExcersiceView(){
          binding?.flRestView?.visibility = View.INVISIBLE
          binding?.tvTitle?.visibility =View.INVISIBLE
          binding?.tvExexciseName?.visibility =View.VISIBLE
          binding?.flExcerciseView?.visibility =View.VISIBLE
          binding?.ivImage?.visibility=View.VISIBLE

          if(excerciseTimer !=null){
              excerciseTimer?.cancel()
              excerciseProgress = 0
          }
          speakOut(excerciseList!![currentExcercisePosition].getName())
            binding?.ivImage?.setImageResource(excerciseList!![currentExcercisePosition].getImage())
          binding?.tvExexciseName?.text =excerciseList!![currentExcercisePosition].getName()
          binding?.tvTitle?.text=" Get Ready For "+excerciseList!![currentExcercisePosition+1].getName()

          setExerciseProgressBar()
      }

    private fun setExerciseProgressBar(){
        binding?.progressBarExcercise?.progress =excerciseProgress

        excerciseTimer =object: CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                excerciseProgress++
                binding?.progressBarExcercise?.progress =30-excerciseProgress
                binding?.tvTimerExcercise?.text =(30-excerciseProgress).toString()
                //speakOut(binding?.tvTimerExcercise?.text.toString())
            }

            override fun onFinish() {
               if(currentExcercisePosition <excerciseList?.size!! -1){
                   setupRestView()
               }else{
                   Toast.makeText(this@ExerciseActivity,"Congratulations! You have completed 7 Min workout. let's have one more LAP",Toast.LENGTH_SHORT).show()
               }
            }

        }.start()
    }


    private fun setRestProgressBar(){
        binding?.progressBar?.progress =restProgress

        restTimer =object: CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress =10-restProgress
                binding?.tvTimer?.text =(10-restProgress).toString()
              //  speakOut(binding?.tvTimer?.text.toString())
            }

            override fun onFinish() {
                currentExcercisePosition++
                setupExcersiceView()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer !=null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(excerciseTimer !=null){
            excerciseTimer?.cancel()
           excerciseProgress = 0
        }

        if(tts !=null){
            tts?.stop()
            tts?.shutdown()
        }

        if(player !=null){
            player!!.stop()
        }
        binding =null
    }

     private fun speakOut(text:String){
//         tts?.setPitch(0.7.toFloat())
//         tts?.setSpeechRate(0.5.toFloat())
         tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
     }
    override fun onInit(status: Int) {
       if(status == TextToSpeech.SUCCESS){
           val result = tts!!.setLanguage(Locale.ENGLISH)

           if(result == TextToSpeech.LANG_MISSING_DATA
               || result == TextToSpeech.LANG_NOT_SUPPORTED
           ){

               Log.d("TTS","TextToSpeech failed")
           }else{
                Log.d("initF","initialization failed")
           }
       }
    }
}