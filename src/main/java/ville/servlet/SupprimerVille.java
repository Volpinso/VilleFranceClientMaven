package ville.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class EditerVille
 */
@WebServlet("/SupprimerVille")
public class SupprimerVille extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerVille() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String ville = request.getParameter("idVille");

        int debutDepart = 0;
        int finDepart = 0;
        debutDepart = ville.indexOf("codeCommuneInsee=");
        finDepart = ville.indexOf(", nomCommune=");

        if (debutDepart < 0) {
            debutDepart = -2;
        }

        String codeCommuneInsee = ville.substring(debutDepart + 17, finDepart);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8181/villeFranceDelInsee");
        List<NameValuePair> arguments = new ArrayList<NameValuePair>(1);
        arguments.add(new BasicNameValuePair("value", codeCommuneInsee));
        try {
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response1 = client.execute(post);

            // Print out the response message
            System.out.println(EntityUtils.toString(response1.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/UtilisationVille").forward(request, response);
    }

}
