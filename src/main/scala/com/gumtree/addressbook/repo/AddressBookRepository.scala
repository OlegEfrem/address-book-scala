package com.gumtree.addressbook.repo

import com.gumtree.addressbook.model.{Gender, Person}

trait AddressBookRepository {
  def findBy(gender: Gender.Value): Traversable[Person]
}
