package ufrn.br.aulaweb2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AulaController {

    @Autowired
    ServletContext servletContext;

    @RequestMapping("/redirecionar")
    public void doRedirecionamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/parte1");
    }

    @RequestMapping("/parte2")
    public void doParte2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mensagem = (String) request.getAttribute("mensagem");
        response.getWriter().println("parte 2 do processamento");
        response.getWriter().println(mensagem);
        //response.sendRedirect("http://localhost:8080/redirecionar");
    }

    @RequestMapping("/parte1")
    public void doEncaminhar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensagem", "parte 1 do processamento");
        RequestDispatcher encaminhar = request.getRequestDispatcher("/parte2");
        encaminhar.forward(request, response);
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var aula = request.getParameter("aula");

        String admin = servletContext.getInitParameter("admin");
        String teste = servletContext.getInitParameter("aula");



        response.setContentType("text/html");
        response.setStatus(210);
        response.getWriter().println("<html><body>Hello world "+ aula + " admin:" +admin+teste+ "</body></html>");
    }

    @RequestMapping(value = "/cadastra", method = RequestMethod.GET)
    public void doCadastra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var nome = request.getParameter("nome");
        var idade = request.getParameter("idade");
        var prefs = request.getParameterValues("prefs");

        var writer = response.getWriter();
        writer.println(nome);
        writer.println(idade);
        for (var p : prefs)
            writer.println(p);
    }

    @RequestMapping(value = "/cadastraform", method = RequestMethod.POST)
    public void doCadastraForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var nome = request.getParameter("nome");
        var idade = request.getParameter("idade");
        var prefs = request.getParameterValues("prefs");

        var writer = response.getWriter();
        writer.println(nome);
        writer.println(idade);
        for (var p : prefs)
            writer.println(p);
    }
}
