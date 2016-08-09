package com.gumtree.addressbook.model

import com.gumtree.addressbook.{AddressBookException, UnitSpec}

class DateOfBirthTest extends UnitSpec {

  "toString/from" should {

    "return 28/11/1925 for an object created with 28/11/25" in {
        DateOfBirth.from("28/11/25").toString shouldBe "28/11/1925"
    }

    "return 03/04/2024 for an object created with 03/04/24" in {
      DateOfBirth.from("03/04/24").toString shouldBe "03/04/2024"
    }

    "return 03/04/1999 for an object created with 03/04/99" in {
      DateOfBirth.from("03/04/99").toString shouldBe "03/04/1999"
    }

    "return 03/04/1999 for an object created with 03/04/1999" in {
      DateOfBirth.from("03/04/1999").toString shouldBe "03/04/1999"
    }

  }

  "from" should {

    "throw an exception for the input 03/04/1999" in {
      the[AddressBookException] thrownBy {
        DateOfBirth.from("32/04/99")
      } should have message """Text: 32/04/99 does not match the required format: dd/MM/yy or has illegal values: Cannot parse "32/04/99": Value 32 for dayOfMonth must be in the range [1,30]"""
    }

  }
}
