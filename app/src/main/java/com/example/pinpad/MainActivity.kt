package com.example.pinpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.pinpad.library.PinpadView
import com.example.pinpad.library.PinpadViewListener
import android.widget.LinearLayout
import android.widget.TextView
import android.os.Looper

class MainActivity : AppCompatActivity(), PinpadViewListener {


    // SUPPOSE YOUR ACTUAL VALID PIN IS `1234`
    private val validPIN = "1234"

    private var pin = ""
    private val maxPINLength = 14
    private lateinit var header: LinearLayout
    private lateinit var loader:LinearLayout
    private lateinit var title: TextView
    private lateinit var subTitle:TextView
    private lateinit var textViewPin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        val pinpad: PinpadView = findViewById<View>(R.id.customPinpad) as PinpadView
        pinpad.setListener(this)

        /**
         *  TO CUSTOMISE PINPAD LAYOUT
         *
         * TO CHANGE THE BACKGROUND COLOR OF PINPAD LAYOUT LIKE THIS..
         * pinpad.pinpadLayout.setBackgroundColor(Color.GREEN)
         *
         * TO CHANGE THE PROPERTIES OF CHILD ELEMENTS (BUTTONS) OF PINPADLAYOUT
         * pinpad.pinpadLayout.getChildAt(0).***
         */
    }

    private fun init() {

        header = findViewById(R.id.header)
        loader = findViewById(R.id.loader)
        title = findViewById(R.id.title)
        subTitle = findViewById(R.id.subTitle)
        textViewPin = findViewById(R.id.textViewPin)
    }

    override fun onClickedNumber(number: Int) {

        if (pin.length < maxPINLength) {
            pin += number
            textViewPin.setText(pin)
        } else {
            subTitle.text = "Maximum 14 digits only"
        }
    }

    override fun onClickedBackspace() {
        if (pin.length != 0) {
            pin = pin.substring(0, pin.length - 1)
            textViewPin.setText(pin)
        }
    }

    override fun onClickedConfirm() {
        validatePIN()
    }

    private fun validatePIN() {

        header.visibility = View.GONE
        loader.visibility = View.VISIBLE

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            if (isValidPIN()) {
                setContentView(R.layout.activity_thank_you)
            } else {
                title.text = "Invalid PIN"
                subTitle.text = "Please try again."

                // RESETTING THE TEXTVIEW AND PIN
                textViewPin.setText("")
                pin =""
            }

            header.visibility = View.VISIBLE
            loader.visibility = View.GONE
        }, 500)
    }

    private fun isValidPIN(): Boolean {
        /* TODO - NEED TO WRITE CODE THAT ACTUALLY CHECKS PIN ENTRY WITH PAYMENTS NETWORK / DATABASE */
        return validPIN == pin
    }
}