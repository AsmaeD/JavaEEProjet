package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class errorHandler
 */
@WebServlet("/AppExceptionHandler")
public class errorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//Analyse de l'exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if(servletName==null) {
			servletName="Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if(requestUri==null) {
			requestUri="Unknown";
		}
		
		//Effecuer une réponse adaptative à l'erreur rencontrée
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Exception/Détails Erreurs</title></head><body>");
		if(statusCode != 500) {
			out.write("<h3>Détails de l'erreur</h3>");
			out.write("<strong>Code du status</strong>:"+statusCode+"<br>");
			out.write("<strong>Requête URI</strong>:"+requestUri);
		}else {
			out.write("<h3>Détails de l'erreur</h3>");
			out.write("<ul><li>Nom de la page:"+servletName+"</li>");
			out.write("<li>Nom de l'exception:"+throwable.getClass().getName()+"</li>");
			out.write("<li>Requête URI:"+requestUri+"</li>");
			out.write("Message de l'exception:"+throwable.getMessage()+"</li>");
			out.write("</ul>");
		}
		
		out.write("<br><br>");
		out.write("<a href=\"index.html\">Accueil</a>");
		out.write("</body></html>");
	}

}
