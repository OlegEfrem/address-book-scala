package com.gumtree.addressbook.implementation

import com.gumtree.addressbook.UnitSpec
import com.gumtree.addressbook.model.Name
import com.gumtree.addressbook.repo.AddressBookRepository
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._

/**
  * The only functionality not already unit tested is daysDifferenceBetween(oldPerson, youngPerson).
  * Other methods are just delegations to the repository one.
  **/
class DefaultAddressBookServiceTest extends UnitSpec {

  val addressBookRepository = mock[AddressBookRepository]

  def defaultAddressBookService = new DefaultAddressBookService(addressBookRepository)

  "daysDifferenceBetween" should {

    "calculate days diff if both persons are in the repo" in {
      val olderPerson = newPerson(name = Name("OlderPersonName"), minusDays = 2000)
      val youngerPerson = newPerson(name = Name("YoungerPersonName"), minusDays = 1000)
      when(addressBookRepository.findBy(olderPerson.name)).thenReturn(Stream(olderPerson))
      when(addressBookRepository.findBy(youngerPerson.name)).thenReturn(Stream(youngerPerson))
      defaultAddressBookService.daysDifferenceBetween(olderPerson.name, youngerPerson.name) shouldBe 1000
    }

    "get first person from the Stream if multiple with same name are found in the repo" in {
      val olderPerson = newPerson(name = Name("OlderPersonName"), minusDays = 2000)
      val olderPerson2 = newPerson(name = Name("OlderPersonName"), minusDays = 3000)
      val youngerPerson = newPerson(name = Name("YoungerPersonName"), minusDays = 1000)
      val youngerPerson2 = newPerson(name = Name("YoungerPersonName"), minusDays = 500)
      when(addressBookRepository.findBy(olderPerson.name)).thenReturn(Stream(olderPerson, olderPerson2))
      when(addressBookRepository.findBy(youngerPerson.name)).thenReturn(Stream(youngerPerson, youngerPerson2))
      defaultAddressBookService.daysDifferenceBetween(olderPerson.name, youngerPerson.name) shouldBe 1000
    }

    "throw an exeption if any person is not found" in {
      when(addressBookRepository.findBy(ArgumentMatchers.any(classOf[Name]))).thenReturn(Stream())
      the[DataNotFoundException] thrownBy {
        defaultAddressBookService.daysDifferenceBetween(Name("name1"), Name("name2"))
      } should have message """Not found person with the name: name1"""
    }

  }

}
