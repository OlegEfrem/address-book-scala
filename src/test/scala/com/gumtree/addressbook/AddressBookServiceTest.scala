package com.gumtree.addressbook

import com.gumtree.addressbook.model.{Gender, Name}
import org.scalatest.{Matchers, WordSpec}

class AddressBookServiceTest extends WordSpec with Matchers {
  val addressBookService: AddressBookService = new AddressBookService {}


  "countBy(Gender.Male)" should {

    "return 3" in {
      addressBookService.countBy(Gender.Male) shouldBe 3
    }

  }

  "oldestPerson" should {

    "return Wes Jackson" in {
      addressBookService.oldestPerson().head.name shouldBe Name("Wes", Some("Jackson"))
    }

  }

  "daysDifferenceBetween(Bill, Paul)" should {

    "return 2862 days" in {
      addressBookService.daysDifferenceBetween(olderPerson = Name("Bill"), youngerPerson = Name("Paul")) shouldBe 2862
    }

  }

}
