package com.gumtree.addressbook.implementation

import com.gumtree.addressbook.AddressBookService
import com.gumtree.addressbook.model.{Gender, Name, Person}
import com.gumtree.addressbook.repo.AddressBookRepository

class DefaultAddressBookService(repo: AddressBookRepository) extends AddressBookService {

  override def countBy(gender: Gender.Value): Long = repo.findBy(gender).size

  override def oldestPerson(): Traversable[Person] = repo.findOldestPersons

  override def daysDifferenceBetween(olderPerson: Name, youngerPerson: Name): Long = -1

}
