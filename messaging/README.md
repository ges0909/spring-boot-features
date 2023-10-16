# Spring Boot Messaging

- [Working with ActiveMQ using Spring Boot](https://yewtu.be/watch?v=rupsZ27Ncvo)

Download [activemq](https://activemq.apache.org/components/classic/download/), unzip and install.

```shell
activemq start
```

```shell
start http://localhost:8161
```

```shell
curl --data "{\"source\": \"Another external system\", \"message\": \"Transactions started.\"}" --header "Content-Type: application/json" --request POST http://localhost:8080/publishMessage
```
