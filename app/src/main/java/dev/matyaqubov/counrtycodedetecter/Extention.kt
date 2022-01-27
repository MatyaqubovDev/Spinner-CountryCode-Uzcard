package dev.matyaqubov.counrtycodedetecter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

fun EditText.cardNumber(error: TextView) {
    this.addTextChangedListener (
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.toString().startsWith("8600") && p0.toString().length >= 4) {
                    error.visibility = View.VISIBLE
                } else {
                    error.visibility = View.GONE
                }

                for (i in 4 until p0.toString().length step 5) {
                    if (p0.toString()[i] != ' ') {
                        p0?.insert(i, " ")
                    }
                }
            }


        }
        )
}