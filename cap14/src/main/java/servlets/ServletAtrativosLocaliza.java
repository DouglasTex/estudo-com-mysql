package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletAtrativosLocaliza")
public class ServletAtrativosLocaliza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletAtrativosLocaliza() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<main>");
		out.println("<head> <tittle> Busca de Atrativos </tittle>"
				+ "<link rel='stylesheet' href='estilos.css'></head>");
		out.println("<body class='excluir'> <form action='ServletAtrativosAlteracao' method='get'>");
		
		out.println("<table border='1' cellspacing='0'>");
		AtrativoDAO atrativos = new AtrativoDAO();
		atrativos.bd.getConnection();
		
		try {
			atrativos.atrativo.setCodigo(request.getParameter("id"));
			
			if (atrativos.localizar()) {
				out.println("<input type='hidden' name='id' value='"+atrativos.atrativo.getCodigo()+"'>");
				out.println("<tr><td bgcolor='beige'>Código: </td><td>"+atrativos.atrativo.getCodigo()+"</td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Nome: </td><td><input type='text' name='p_nome' size='30' value='"+atrativos.atrativo.getNome()+"'></td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Cidade: </td><td><input type='text' name='p_cidade' size='30' value='"+atrativos.atrativo.getCidade()+"'></td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Estado: </td><td><input type='text' name='p_estado' size='2' value='"+atrativos.atrativo.getEstado()+"'></td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Descrição: </td><td><input type='text' name='p_descricao' size='50' value='"+atrativos.atrativo.getDescricao()+"'></td></tr><br>");
				out.println("</table><br>");
				out.println("<input type='submit' value='Salvar'>");
			} else {
				out.println("<b>Atrativo não encontrado</b>"
						+ "<br><br>");
			}
			
			atrativos.bd.close();
			
		} catch (Exception e) {
			out.println("<b>" + e.toString() + "</b>");
		}
		
		out.println("<a href='localizar.html'><input type='button' value='Nova Busca'</a>");
		out.println("</form></main></body></html>");
		
	}
	
}
