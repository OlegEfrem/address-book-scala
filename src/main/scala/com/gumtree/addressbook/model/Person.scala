package com.gumtree.addressbook.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

case class Person(name: Name, gender: Gender.Value, dateOfBirth: DateOfBirth) {
  override def toString: String = s"${name.first} ${name.last}"

  def ageYears: Long = ChronoUnit.YEARS.between(dateOfBirth.dateTime, LocalDate.now)

  def ageDays: Long = ChronoUnit.DAYS.between(dateOfBirth.dateTime, LocalDate.now)
}
