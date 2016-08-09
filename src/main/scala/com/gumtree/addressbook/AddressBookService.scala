package com.gumtree.addressbook

import com.gumtree.addressbook.model.{Gender, Name, Person}

trait AddressBookService {

  def countBy(gender: Gender.Value): Long

  def oldestPerson: Traversable[Person]

  def daysDifferenceBetween(olderPerson: Name, youngerPerson: Name): Long

}
