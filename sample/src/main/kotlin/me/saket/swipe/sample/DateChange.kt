package me.saket.swipe.sample

import android.util.Log

class DateChange: InterfaceRefreshValue {
  override fun onDateChanged(newText: String){
    Log.i("text", newText)}
}
