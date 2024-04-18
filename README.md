# Quotely
Quotely is an experemental Java project using gradle, picocli, and docker. 

## Building

```
docker build . -t quotely 
docker run quotely -l russian
```

## Development
```
gradle build
gradle test
```