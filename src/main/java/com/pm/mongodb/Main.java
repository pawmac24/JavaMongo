package com.pm.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
	}

	private void run() {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("test");

		MongoCollection<Document> collection = database.getCollection("mongopmcol");

		//insert one document
		Document doc = new Document("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);

		//insert many documents
		List<Document> documents = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			documents.add(new Document("i", i));
		}
		collection.insertMany(documents);

		LOG.info("(1) " + collection.count());

		//find the first document in a collection
		Document myDoc = collection.find().first();
		LOG.info("(2) " + myDoc.toJson());

		//find all documents in a collection
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				LOG.info("(3) " + cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		DeleteResult deleteResult1 = collection.deleteMany(eq("name", "MongoDB"));
		LOG.info("(4) " + deleteResult1.getDeletedCount());
		//
		DeleteResult deleteResult = collection.deleteMany(lt("i", 100));
		LOG.info("(5) " + deleteResult.getDeletedCount());

		//find all documents in a collection
		MongoCursor<Document> cursor2 = collection.find().iterator();
		try {
			while (cursor2.hasNext()) {
				LOG.info("(6) " + cursor2.next().toJson());
			}
		} finally {
			cursor2.close();
		}


		mongoClient.close();
	}
}
