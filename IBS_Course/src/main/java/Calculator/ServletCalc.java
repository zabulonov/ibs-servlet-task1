package Calculator;

import Calculator.Logic.Calculation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = new StringBuffer();
        String line;

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
            response.sendError(400, e.getMessage());
        }
    }
}
