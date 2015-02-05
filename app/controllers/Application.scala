package controllers

import play.api.mvc._
import java.io.File
import scala.io.Source
import org.apache.log4j.Logger
import org.apache.log4j.Level
<<<<<<< HEAD
=======
import play.api.mvc._
>>>>>>> origin/master
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}
import util.Recommender
import model.AllRatedProducts._

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._
import java.io.InputStream
import java.net.URI
import java.net.URLDecoder

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object Application extends Controller {  
   
   val sc = new SparkContext("spark://192.168.1.9:7077", "recommender")

  def index() = Action {     
    Redirect("/ratings")
  }


def ratings() = Action {  
       //  val ratings_initial = sc.textFile("hdfs://localhost:8097/analytics/us3.csv").map { line =>
      val ratings = sc.textFile("hdfs://localhost:8097/ss/retail1.csv").map { line =>
      val fields = line.split(",")
      (fields(6).toLong % 10, Rating(fields(1).toInt, fields(4).toInt, fields(5).toDouble))
     
    }
    
   val numRatings = ratings.count() 

    println("Got " + numRatings)

     println("Ratings:" + ratings)

   Ok(views.html.ratings("hello"))  
      
  }


}
