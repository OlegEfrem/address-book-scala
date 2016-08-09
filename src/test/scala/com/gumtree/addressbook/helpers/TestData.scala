package com.gumtree.addressbook.helpers

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.gumtree.addressbook.model.{DateOfBirth, Gender, Name, Person}
import scala.io.Source

trait TestData {

  val nonEmptyAddressBookFile = "/AddressBook.csv"

  def localFileSystemSource(resourceFileName: String = nonEmptyAddressBookFile) = Source.fromURL(getClass.getResource(resourceFileName)) //This will read from local filesystem: /src/test/resources

  def newPerson(name: Name = newName, minusDays: Long = 0) = Person(name, Gender.Male, DateOfBirth(LocalDate.now().minus(minusDays, ChronoUnit.DAYS)))

  def newName = Name("FirstName", Some("LastName"))

}
