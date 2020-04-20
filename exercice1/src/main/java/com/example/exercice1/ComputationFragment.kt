package com.example.exercice1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ComputationFragment: Fragment() {
    val operation: String by lazy {
        arguments?.getString(ARGS_OPERATION, "addition") ?: "addition"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.computation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.title)
        val numberA = view.findViewById<EditText>(R.id.numberA)
        val numberB = view.findViewById<EditText>(R.id.numberB)
        val result = view.findViewById<TextView>(R.id.result)

        title.text = operation.toString()

        val buttonOperation = view.findViewById<Button>(R.id.add)
        buttonOperation.setOnClickListener {
            val numberAInt = numberA.text.toString().toInt()
            val numberBInt = numberB.text.toString().toInt()
            var resultInt = 0

            when (operation) {
                "Addition" -> {
                    resultInt = numberAInt + numberBInt
                }

                "Soustraction" -> {
                    resultInt = numberAInt - numberBInt
                }

                "Multiplication" -> {
                    resultInt = numberAInt * numberBInt
                }

                "Division" -> {
                    resultInt = numberAInt / numberBInt
                }
            }

            result.text = resultInt.toString()
        }
    }

    companion object {
        const val ARGS_OPERATION = "ARGS_OPERATION"
        fun newInstance(operation: String):ComputationFragment {
            return ComputationFragment().apply {
                //On sauvegarde l’opération dans les arguments,
                //Si le fragment se recrée, la valeur sera restaurée
                arguments = bundleOf(ARGS_OPERATION to operation)
            }
        }
    }
}