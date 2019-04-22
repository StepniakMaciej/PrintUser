package usersengine.servlet;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import usersengine.freemarker.TemplateProvider;
import usersengine.user.User;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet("/infoShareAcademy")
public class UserServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Enumeration enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String param = (String) enumeration.nextElement();
            String value = req.getParameter(param);
            resp.getWriter().println(param + "= " + value);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = "Maciej";
        String surname = "Stepniak";
        String team = "jjdd6-errorzy";

        LocalDateTime localDateTime = LocalDateTime.now();

        Template template = templateProvider.getTemplate(getServletContext(), "print-user.ftlh");
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("surname", surname);
        model.put("team", team);
        model.put("localDateTime", localDateTime);
        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}