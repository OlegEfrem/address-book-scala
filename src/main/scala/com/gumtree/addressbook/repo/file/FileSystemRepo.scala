package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.model.{Gender, Person}
import com.gumtree.addressbook.repo.AddressBookRepository

class FileSystemRepo(dataParser: DataParser) extends AddressBookRepository {
  private lazy val personList: Stream[Person] = dataParser.readPersons

  override def findBy(gender: Gender.Value): Traversable[Person] = personList.filter(_.gender == gender)

}
