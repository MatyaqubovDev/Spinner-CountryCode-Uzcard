package dev.matyaqubov.counrtycodedetecter

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var code:String
    private lateinit var arrCountryCode:Array<String>
    private lateinit var card:EditText
    private lateinit var error:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrCountryCode = this.resources.getStringArray(R.array.DialingCountryCode)
        code = getCountryCode();

        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrCountryCode)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + arrCountryCode[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        }

        card=findViewById(R.id.et_cardNumber)
        error=findViewById(R.id.tv_error)
        card.cardNumber(error)

    }

    private fun getCountryCode():String{
        var countryId:String?=null
        var countryCode:String?=null

        val telephonyManager=this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        countryId=telephonyManager.simCountryIso.uppercase()


        for (i in arrCountryCode.indices){
            var arrDial=arrCountryCode[i].split(",").toTypedArray()
            if (arrDial[1].trim()==countryId.trim()){
                countryCode=arrDial[0]
                break
            }
        }
        return countryCode!!
    }
}