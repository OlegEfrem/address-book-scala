package com.gumtree.addressbook.repo

import com.gumtree.addressbook.model.{Gender, Name, Person}

trait AddressBookRepository {

  def findBy(gender: Gender.Value): Traversable[Person]

  def findBy(name: Name): Traversable[Person]

  def findOldestPersons(): Traversable[Person]

}
