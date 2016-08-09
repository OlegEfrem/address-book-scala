package com.gumtree.addressbook.helpers

import com.gumtree.addressbook.model.{DateOfBirth, Gender, Name, Person}

trait TestData {
  def newPerson = Person(newName, Gender.Male, DateOfBirth.from("28/11/83"))

  def newName = Name("FirstName", Some("LastName"))

}
