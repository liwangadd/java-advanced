package cn.windylee.sql

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

object UserDefinedUntypedAggregation{

  object MyAverage extends UserDefinedAggregateFunction{
    def inputSchema: StructType = StructType(StructField("inputColumn", LongType) :: Nil)

    def bufferSchema:StructType = {StructType(StructField("sum", LongType)::StructField("count", LongType) :: Nil)}

    def dataType:DataType = DoubleType

    def deterministic :Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L
      buffer(1) = 0L
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      if(!input.isNullAt(0)){
        buffer(0) = buffer.getLong(0) + input.getLong(0)
        buffer(1) = buffer.getLong(1) + 1
      }
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = buffer.getLong(0).toDouble / buffer.getLong(1)
  }

  def main(args:Array[String]):Unit={
    val spark = SparkSession.builder()
      .appName("Spark SQL user-defined DataFrame aggregation example")
      .getOrCreate()
    spark.udf.register("myAverage", MyAverage)

    val df = spark.read.json("file:///E:/Data/employees.json")
    df.createOrReplaceTempView("employees")
    df.show()

    val result = spark.sql("SELECT myAverage(salary) as average_salary FROM employees")
    result.show()

    spark.stop()
  }

}