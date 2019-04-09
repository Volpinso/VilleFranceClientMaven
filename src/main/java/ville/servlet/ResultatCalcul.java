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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");

        /* Récupération des latitudes et longitudes */

        int debutDepart = villeDepart.indexOf("lattitude=");
        int finDepart = villeDepart.indexOf(", longitude=");
        int finDepartString = villeDepart.indexOf("]");
        int finArriveeString = villeArrivee.indexOf("]");

        if (debutDepart < 0) {
            debutDepart = -2;
        }

        double villeDepartLatitude = Double.parseDouble(villeDepart.substring(debutDepart + 10, finDepart));
        double villeDepartLongitude = Double.parseDouble(villeDepart.substring(finDepart + 12, finDepartString));

        int debutArrivee = villeArrivee.indexOf("lattitude=");
        int finArrivee = villeArrivee.indexOf(", longitude=");

        if (debutDepart < 0) {
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

        double distanceB = 6372 * (Math
                .acos(Math.sin(villeDepartLatitude * Math.PI / 180) * Math.sin(villeArriveeLatitude * Math.PI / 180)
                        + Math.cos(villeDepartLatitude * Math.PI / 180) * Math.cos(villeArriveeLatitude * Math.PI / 180)
                                * Math.cos((villeDepartLongitude - villeArriveeLongitude) * Math.PI / 180)));

        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);

        HttpSession session = request.getSession();
        session.setAttribute("nomCommuneDepart", nomCommuneDepart);
        session.setAttribute("nomCommuneArrivee", nomCommuneArrivee);
        session.setAttribute("distance", f.format(distanceB));
        this.getServletContext().getRequestDispatcher("/WEB-INF/resultatCalcul.jsp").forward(request, response);
    }

}
