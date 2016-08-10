package com.gumtree.addressbook.implementation

import com.gumtree.addressbook.AddressBookService
import com.gumtree.addressbook.model.{Gender, Name, Person}
import com.gumtree.addressbook.repo.AddressBookRepository

class DefaultAddressBookService(repo: AddressBookRepository) extends AddressBookService {

  override def countBy(gender: Gender.Value): Long = repo.findBy(gender).par.size

  override def oldestPerson(): Traversable[Person] = repo.findOldestPersons()

  override def daysDifferenceBetween(olderPerson: Name, youngerPerson: Name): Long = {
    val older = repo.findBy(olderPerson)
    val younger = repo.findBy(youngerPerson)
    headOrException(older, olderPerson).ageDays - headOrException(younger, youngerPerson).ageDays
  }

  private def headOrException(persons: Traversable[Person], name: Name): Person = {
    persons.headOption match {
      case Some(x) => x
      case None => throw new DataNotFoundException(s"Not found person with the name: $name")
    }
  }

}
