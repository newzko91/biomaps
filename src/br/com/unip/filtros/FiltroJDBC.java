package br.com.unip.filtros;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.unip.conexao.CCIConGeral;
import br.com.unip.dao.OperGer;

@WebFilter(filterName = "JDBCFilter", urlPatterns = { "/*" })
public class FiltroJDBC implements Filter{

	public FiltroJDBC() {
		
	}
	
	@Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
 
    @Override
    public void destroy() {
 
    }
 
    // Verifica se a requisição é para um Servlet
    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("Filtro JDBC | Banco de Dados");

        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if (pathInfo != null) {
            
            urlPattern = servletPath + "/*";
        }
 
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
 

        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        
        if (this.needJDBC(req)) {
 
            System.out.println("Acessando página: " + req.getServletPath());
 
            Connection conn = null;
            try {
               
                conn = CCIConGeral.getConnection();
               
                conn.setAutoCommit(false);
 
                OperGer.storeConnection(request, conn);
           
                chain.doFilter(request, response);
       
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                CCIConGeral.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
            	CCIConGeral.closeQuietly(conn);
            }
        }
        
        else {
            
            chain.doFilter(request, response);
        	
        }
    }
}
