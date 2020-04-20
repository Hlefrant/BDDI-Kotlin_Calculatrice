package com.example.exercice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //créer une instance du fragmen
        //valider la transaction

        change(ChoiceFragment())
    }

}

fun FragmentActivity.change(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {

        replace(R.id.container, fragment)

        addToBackStack(null)
    }.commit()
}