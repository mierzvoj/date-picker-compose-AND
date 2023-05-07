package me.saket.swipe.sample


import DonationType
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NextDonation(
  override val date: LocalDateTime,
  override val type: String,
) : NextDonationInterface {
  fun calculateNextDonation(): Long {
    return if (this.type == DonationType.FULL) {
      ChronoUnit.DAYS.between(LocalDateTime.now(), date.plusMonths(3))
    } else {
      ChronoUnit.DAYS.between(LocalDateTime.now(), date.plusMonths(2))
    }
  }
}


