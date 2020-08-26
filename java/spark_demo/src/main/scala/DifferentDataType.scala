import org.apache.spark
import org.apache.spark.sql.SparkSession

/**
 * @Project java
 * @Author Ravooo
 * @Since 2020/8/12 21:18
 * @Description DifferentDataType
 */
object DifferentDataType {

    def main(args: Array[String]): Unit = {
        val app = SparkSession.active
        val df = app.read.format("csv")
            .option("header", "true")
            .option("inferSchema", "true")
            .load("Documents/Data/tm_region.csv")
        df.printSchema()
    }
}
