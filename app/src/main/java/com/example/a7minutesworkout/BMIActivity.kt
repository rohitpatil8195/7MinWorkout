package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.RadioGroup

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRICS_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"

        private var currantVisibleView: String =METRICS_UNITS_VIEW
    }
    private var binding: ActivityBmiBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title ="Calculate BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
           onBackPressed()
        }
        binding?.btnCalculateUnits?.setOnClickListener {
          if(validateMetricUnits()){
              val heightValue:Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
              val weightValue:Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
              val bmi = weightValue / (heightValue*heightValue)
              displayBMIResult(bmi)
          }else{
              Toast.makeText(this,"Please enter valid values",Toast.LENGTH_LONG).show()
          }

        }
               makeVisibleMetricUnitsView()
        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId:Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeUSVisibleMetricUnitsView()
            }
        }
    }


     private fun makeVisibleMetricUnitsView(){
          currantVisibleView = METRICS_UNITS_VIEW
         binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
         binding?.tilMetricUnitHeight?.visibility =View.VISIBLE

         binding?.tilUsMetricUnitWeight?.visibility =View.INVISIBLE
         binding?.tilMetricUnitHeightInFt?.visibility =View.INVISIBLE
         binding?.tilMetricUnitHeightInch?.visibility =View.INVISIBLE

         binding?.etMetricUnitHeight?.text!!.clear()
         binding?.etMetricUnitWeight?.text!!.clear()

         binding?.llDiplayBMIResult?.visibility =View.INVISIBLE
     }


    private fun makeUSVisibleMetricUnitsView(){
        currantVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility =View.INVISIBLE

        binding?.tilUsMetricUnitWeight?.visibility =View.VISIBLE
        binding?.tilMetricUnitHeightInFt?.visibility =View.VISIBLE
        binding?.tilMetricUnitHeightInch?.visibility =View.VISIBLE

        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeightFt?.text!!.clear()
        binding?.etMetricUnitHeightInch?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility =View.INVISIBLE
    }


    private fun displayBMIResult(bmi:Float){
        var bmiLabel: String
        var bmiDescription: String

        if(bmi.compareTo(15f)<=0){
            bmiLabel ="Very severely underweight"
            bmiDescription = "Oops! you really need to take care of yourself! Eat more!"
        }

        else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f)<= 0 ){
            bmiLabel ="severely underweight"
            bmiDescription = "Oops! you really need to take care of yourself! Eat more!"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f)<= 0 ){
            bmiLabel ="underweight"
            bmiDescription = "Oops! you really need to take care of yourself! Eat more!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f)<= 0 ){
            bmiLabel ="Normal"
            bmiDescription = "Congratulations! You are in good shape!"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f)<= 0 ){
            bmiLabel ="OverWeight"
            bmiDescription = "Oops! you really need to take care of yourself! Workout More!"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f)<= 0 ){
            bmiLabel ="OverWeight"
            bmiDescription = "Oops! you really need to take care of yourself! Workout More!"
        }
        else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f)<= 0 ){
            bmiLabel ="OverWeight"
            bmiDescription = "Oops! you really need to take care of yourself! Workout More!"
        }
        else{
            bmiLabel = "Obese Class ||| (Very severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
         binding?.llDiplayBMIResult?.visibility = View.VISIBLE
         binding?.tvYourBMIType?.text = bmiLabel
        binding?.tvYourBMIDescription?.text = bmiDescription
        binding?.tvYourBMIType?.text = bmiValue
    }

    private fun validateMetricUnits():Boolean{
        var isValid = true
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
          isValid =false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}