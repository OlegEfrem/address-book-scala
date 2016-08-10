package com.gumtree.addressbook

import com.gumtree.addressbook.implementation.DefaultAddressBookService
import com.gumtree.addressbook.model.{Gender, Name, Person}
import com.gumtree.addressbook.repo.file.{DataParser, FileSystemRepo}
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.SpanSugar._

class PerformanceTest extends BaseSpec with TimeLimitedTests {
  val timeLimit = 60 seconds
  val timeout = timeLimit.millisPart / 1000
  val numOfEntries = 10000000
  val dataParser = new DataParser {
    override lazy val readPersons: Stream[Person] = (1 to numOfEntries).map(e => newPerson(name = Name(s"FirstName$e"), minusDays = e)).toStream
  }
  val repo = new FileSystemRepo(dataParser)
  val addressBookService: AddressBookService = new DefaultAddressBookService(repo)

  "countBy(Gender.Male)" should {

    s"count $numOfEntries entries in less than $timeout seconds" in {
      val timeStart = System.currentTimeMillis()
      addressBookService.countBy(Gender.Male) shouldBe numOfEntries
      val timeEnd = System.currentTimeMillis()
      val result = s"Count result on $numOfEntries entries: ${(timeEnd - timeStart).toDouble / 1000}"
      println(result)
    }

  }

  "oldestPerson" should {

    s"find the oldest person from $numOfEntries entries in less than $timeout seconds" in {
      val timeStart = System.currentTimeMillis()
      addressBookService.oldestPerson().head.name shouldBe Name(s"FirstName$numOfEntries")
      val timeEnd = System.currentTimeMillis()
      val result = s"Oldest person result on $numOfEntries entries: ${(timeEnd - timeStart).toDouble / 1000}"
      println(result)
    }

  }

  "daysDifferenceBetween(Bill, Paul)" should {

    s"get the age difference between the oldest and youngest person from $numOfEntries entries in less than $timeout seconds" in {
      val timeStart = System.currentTimeMillis()
      addressBookService.daysDifferenceBetween(olderPerson = Name(s"FirstName$numOfEntries"), youngerPerson = Name("FirstName1")) shouldBe numOfEntries - 1
      val timeEnd = System.currentTimeMillis()
      val result = s"Age difference result on $numOfEntries entries: ${(timeEnd - timeStart).toDouble / 1000}"
      println(result)
    }

  }
}
