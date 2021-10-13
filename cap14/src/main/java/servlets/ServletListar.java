package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletListar")
public class ServletListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		AtrativoDAO atrativos = new AtrativoDAO();
		
		out.println("<html>");
		out.println("<head> <tittle> Lista de Atrativos </tittle></head>");
		out.println("<body> <form action='ServletAtrativosAlteracao' method='get>");
		out.println("<table border='0' cellspacing='0'>");
		atrativos.bd.getConnection();
		
		ArrayList<Atrativo> lista = atrativos.listar();
		
		try {
			
			for (Atrativo atrativo : lista) {
				
				//TIRAR DEPOIS
				//System.out.println(lista.get(3).getCidade());
				
				out.println("<form action=\'http://localhost:8080/cap14/ServletAtrativosLocaliza\'>");
				out.println("<input type='hidden' name='id' value='"+atrativo.getCodigo()+"'>");
				out.println("<tr><td bgcolor='beige'>Código: </td><td>"+atrativo.getCodigo()+"</td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Nome: </td><td>"+atrativo.getNome()+"</td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Cidade: </td><td>"+atrativo.getCidade()+"</td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Estado: </td><td>"+atrativo.getEstado()+"</td></tr><br>");
				out.println("<tr><td bgcolor='beige'>Descrição: </td><td>"+atrativo.getDescricao()+"</td></tr><br>");
				out.println("</table><br>");
				out.println("<input type='submit' value='Editar'>");
				out.println("</form>");
			}
			
			atrativos.bd.close();
			
		} catch (Exception e) {
			out.println("<b>Falha: " + e.toString() + "</b>");
		}
		
		out.println("<a href='localizar.html'><input type='button' value='Nova Busca'</a>");
		out.println("</form></body></html>");
	}

}
