package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.AddressBookException

class DataParserException(message: String, cause: Throwable) extends AddressBookException(message, cause) {
  def this(message: String) = this(message, null)

  def this(cause: Throwable) = this(null, cause)
}
