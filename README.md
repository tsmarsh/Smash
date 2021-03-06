# Smash

The sister project of [Clobber](https://github.com/tsmarsh/Clobber).

It was bought to my attention that it was really hard to see how you could take Clobber and tune it to your own purposes.

As Clobber was originally designed just to prove that PEST was a viable architecture, that was more than true.

So I have turned Clobber into a jar and wrapped Smash around it.

With minimal code Smash can:

* Define its database using a persistence.xml
* Define its routes using guice servlet mappings (see RoutesModule)
* Define how it should create links (ServerModule)
* Define how it should get settings (Main)

Could it be cleaner? Yes... I'm working on it. 

## Building:

Until I can publish Clobber on Maven Central, you need to download and install it.

```
git clone https://github.com/tsmarsh/Clobber.git
cd Clobber
mvn install
```

after that

```
mvn package
mvn exec:java -Dconfig=test
```

should get you working server.

## TODO:

* Performance tests
* End to End tests

