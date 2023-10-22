package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Model model = Model.getInstance();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);

        int id = 0;
        User user = null;

        try {
            id = jsonObject.get("id").getAsInt();
            String userName = jsonObject.get("name").getAsString();
            String userSurname = jsonObject.get("surname").getAsString();
            double userSalary = jsonObject.get("salary").getAsDouble();
            user = new User(userName, userSurname, userSalary);
        } catch (Exception e) {
            response.sendError(400, "Invalid Json!");
        }
        assert user != null;
        if (!user.validateFields() || !model.validateId(id))
            response.sendError(400, "Invalid id or User");
        else {
            model.Put(user, id);
            PrintWriter pw = response.getWriter();
            pw.print(gson.toJson("User with id =" + id + ", was edit!"));
        }
    }
}
