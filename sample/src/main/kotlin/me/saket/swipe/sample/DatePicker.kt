package me.saket.swipe.sample

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.get
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
  date: String, updateDate: (String) -> Unit
) {
  val textView = TextView()
  var date by remember {
    mutableStateOf(

      ""
    )
  }
  val day: Int
  val month: Int
  val year: Int
  val mCalendar = Calendar.getInstance()
  day = mCalendar.get(Calendar.DAY_OF_MONTH)
  month = mCalendar.get(Calendar.MONTH)
  year = mCalendar.get(Calendar.YEAR)
  val mDatePickerDialog = DatePickerDialog(
    LocalContext.current,
    { _, year: Int, month: Int, day: Int -> date = "$year-${month + 1}-$day" },
    year,
    month,
    day
  )
  mDatePickerDialog.datePicker.minDate = mCalendar.timeInMillis
  Box(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier.align(Alignment.CenterStart)) {
      OutlinedTextField(value = date, onValueChange = updateDate,
        readOnly = false,
        label = { Text(text = "Data") }

      )
      Icon(
        imageVector = Icons.Filled.DateRange, contentDescription = null,
        modifier = Modifier
          .size(60.dp)
          .padding(4.dp)
          .clickable {
            textView.text = updateDate(date).toString()
            mDatePickerDialog.show()

//            updateDate(date)

          }
      )
    }
  }
}

