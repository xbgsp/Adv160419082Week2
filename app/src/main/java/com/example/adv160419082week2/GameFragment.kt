package com.example.adv160419082week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random


class GameFragment : Fragment() {
    var random1 = 1
    var random2 = 1
    var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()

        if(arguments != null)
        {
            var playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        btnSubmit.setOnClickListener {
            val result = random1 + random2

            if(txtAnswer.text.toString() == result.toString())
            {
                score = score + 1
                refresh()
                txtAnswer.setText("")
                Toast.makeText(activity, "Jawaban anda benar! Pertahankan!!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
                Toast.makeText(activity, "Jawaban anda salah, Ayo berusaha lagi!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun refresh() {
        random1 = Random.nextInt(1, 51)
        random2 = Random.nextInt(1, 51)

        txtAngka1.text = random1.toInt().toString()
        txtAngka2.text = random2.toInt().toString()
    }
}