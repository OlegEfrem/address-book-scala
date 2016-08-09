package com.gumtree.addressbook.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import com.gumtree.addressbook.AddressBookException
import org.joda.time.format.DateTimeFormat

case class DateOfBirth(dateTime: LocalDate) {
  override def toString: String = dateTime.format(DateOfBirth.outFormatter)
}

object DateOfBirth {
  private val inFormat = "dd/MM/yy"
  private val inFormatter = DateTimeFormat.forPattern(inFormat).withPivotYear(1975)
  private val outFormat = "dd/MM/yyyy"
  private val outFormatter = DateTimeFormatter.ofPattern(outFormat)


/**
  * Joda time is a workaround for missing pivotal year in java.time
  * */
  def from(textDateTime: String): DateOfBirth = {
    try {
      val jodaLocalDate = org.joda.time.LocalDate.parse(textDateTime, inFormatter).toString(outFormat)
      DateOfBirth(LocalDate.parse(jodaLocalDate, outFormatter))
    } catch {
      case e: Throwable => throw new AddressBookException(s"Text: $textDateTime does not match the required format: $inFormat or has illegal values: ${e.getMessage}", e)
    }
  }

}