package ville.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditerVille
 */
@WebServlet("/EditerVille")
public class EditerVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditerVille() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ville = request.getParameter("idVille");
		
		int debutDepart = 0;
		int finDepart = 0;
		debutDepart = ville.indexOf("codeCommuneInsee=");
		finDepart = ville.indexOf(", nomCommune=");
		
		if(debutDepart < 0) {
			debutDepart = -2;
		}
		
		String codeCommuneInsee = ville.substring(debutDepart + 17, finDepart);
		
		debutDepart = ville.indexOf("nomCommune=");
		finDepart = ville.indexOf(", codePostal=");
		
		String nomCommune = ville.substring(debutDepart + 11, finDepart);
		
		debutDepart = ville.indexOf("codePostal=");
		finDepart = ville.indexOf(", libelleAcheminement=");
		
		String codePostal = ville.substring(debutDepart + 11, finDepart);
		
		debutDepart = ville.indexOf("libelleAcheminement=");
		finDepart = ville.indexOf(", ligne5=");
		
		String libelleAcheminement = ville.substring(debutDepart + 20, finDepart);
		
		debutDepart = ville.indexOf("ligne5=");
		finDepart = ville.indexOf(", lattitude=");
		
		String ligne5 = ville.substring(debutDepart + 7, finDepart);
		
		debutDepart = ville.indexOf("lattitude=");
		finDepart = ville.indexOf(", longitude=");
		
		String latitude = ville.substring(debutDepart + 10, finDepart);
		
		debutDepart = ville.indexOf("longitude=");
		finDepart = ville.indexOf("]");
		
		String longitude = ville.substring(debutDepart + 10, finDepart);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("codeCommuneInsee", codeCommuneInsee);
		session.setAttribute("nomCommune", nomCommune);
		session.setAttribute("codePostal", codePostal);
		session.setAttribute("libelleAcheminement", libelleAcheminement);
		session.setAttribute("ligne5", ligne5);
		session.setAttribute("latitude", latitude);
		session.setAttribute("longitude", longitude);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editerVille.jsp").forward(request, response);
	}

}
