package com.gumtree.addressbook.implementation

import com.gumtree.addressbook.AddressBookException

class DataNotFoundException(message: String, cause: Throwable) extends AddressBookException(message, cause) {
  def this(message: String) = this(message, null)

  def this(cause: Throwable) = this(null, cause)
}
