package ServletFilial;

import DAO.FilialDAO;
import Model.Filial;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FilialExcluirVariosServlet", urlPatterns = {"/ti/excluir_filiais"})
public class FilialExcluirVariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] filiaisSelecionadas = request.getParameterValues("selected");

        boolean httpOK = FilialDAO.excluirFiliais(filiaisSelecionadas);

        if (httpOK) {
            ArrayList<Filial> filiais = FilialDAO.getFiliais();
            request.setAttribute("listaFiliais", filiais);

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Filiais excluidas com sucesso.");
            
        } else {
            ArrayList<Filial> filiais = FilialDAO.getFiliais();
            request.setAttribute("listaFiliais", filiais);

            request.setAttribute("varMsgErro", true);
            request.setAttribute("msg", "Erro ao excluir filiais.");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_filiais.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
