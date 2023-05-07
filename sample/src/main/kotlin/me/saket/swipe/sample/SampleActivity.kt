package me.saket.swipe.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.joda.time.DateTime
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
class SampleActivity : AppCompatActivity() {
val textView = TextView()
  @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      var date = remember {
        mutableStateOf("")
      }
      textView.listener = DateChange()


      var blood by rememberSaveable(stateSaver = BloodSaver) {
        mutableStateOf(HorizontalPagerContent("Krew", "2023-05-25"))
      }
      Column(modifier = Modifier.background(Color.White)) {
        Row {
          DatePicker(date = date.value) { newValue -> date.value = newValue }
          textView.text = date.value
          Log.d("date", "date" + date)
        }
        Row {

          IntroScreen()
        }
      }

    }
  }
}


@Composable
fun GetDate(date: String) {
  var dt = DateTime.parse(date)
  Text(text = "Data: " + dt.plusDays(40))
}


data class HorizontalPagerContent(var name: String, val date: String)

val BloodSaver = run {
  val nameKey = "name"
  val dateKey = "date"
  mapSaver(save = { mapOf(nameKey to it.name, dateKey to it.date) },
    restore = { HorizontalPagerContent(it[nameKey] as String, it[dateKey] as String) })
}
val bs = BloodSaver

val getList: List<HorizontalPagerContent> =
  listOf(
    HorizontalPagerContent(
      "Krew Pełna",
      "2022-05-26"
    ),
    HorizontalPagerContent(
      "Osocze",
      "2022-07-26"
    ),
    HorizontalPagerContent(
      "Krwinki",
      "2022-08-26"
    ),
    HorizontalPagerContent(
      "Płytki krwi",
      "A022-09-26"
    ),

    )


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen() {

  val pagerState = rememberPagerState()
  val scope = rememberCoroutineScope()
  val isNextVisible = remember {
    derivedStateOf {
      pagerState.currentPage != getList.size - 1
    }
  }
  val isPrevVisible = remember {
    derivedStateOf {
      pagerState.currentPage != 0
    }
  }


  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.White),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,

    ) {
    Box(
      modifier = Modifier
        .fillMaxHeight(0.75f)
        .fillMaxWidth()
    ) {

      HorizontalPager(count = getList.size, state = pagerState) { currentPage ->
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = getList[currentPage].name,
            color = Color.Black
          )
          Text(
            text = getList[currentPage].date,
            color = Color.Black
          )
        }
      }
    }

    HorizontalPagerIndicator(
      pagerState = pagerState,
      modifier = Modifier.align(Alignment.CenterHorizontally),
      pageCount = getList.size,
      indicatorWidth = 15.dp,
      indicatorHeight = 15.dp,
      activeColor = Color.Blue,
      inactiveColor = Color.Yellow
    )
  }
}

