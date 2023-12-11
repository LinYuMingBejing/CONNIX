# Line Bot Message API

## Set Up Line Bot configuration

* **How to get channel access token and channel secret?**
  
  1. Create the Line Provider. (https://developers.line.biz/console/) <br>
  Access Token
  ![](https://i.imgur.com/M1Z6NCQ.png)
  Channel Secret
  ![](https://i.imgur.com/PhyTCyy.png)

* **Set environment variables**
``` 
export LINE_BOT_CHANNEL_TOKEN=YOUR_LINE_BOT_CHANNEL_TOKEN
export LINE_BOT_CHANNEL_SECRET=YOUR_LINE_BOT_CHANNEL_SECRET
```
* **Generate a https endpoint with ngrok**
```
ngrok http 8081
```
  ![](https://i.imgur.com/F3ood1d.png)
* **Replace webhook settings with the above result**
  ![](https://i.imgur.com/kfWfQlJ.png)

* **Run MongoDB with Docker**
```
docker run --name mongo -d -p 27017:27017 --rm mongo
```

* **NOW, You can start Spring Boot project**


## How to test Line Bot Related API?
**LINE Bot Account(Scan following QR Code)** <br>
![](https://i.imgur.com/rY82ehX.png)<br>
**Chat with LINE BOT[If you want to reply yourself, you can use the following response API]**
![](https://i.imgur.com/PKAjOyW.png)
<br>
* **LINE Bot Reply API:**
``` 
curl -X POST http://localhot:8080/message  \
     -H "Content-Type: application/json" \
     -d '{"userId":"userId", "text":"Hello"}'
```
* **Response**
```
{"statusCode":200,"data":null}
```
* **User Query API:**
```
curl -X POST http://localhot:8080/message/U782sdsdsdea59f9f9f7ecde11cb1a010e
```
* **Response**
```
{"statusCode":200,"data":["Amy","Eric","Andy","Jolin"]}
```

## TODO
* Get reply token from API due to expiration policy
* Support message response with multi-line