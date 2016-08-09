package com.gumtree.addressbook

import scala.io.Source

trait IntegrationSpec extends BaseSpec {
  val source: Source = localFileSystemSource()
}
