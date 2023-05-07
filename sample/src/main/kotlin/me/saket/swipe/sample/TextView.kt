package me.saket.swipe.sample

import kotlin.properties.Delegates

class TextView {
  var listener: InterfaceRefreshValue? = null

  var text: String by Delegates.observable("") { prop, old, new ->
    listener?.onDateChanged(new)
  }
}
