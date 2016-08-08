package com.gumtree.addressbook

import com.gumtree.addressbook.model.{Gender, Name}

object Application {
  private val addressBookService: AddressBookService = new AddressBookService {}
  private val olderPersonName = "Bill"
  private val youngerPersonName = "Paul"

  def main(args: Array[String]): Unit = {
    println(malesCountMessage)
    println(oldestPersonsMessage)
    println(daysDifferenceMessage)
  }

  private def malesCountMessage: String = {
    val malesCount = addressBookService.countBy(Gender.Male)
    malesCount match {
      case -1 => s"$noPersonsMessage to count the number of males."
      case _ => s"There are: $malesCount males in the address book."
    }
  }

  private def oldestPersonsMessage: String = {
    val personList = addressBookService.oldestPerson()
    personList.toList match {
      case Nil => "There are no persons in the address book to find the oldest."
      case head :: Nil => s"The oldest person in the address book is ${head.name}, was born on ${head.dateOfBirth} and now is ${head.ageYears} years old."
      case head :: tail => s"The oldest persons in the address book are ${personList.mkString(",")}, were born on ${head.dateOfBirth} and now are ${head.ageYears} old."
    }
  }

  private def daysDifferenceMessage: String = {
    val daysDifference = addressBookService.daysDifferenceBetween(olderPerson = Name("Bill"), youngerPerson = Name("Paul"))
    daysDifference match {
      case -1 => s"$noPersonsMessage to calculate how many days is $olderPersonName older than $youngerPersonName"
      case _ => s"$olderPersonName is older than $youngerPersonName with: $daysDifference days."
    }

  }

  private val noPersonsMessage = "There are no persons in the address book"

}
