package com.gumtree.addressbook

import com.gumtree.addressbook.model.{Gender, Name, Person}

trait AddressBookService {

  def countBy(gender: Gender.Value): Int = -1

  def oldestPerson(): Traversable[Person] = List()

  def daysDifferenceBetween(olderPerson: Name, youngerPerson: Name) = -1

}
