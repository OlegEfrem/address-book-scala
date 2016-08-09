package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.helpers.TestData
import com.gumtree.addressbook.model.Gender
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}

class FileSystemRepoTest extends WordSpec with Matchers with MockitoSugar with TestData with BeforeAndAfterEach {
  val dataParser = mock[DataParser]

  def fileSystemRepo = new FileSystemRepo(dataParser)

  "findBy(Gender)" should {

    "return 0 persons if no males are in the repo" in {
      when(dataParser.readPersons).thenReturn(Stream())
      fileSystemRepo.findBy(Gender.Male) shouldBe empty
    }

    "return 1 person if 1 male is in the repo" in {
      val expected = Stream(newPerson)
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

    "return 2 persons if there are 2 males in the repo" in {
      val expected = Stream(newPerson, newPerson)
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

  }
}
