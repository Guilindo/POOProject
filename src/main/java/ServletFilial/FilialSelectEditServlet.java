package ServletFilial;

import DAO.FilialDAO;
import Model.Filial;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FilialGetFilialServlet", urlPatterns = {"/ti/dados_filial"})
public class FilialSelectEditServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fCodigo = request.getParameter("editarID");
        Filial filial = FilialDAO.getFilial(Integer.parseInt(fCodigo));

        request.setAttribute("acao", "editar");
        request.setAttribute("codigo", filial.getCodigo());
        request.setAttribute("logradouro", filial.getLogradouro());
        request.setAttribute("numero", filial.getNumero());
        request.setAttribute("cep", filial.getCep());
        request.setAttribute("bairro", filial.getBairro());
        request.setAttribute("cidade", filial.getCidade());
        request.setAttribute("estado", filial.getEstado());
        request.setAttribute("telefone", filial.getTelefone());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/cadastro_filiais.jsp");
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
