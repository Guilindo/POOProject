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

@WebServlet(name = "FilialExcluirServlet", urlPatterns = {"/ti/excluir_filial"})
public class FilialExcluirServlet extends HttpServlet {

    protected void processaRequisicao(String HttpMethod, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fCodigo = request.getParameter("excluirID");

        boolean error = false;
        if (fCodigo == null) {
            error = true;
            request.setAttribute("codigoErro", "Codigo não informado");
        } else if (fCodigo.equalsIgnoreCase("0")) {
            error = true;
            request.setAttribute("codigoErro", "Codigo invalido");
        }

        if (error) {
            ArrayList<Filial> filiais = FilialDAO.getFiliais();
            request.setAttribute("listaFiliais", filiais);

            request.setAttribute("varMsgErro", true);
            request.setAttribute("msg", "Erro ao excluir a filial, verifique os campos e tente novamente.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/cadastro_filiais.jsp");
            dispatcher.forward(request, response);
        } else {
            boolean httpOk = FilialDAO.excluirFilial(Integer.parseInt(fCodigo));
            if (httpOk) {
                ArrayList<Filial> filiais = FilialDAO.getFiliais();
                request.setAttribute("listaFiliais", filiais);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Filial excluida com sucesso.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_filiais.jsp");
                dispatcher.forward(request, response);
            } else {
                ArrayList<Filial> filiais = FilialDAO.getFiliais();
                request.setAttribute("listaFiliais", filiais);

                request.setAttribute("varMsgErro", true);
                request.setAttribute("msg", "Erro ao excluir filial.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_filiais.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processaRequisicao("GET", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processaRequisicao("POST", request, response);
    }

}
