package ville.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ville.bean.VilleBuilder;

/**
 * Servlet implementation class ResultatModification
 */
@WebServlet("/ResultatModification")
public class ResultatModification extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatModification() {
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
        HttpSession session = request.getSession();

        String codeCommuneInsee = request.getParameter("codeCommuneInsee");

        String nomCommune = request.getParameter("nomCommune");

        if (nomCommune.equals("")) {
            nomCommune = (String) session.getAttribute("nomCommune");
        }

        String codePostal = request.getParameter("codePostal");

        if (codePostal.equals("")) {
            codePostal = (String) session.getAttribute("codePostal");
        }

        String libelleAcheminement = request.getParameter("libelleAcheminement");

        if (libelleAcheminement.equals("")) {
            libelleAcheminement = (String) session.getAttribute("libelleAcheminement");
        }

        String ligne5 = request.getParameter("ligne5");

        if (ligne5.equals("")) {
            ligne5 = (String) session.getAttribute("ligne5");
        }

        String latitude = request.getParameter("latitude");

        if (latitude.equals("")) {
            latitude = (String) session.getAttribute("latitude");
        }

        String longitude = request.getParameter("longitude");

        if (longitude.equals("")) {
            longitude = (String) session.getAttribute("longitude");
        }

        VilleBuilder ville = new VilleBuilder();

        ville.setCodeCommuneInsee(codeCommuneInsee);
        ville.setCodePostal(codePostal);
        ville.setLattitude(latitude);
        ville.setLibelleAcheminement(libelleAcheminement);
        ville.setLigne5(ligne5);
        ville.setLongitude(longitude);
        ville.setNomCommune(nomCommune);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8181/villeFrancePost");
        List<NameValuePair> arguments = new ArrayList<NameValuePair>(1);
        arguments.add(new BasicNameValuePair("value", ville.toString()));
        try {
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response1 = client.execute(post);

            // Print out the response message
            System.out.println(EntityUtils.toString(response1.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/resultatModification.jsp").forward(request, response);
    }

}
