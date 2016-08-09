package com.gumtree.addressbook

import com.gumtree.addressbook.model.{Gender, Name, Person}

/**
  * Service that allows to query an Address Book with Persons
  **/
trait AddressBookService {

  /**
    * Counts how many persons of a given gender are in the Address Book.
    *
    * @param gender person with this gender will be counted.
    * @return returns number of persons having the gender in the provided param.
    *         If there are not persons of such gender or repository is empty it will return 0.
    **/
  def countBy(gender: Gender.Value): Long

  /**
    * Retrieves the oldest person from the Address Book.
    *
    * @return person or persons that has/have maximum number of days (calculated by subtracting birthday from today).
    *         Empty if repository has no data.
    **/
  def oldestPerson(): Traversable[Person]


  /**
    * Calculates age difference in days between older and younger person.
    *
    * @param olderPerson   person that has more number of days compared to the youngerPerson.
    * @param youngerPerson that has less number of days the olderPerson.
    * @return number of days of olderPerson minus youngerPerson, negative if olderPerson is younger than youngerPerson.
    **/
  def daysDifferenceBetween(olderPerson: Name, youngerPerson: Name): Long

}
