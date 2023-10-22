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

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        StringBuffer jb = new StringBuffer();
        String line;

        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }
        }
        catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        User user = null;

        try {
            String name = jobj.get("name").getAsString();
            String surname = jobj.get("name").getAsString();
            double salary = jobj.get("salary").getAsDouble();
            user = new User(name, surname, salary);
        }
        catch (Exception e) {
            response.sendError(400, "Invalid Json!");
        }

        assert user != null;
        if (!user.validateFields())
            response.sendError(400, "Invalid User fields");
        else {
            model.Add(user, counter.getAndIncrement());
            PrintWriter pw = response.getWriter();
            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
