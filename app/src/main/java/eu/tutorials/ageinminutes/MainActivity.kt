package eu.tutorials.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this@MainActivity, "Date Picker works", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)

            tvSelectedDateInMinutes.text = differenceInMinutes.toString()

        }
            ,year
            ,month
            ,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}