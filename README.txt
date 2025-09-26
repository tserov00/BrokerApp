## Инструкция по запуску

1) Склонируйте репозиторий:  
   git clone https://github.com/tserov00/BrokerApp.git 

2) Восстановите базу данных PostgreSQL из дампа:  
   cd BrokerApp
   createdb broker_app 
   psql broker_app < "db_dump.sql"

3) Запустите приложение

## Инструкция для работы FinnhubApi (необязательно)

1) Зарегистрируйтесь на сайте https://finnhub.io/ и получите api токен

2) Вставьте свой токен в application.properties в поле finnhub.api.key

