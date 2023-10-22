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
import java.util.Map;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
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

        try {
            id = jsonObject.get("id").getAsInt();
        } catch (Exception e) {
            response.sendError(400, "Invalid Json!");
        }

        if (!model.validateId(id)) {
            response.sendError(400, "Invalid id.");
        }
        else{
            PrintWriter pw = response.getWriter();
            User user = model.getFromList().get(id);
            try {
                model.Delete(id, user);
            } catch (Exception e) {
                response.sendError(400, e.getMessage());
            }
            pw.print(gson.toJson("User with id=" + id + ", was deleted"));
        }
    }
}
