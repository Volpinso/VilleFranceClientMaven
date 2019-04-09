package ville.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import ville.bean.VilleBuilder;

/**
 * Servlet implementation class ResultatModification
 */
@WebServlet("/ResultatAjout")
public class ResultatAjout extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatAjout() {
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

        String codeCommuneInsee = request.getParameter("codeCommuneInsee");

        String nomCommune = request.getParameter("nomCommune");

        String codePostal = request.getParameter("codePostal");

        String libelleAcheminement = request.getParameter("libelleAcheminement");

        String ligne5 = request.getParameter("ligne5");

        String latitude = request.getParameter("latitude");

        String longitude = request.getParameter("longitude");

        VilleBuilder ville = new VilleBuilder();

        ville.setCodeCommuneInsee(codeCommuneInsee);
        ville.setCodePostal(codePostal);
        ville.setLattitude(latitude);
        ville.setLibelleAcheminement(libelleAcheminement);
        ville.setLigne5(ligne5);
        ville.setLongitude(longitude);
        ville.setNomCommune(nomCommune);

        URL url = new URL("http://localhost:8181/villeFranceCompte?value=" + ville.getCodeCommuneInsee());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response1 = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response1.append(inputLine);
        }
        in.close();

        if ("0".equals(response1.toString())) {

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("http://localhost:8181/villeFranceAdd");
            List<NameValuePair> arguments = new ArrayList<NameValuePair>(1);
            arguments.add(new BasicNameValuePair("value", ville.toString()));
            try {
                post.setEntity(new UrlEncodedFormEntity(arguments));
                client.execute(post);

            } catch (IOException e) {
                throw new IOException(e);
            }

            HttpSession session = request.getSession();
            session.setAttribute("test", response1.toString());
            this.getServletContext().getRequestDispatcher("/UtilisationVille").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/erreurAjout.jsp").forward(request, response);
        }
    }

}
