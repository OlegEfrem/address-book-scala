package com.gumtree.addressbook

import com.gumtree.addressbook.implementation.DefaultAddressBookService
import com.gumtree.addressbook.model.{Gender, Name}
import com.gumtree.addressbook.repo.file.{CsvDataParser, FileSystemRepo}
import scala.io.Source

class IntegrationTest extends BaseSpec {
  val source: Source = localFileSystemSource()
  val addressBookService: AddressBookService = new DefaultAddressBookService(new FileSystemRepo(new CsvDataParser(source)))

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
