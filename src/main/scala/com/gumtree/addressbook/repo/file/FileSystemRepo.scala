package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.model.{Gender, Person}
import com.gumtree.addressbook.repo.AddressBookRepository

class FileSystemRepo(dataParser: DataParser) extends AddressBookRepository {
  /**
    * Stream is like a list except that its elements are computed lazily.
    * Because of this, a stream can be infinitely long.
    * */
  private lazy val personStream: Stream[Person] = dataParser.readPersons

  override def findBy(gender: Gender.Value): Traversable[Person] = personStream.filter(_.gender == gender)

  override def findOldestPersons: Traversable[Person] = minAgeInDays match {
    case -1 => Stream()
    case pos => personStream.filter(_.ageDays == pos)
  }

  private def minAgeInDays: Long = personStream.map(_.ageDays).sortWith( _ > _) match {
    case l if l.nonEmpty => l.head
    case _ => -1
  }
}
