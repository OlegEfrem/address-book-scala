package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.UnitSpec
import com.gumtree.addressbook.model.{DateOfBirth, Gender, Name, Person}

class CsvDataParserTest extends UnitSpec {
  def csvDataParser(resourceFile: String = nonEmptyAddressBookFile) = new CsvDataParser(localFileSystemSource(resourceFile))

  "readPersons" should {
    "return parsed info from AddressBook.csv file" in {
      val expected = List(
        Person(Name("Bill", Some("McKnight")), Gender.Male, DateOfBirth.from("16/03/1977")),
        Person(Name("Paul", Some("Robinson")), Gender.Male, DateOfBirth.from("15/01/1985")),
        Person(Name("Gemma", Some("Lane")), Gender.Female, DateOfBirth.from("20/11/1991")),
        Person(Name("Sarah", Some("Stone")), Gender.Female, DateOfBirth.from("20/09/1980")),
        Person(Name("Wes", Some("Jackson")), Gender.Male, DateOfBirth.from("14/08/1974"))
      )
      csvDataParser().readPersons.toList shouldBe expected
    }

    "return empty Stream for empty csv file" in {
      csvDataParser("/EmptyAddressBook.csv").readPersons shouldBe empty
    }

    "throw an exception for the input 03/04/1999" in {
      the[DataParserException] thrownBy {
        csvDataParser("/NoLastNameAddressBook.csv").readPersons
      } should have message """Non parsable csv line: List(Bill,  Male,  16/03/77)"""
    }

  }
}
