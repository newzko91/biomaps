package br.com.unip.servlets.pessoas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.unip.dao.OperGer;
import br.com.unip.dao.OperBD;
import br.com.unip.models.Pessoa;

@WebServlet(urlPatterns = { "/pessoas" })
public class RetrievePessoasServlet extends HttpServlet {
	private static final long serialVersionUID = 864654680631969782L;

    public RetrievePessoasServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = OperGer.getStoredConnection(request);
 
        String errorString = null;
        List<Pessoa> list = null;
        try {
            list = OperBD.queryPessoa(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("pessoaView", list);
         
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsps/pessoas/pessoasView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
