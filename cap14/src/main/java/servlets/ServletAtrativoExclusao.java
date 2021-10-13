package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletAtrativoExclusao")
public class ServletAtrativoExclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAtrativoExclusao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head> <tittle> Exclusão de Atrativos </tittle></head>");
		out.println("<body>");
		AtrativoDAO atrativos = new AtrativoDAO();
		atrativos.bd.getConnection();
		
		try {
			atrativos.atrativo.setCodigo(request.getParameter("id"));
			String retorno = atrativos.atualizar(AtrativoDAO.EXCLUSAO);
			
			atrativos.bd.close();
			out.println("<b>Falha: " + retorno + "</b>");
		} catch (Exception e) {
			out.println("<b>Falha: " + e.toString() + "</b>");
		}
		
		out.println("</body></html>");
	}

}
