package ville.servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResultatCalcul
 */
@WebServlet("/ResultatCalcul")
public class ResultatCalcul extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatCalcul() {
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
		String villeDepart = request.getParameter("villeDepart");
		String villeArrivee = request.getParameter("villeArrivee");
		
		 /*Récupération des latitudes et longitudes */
		
		int debutDepart = 0;
		int finDepart = 0;
		int finDepartString = 0;
		int finArriveeString = 0;
		debutDepart = villeDepart.indexOf("lattitude=");
		finDepart = villeDepart.indexOf(", longitude=");
		finDepartString = villeDepart.indexOf("]");
		finArriveeString = villeArrivee.indexOf("]");
		
		if(debutDepart < 0) {
			debutDepart = -2;
		}
		
		double villeDepartLatitude = Double.parseDouble(villeDepart.substring(debutDepart + 10, finDepart));
		double villeDepartLongitude = Double.parseDouble(villeDepart.substring(finDepart + 12, finDepartString));
		
		int debutArrivee = 0;
		int finArrivee = 0;
		debutArrivee = villeArrivee.indexOf("lattitude=");
		finArrivee = villeArrivee.indexOf(", longitude=");
		
		if(debutDepart < 0) {
			debutDepart = -2;
		}
		
		double villeArriveeLatitude = Double.parseDouble(villeArrivee.substring(debutArrivee + 10, finArrivee));
		double villeArriveeLongitude = Double.parseDouble(villeArrivee.substring(finArrivee + 12, finArriveeString));
		
		
		debutDepart = villeDepart.indexOf("nomCommune=");
		finDepart = villeDepart.indexOf(", codePostal=");
		String nomCommuneDepart = villeDepart.substring(debutDepart + 11, finDepart);
		
		debutDepart = villeArrivee.indexOf("nomCommune=");
		finDepart = villeArrivee.indexOf(", codePostal=");
		String nomCommuneArrivee = villeArrivee.substring(debutDepart + 11, finDepart);
		
		System.out.println(villeDepart);
		System.out.println(villeArrivee);
		System.out.println(villeDepartLatitude);
		System.out.println(villeDepartLongitude);
		System.out.println(villeArriveeLatitude);
		System.out.println(villeArriveeLongitude);
		System.out.println(nomCommuneDepart);
		System.out.println(nomCommuneArrivee);
		
		double distance = Math.sqrt(Math.pow(Math.abs(villeArriveeLatitude - villeDepartLatitude), 2) + 
				Math.pow(Math.abs(villeArriveeLongitude - villeDepartLongitude), 2))*111.16;
		
		double distanceB = 6372*(Math.acos(Math.sin(villeDepartLatitude*Math.PI/180)*Math.sin(villeArriveeLatitude*Math.PI/180) 
				+ Math.cos(villeDepartLatitude*Math.PI/180)*Math.cos(villeArriveeLatitude*Math.PI/180)*
				Math.cos((villeDepartLongitude - villeArriveeLongitude)*Math.PI/180)));
		
		System.out.println(distance);
		System.out.println(distanceB);
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("nomCommuneDepart", nomCommuneDepart);
		session.setAttribute("nomCommuneArrivee", nomCommuneArrivee);
		session.setAttribute("distance", f.format(distanceB));
		this.getServletContext().getRequestDispatcher("/WEB-INF/resultatCalcul.jsp").forward(request, response);
	}

}
