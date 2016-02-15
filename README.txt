===================
Mongo Database connection
===================

Main page
https://www.mongodb.org/

Getting started
https://docs.mongodb.org/getting-started/shell/

Manual
https://docs.mongodb.org/manual/

MongoDB Java Driver
https://github.com/mongodb/mongo-java-driver/releases?jmp=docs
http://mongodb.github.io/mongo-java-driver/
http://mongodb.github.io/mongo-java-driver/3.2/driver/getting-started/quick-tour/

Example:
mongod.exe --dbpath D:\_MONGODB_DATA_\data
mongod -f C:\MONGO_CONF\mongod.conf


mongod.conf
storage:
    dbPath: D:\_MONGODB_DATA_\data


This is only example:
mongoimport --db test --collection restaurants --drop --file D:\_MONGO_EX_\primer-dataset.json

