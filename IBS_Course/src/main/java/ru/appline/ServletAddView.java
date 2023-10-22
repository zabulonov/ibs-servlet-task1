package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/addView")
public class ServletAddView extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User(name, surname, salary);

        if (!user.validateFields())
            response.sendError(400, "Invalid User fields");
        else {
            model.Add(user, counter.getAndIncrement());
        }


        pw.print("<html>" +
                "<h3> Пользователь " + name + " " + surname + " с зарплатой" + salary +" успешно добавлен!" + "</h3>" +
                "<a href=\'addUser.html\'> Создать нового пользователя</a> </br>" +
                 "<a href=\'index.jsp\'>Домой</a>" +
                "</html>");
    }
}
