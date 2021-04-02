This project is hosted on heroku

https://tinyurl-frontend.herokuapp.com/

```Spring boot App with Basic HTML for frontend```
Using H2 in memory database

To use this project follow following steps: 

1) clone repo
2) open this project as spring boot app in ide
3) all seetings are default, so just run your app as spring boot application

API - 
1) POST - /generate
input Type: JSON object
{
  "url" : "https://www.google.com"
}

Response will have an aliase to access url

2) GET - /
input path - /alias

