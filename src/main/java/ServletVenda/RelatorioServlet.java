package ServletVenda;

import DAO.FilialDAO;
import DAO.RelatorioDAO;
import Model.Filial;
import Model.Relatorio;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RelatorioServlet", urlPatterns = {"/venda/gerar_relatorio"})
public class RelatorioServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dataDe = request.getParameter("dataDe");
        String dataAte = request.getParameter("dataAte");
        String filial = request.getParameter("filial");

        if ((!dataDe.equalsIgnoreCase("")) && (!dataAte.equalsIgnoreCase(""))) {
            String dataDeFormat = dataDe.concat(" 00:00:00");
            String dataAteFormat = dataAte.concat(" 23:59:59");
            if (Integer.parseInt(filial) != 0) {
                ArrayList<Relatorio> relatorio = RelatorioDAO.getRelatorioCompleto(Integer.parseInt(filial), dataDeFormat, dataAteFormat);
                request.setAttribute("listaRelatorios", relatorio);
            } else {
                ArrayList<Relatorio> relatorio = RelatorioDAO.getRelatorioData(dataDeFormat, dataAteFormat);
                request.setAttribute("listaRelatorios", relatorio);
            }
        } else {
            if ((filial != null) && (Integer.parseInt(filial) != 0)) {
                ArrayList<Relatorio> relatorio = RelatorioDAO.getRelatorioFilial(Integer.parseInt(filial));
                request.setAttribute("listaRelatorios", relatorio);
            } else {
                ArrayList<Relatorio> relatorio = RelatorioDAO.getRelatorioGeral();
                request.setAttribute("listaRelatorios", relatorio);
            }
        }

        ArrayList<Filial> filiais = FilialDAO.getFiliais();
        request.setAttribute("listaFiliais", filiais);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/venda/relatorio.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("POST", req, resp);
    }

}
