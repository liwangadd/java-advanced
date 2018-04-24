package cn.windylee.io

import scala.io.Source

object NetworkIO {

  def main(args: Array[String]):Unit={
    println(Source.fromURL("http://www.baidu.com").mkString)
  }

}
