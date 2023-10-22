# ibs-servlet-task1

## Task
Первая часть ДЗ (дополнение к видео):

- [X] 1. Изменить проект таким образом, чтобы обращение ко всем сервлетам было через json, и отвечали они тем же форматом

- [X] 2. Реализовать сервлет с методом doDelete(), который будет удалять записи о пользователях по id

- [X] 3. Реализовать сервлет с методом doPut(), который будет обновлять (изменять) записи о пользователях (необходимо передавать id, имя, фамилию и зарплату пользователей).

- [X] 4. Страница по добавлению пользователей в браузере http://localhost:8080/*/addUser.html должна работать корректно.



Вторая часть ДЗ:

- [X] Написать сервлет-калькулятор, который будет принимать в формате json 3 параметра: число а, b и арифметическое действие, например:

{
"a": 10,
"b": 5,
"math": "*"
}

В качестве ответа сервлет должен возвращать json с выполненным арифметическим действием, т.е. в нашем случае:

{
"result": 50
}

## Description
Я решил для наглядности сделать каждый запрос отдельным сервлетом, а не помещать все запросы в один файл.

| Servlet Name | Тип запроса | Адрес | Что делает |
|----------------|:---------:|:---------:|----------------:|
| ServletAdd | POST | http://localhost:8080/.../add | добавляет пользователя через json |
| ServletAddView | POST |  http://localhost:8080/.../addView | добавляет пользователя через addUser.html |
| ServletDelete | DELETE | http://localhost:8080/.../delete | удаляет пользователя через json |
| ServletPut | PUT | http://localhost:8080/.../put | изменяет пользователя через json |
| ServletListView | GET | http://localhost:8080/.../getView | отображает пользователей через html |
| ServletList | GET | http://localhost:8080/.../get | отбражает пользователей через json |
| ServletCalc| POST | http://localhost:8080/.../calc | Калькулятор через json |

### Вопрос: Нормально ли делать так?(см Коментарии в коде)

```Java
        // Блок из видео уроков. Думаю, важно отделить эти 2 блока try
        // Так как один отвечает за исключение потока, а второй за правильность запроса
        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }
        }
        catch (Exception e){
            System.out.println("Error!");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        try {
            double a = jobj.get("a").getAsDouble();
            double b = jobj.get("b").getAsDouble();
            char op = jobj.get("operation").getAsCharacter();

            Calculation calculator = new Calculation(a, b, op);

            double ans = calculator.Calc();

            PrintWriter pw = response.getWriter();
            pw.print(gson.toJson(ans));
        } catch (Exception e) {
            // В ответ передаем исключение из Calculation, либо исключение при парсинге.
            response.sendError(400, e.getMessage());
        }
```

## Видео работы
https://youtu.be/cbbS4M1kBYk

