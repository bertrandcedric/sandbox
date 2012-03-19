package fr.bertrand.cedric;

import static org.fest.assertions.Assertions.assertThat;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoTest extends AbstractMongoTest {

	@Test
	public void test() throws UnknownHostException, MongoException {
		// connect to mongoDB, ip and port number
		Mongo mongo = new Mongo("localhost", 27017);

		// get database from MongoDB,
		// if database doesn't exists, mongoDB will create it automatically
		mongo.dropDatabase("yourdb");
		DB db = mongo.getDB("yourdb");

		// Get collection from MongoDB, database named "yourDB"
		// if collection doesn't exists, mongoDB will create it automatically
		DBCollection collection = db.getCollection("yourCollection");

		// create a document to store key and value
		BasicDBObject document = new BasicDBObject();
		document.put("id", 1001);
		document.put("msg", "hello world mongoDB in Java");

		// save it into collection named "yourCollection"
		collection.insert(document);

		// search query
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", 1001);

		// query it
		DBCursor cursor = collection.find(searchQuery);

		// loop over the cursor and display the retrieved result
		while (cursor.hasNext()) {
			DBObject next = cursor.next();
			assertThat(next).isNotNull();
			assertThat(next.get("_id")).isNotNull();
			assertThat(next.get("id")).isEqualTo(1001);
			assertThat(next.get("msg")).isEqualTo("hello world mongoDB in Java");
		}
	}
}
