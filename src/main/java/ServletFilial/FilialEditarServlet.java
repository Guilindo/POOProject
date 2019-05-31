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

@WebServlet(name = "FilialEditarServlet", urlPatterns = {"/ti/editar_filial"})
public class FilialEditarServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fCodigo = request.getParameter("codigoFilial");
        String fLogradouro = request.getParameter("logradouro");
        String fNumero = request.getParameter("numero");
        String fCep = request.getParameter("cep");
        String fBairro = request.getParameter("bairro");
        String fCidade = request.getParameter("cidade");
        String fEstado = request.getParameter("estado");
        String fTelefone = request.getParameter("telefone");

        boolean error = false;
        if (fCodigo.length() == 0) {
            error = true;
            request.setAttribute("codigoErro", "Codigo não informado");
        }
        if (fLogradouro.length() == 0) {
            error = true;
            request.setAttribute("logradouroErro", "Logradouro não informado");
        }
        if (fNumero.length() == 0) {
            error = true;
            request.setAttribute("numeroErro", "Numero não informado");
        }
        if (fCep.length() == 0) {
            error = true;
            request.setAttribute("cepErro", "CEP não informado");
        }
        if (fBairro.length() == 0) {
            error = true;
            request.setAttribute("bairroErro", "Bairro não informado");
        }
        if (fCidade.length() == 0) {
            error = true;
            request.setAttribute("cidadeErro", "Cidade não informada");
        }
        if (fEstado.length() == 0) {
            error = true;
            request.setAttribute("estadoErro", "Estado não informado");
        }

        if (error) {
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

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Erro ao editar a filial, verifique os campos e tente novamente.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/cadastro_filiais.jsp");
            dispatcher.forward(request, response);
        } else {
            Filial filial = new Filial(fLogradouro, Integer.parseInt(fNumero), fCep, fBairro, fCidade, fEstado);
            if (fTelefone.length() != 0) {
                filial.setTelefone(fTelefone);
            }
            filial.setCodigo(Integer.parseInt(fCodigo));
            boolean httpOK = FilialDAO.atualizarFilial(filial);

            if (httpOK) {
                ArrayList<Filial> filiais = FilialDAO.getFiliais();
                request.setAttribute("listaFiliais", filiais);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Filial editada com sucesso.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_filiais.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Erro ao salvar editação no banco de dados, verifique os campos e tente novamente.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/cadastro_filiais.jsp");
                dispatcher.forward(request, response);
            }
        }
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
