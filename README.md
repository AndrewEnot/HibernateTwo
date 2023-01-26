1. Создать таблицы (БД в докере, миграция в Flyway):

product
- int id
- string name
- string description
- double price

order_item
- int id
- int orderId
- Product product (ManyToOne) //скоріш за все OneToOne//
- int count

order
- int id
- int clientId
- List<OrderItem> orderItems (OneToMany)

address
- int id
- int client_id
- string country
- string city
- string street
- string house

client
- int id
- string name
- string email
- string phone
- Address address (OneToOne)
- List<Orders> ordersHistory (OneToMany)

2. Создать Entity для всех таблиц (связать соответственно)

3. Создать сервисный слой с функционалом:
- Добавить новый продукт +
- Добавить нового пользователя + 
- Изменить адресс доставки пользователя +
- Создать заказ +
- Получить полную информацию о клиенте и всех его заказах (Отдельная ДТО)
- Получить ТОЛЬКО информацию о клиенте (контактные данные и аддрес) (Отдельная ДТО)
- Получить информацию о пользователе и список ID его заказов (без продуктов и т д) (Отдельная ДТО)

4. Проверить все консольным способом (пока без REST API)