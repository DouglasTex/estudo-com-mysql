package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletAtrativosAlteracao")
public class ServletAtrativosAlteracao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAtrativosAlteracao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head> <tittle> Alteração de Atrativos </tittle></head>");
		out.println("<body>");
		AtrativoDAO atrativos = new AtrativoDAO();
		atrativos.bd.getConnection();
		
		try {
			atrativos.atrativo.setCodigo(request.getParameter("id"));
			atrativos.atrativo.setCidade(request.getParameter("p_cidade"));
			atrativos.atrativo.setNome(request.getParameter("p_nome"));
			atrativos.atrativo.setEstado(request.getParameter("p_estado"));
			atrativos.atrativo.setDescricao(request.getParameter("p_descricao"));
			String retorno = atrativos.atualizar(AtrativoDAO.ALTERACAO);
			
			atrativos.bd.close();
			out.println("<b>" + retorno + "</b>");
		} catch (Exception e) {
			out.println("<b>Falha: " + e.toString() + "</b>");
		}
		
		out.println("<a href='localizar.html'><input type='button' value='Voltar'></a>");
		out.println("</body></html>");
	}

}
