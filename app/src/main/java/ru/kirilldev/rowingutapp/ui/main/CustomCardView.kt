package ru.kirilldev.rowingutapp.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.CardCurrentTrainingBinding

class CustomCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs), View.OnClickListener {

    private lateinit var binding: CardCurrentTrainingBinding
    private lateinit var layoutInflater: LayoutInflater

    init{
        View.inflate(context, R.layout.card_current_training, this)
        setBackground()
        setupBinding()
    }

    private fun setBackground(){
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_card_last_training)
        background = materialBg
        elevation = 2f
    }

    private fun setupBinding(){
        layoutInflater = LayoutInflater.from(context)
        binding = CardCurrentTrainingBinding.inflate(layoutInflater, this)
    }



    fun setName(name: String){
        binding.tvTrainingName.text = name
    }

    fun setTrainingType(type: String){
        binding.tvTypeTraining.text = String.format(
            resources.getString(R.string.type_training),
            type
        )
    }

    fun setTimeTraining(time: Int){
        binding.tvHoursTraining.text = String.format(
            resources.getString(R.string.time_training),
            time
        )
    }

    fun setTraining(training: String){
        binding.tvTraining.text = training
    }



    override fun onClick(v: View?) {

    }


    private fun startButtonAnimation(){

    }


}