package ville.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ville.bean.VilleMeteo;

/**
 * Servlet implementation class ResultatMeteo
 */
@WebServlet("/ResultatMeteo")
public class ResultatMeteo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatMeteo() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ville = request.getParameter("idVille");

        int debutDepart = ville.indexOf("codeCommuneInsee=");
        int finDepart = ville.indexOf(", nomCommune=");

        if (debutDepart < 0) {
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

        URL urlMeteo = new URL("http://api.openweathermap.org/data/2.5/weather?" + "lat=" + latitude + "&lon="
                + longitude + "&APPID=aafa073a24884dbccfef4dc9da725aeb");
        HttpURLConnection conMeteo = (HttpURLConnection) urlMeteo.openConnection();
        conMeteo.setRequestMethod("GET");

        BufferedReader inMeteo = new BufferedReader(new InputStreamReader(conMeteo.getInputStream()));
        String inputLineMeteo;
        StringBuffer responseMeteo = new StringBuffer();

        while ((inputLineMeteo = inMeteo.readLine()) != null) {
            responseMeteo.append(inputLineMeteo);
        }
        inMeteo.close();

        int debutTemps = responseMeteo.indexOf("weather");
        int finTemps = responseMeteo.indexOf(",\"description\"");
        int debutTemperature = responseMeteo.indexOf("temp\":");
        int finTemperature = responseMeteo.indexOf(",\"pressure");
        int debutIcone = responseMeteo.indexOf("\"weather\":[{");
        int finIcone = responseMeteo.indexOf("],\"base\"");

        String temps = responseMeteo.substring(debutTemps + 7, finTemps);
        temps = temps.substring(temps.indexOf("main\":") + 7, temps.length() - 1);

        String temperature = responseMeteo.substring(debutTemperature + 6, finTemperature);
        String temperatureCelcius = kelvinToCelcius(temperature);

        String icone = responseMeteo.substring(debutIcone + 12, finIcone);
        String cheminIcone = "http://openweathermap.org/img/w/"
                + icone.substring(icone.indexOf("icon") + 7, icone.indexOf("}") - 1) + ".png";
        String population = "Inconnu";

        try {
            URL urlPop = new URL("https://geo.api.gouv.fr/communes/" + codeCommuneInsee + "?fields=population");
            HttpsURLConnection conPop = (HttpsURLConnection) urlPop.openConnection();
            conPop.setRequestMethod("GET");

            BufferedReader inPop = new BufferedReader(new InputStreamReader(conPop.getInputStream()));
            String inputLinePop;
            StringBuilder responsePop = new StringBuilder();

            while ((inputLinePop = inPop.readLine()) != null) {
                responsePop.append(inputLinePop);
            }
            inPop.close();

            if (!responsePop.equals("Not Found")) {
                population = responsePop.substring(responsePop.indexOf("population") + 12,
                        responsePop.indexOf(",\"nom\""));
            } else {
                population = "Inconnu";
            }
        } catch (IOException e) {
            throw new IOException(e);
        }

        HttpSession session = request.getSession();

        VilleMeteo villeMeteo = new VilleMeteo();
        villeMeteo.setCodeCommuneInsee(codeCommuneInsee);
        villeMeteo.setCodePostal(codePostal);
        villeMeteo.setLattitude(latitude);
        villeMeteo.setNomCommune(nomCommune);
        villeMeteo.setLibelleAcheminement(libelleAcheminement);
        villeMeteo.setLigne5(ligne5);
        villeMeteo.setLongitude(longitude);
        villeMeteo.setTemperature(temperatureCelcius);
        villeMeteo.setTemps(temps);
        villeMeteo.setIdTemps(cheminIcone);
        villeMeteo.setPop(population);

        session.setAttribute("villeMeteo", villeMeteo);

        this.getServletContext().getRequestDispatcher("/WEB-INF/resultatMeteo.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private String kelvinToCelcius(String temperature) {
        Double celcius = (Double.parseDouble(temperature) - 273.15);
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);
        String temperatureCelcius = f.format(celcius);
        return temperatureCelcius;
    }

}
