package com.akikanellis.skyspark.scala.api.algorithms.bnl

import com.akikanellis.skyspark.scala.test_utils.UnitSpec

class FlagTest extends UnitSpec {
  var flag: Flag = _

  before {
    flag = Flag(false, true, false, true) /* 0101 */
  }

  "A smaller than 0 index" should "throw IndexOutOfBoundsException" in {
    an[IndexOutOfBoundsException] should be thrownBy flag.bit(-1)
  }

  "A bigger than size index" should "throw IndexOutOfBoundsException" in {
    an[IndexOutOfBoundsException] should be thrownBy flag.bit(4)
  }

  "A 0 index" should "return the first value" in {
    flag.bit(0) shouldBe false
  }

  "A 3 index" should "return the last value" in {
    flag.bit(3) shouldBe true
  }
}
