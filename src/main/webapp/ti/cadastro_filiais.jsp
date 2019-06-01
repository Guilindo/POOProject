<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Cadastro de Filiais</title>
        <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="../assets/css/navbar-top.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    </head>
    <body>
        <header>
            <h1 style="text-align: center;">
                <span class="sr-only">Floricultura TADES</span>
                <img src="../assets/img/logo.png">
            </h1>
            <nav class="navbar navbar-expand-md navbar-light bg-warning mb-4">
                <ul id="itensMenu" class="nav justify-content-center">
                    <li class="nav-item">
                        <form action="../venda/cadastro_vendas">
                            <button type="submit" class="btn nav-link nav-text">Vendas</button>
                        </form>                     
                    </li>
                    <li class="nav-item">
                        <form action="../produtos/listagem_produtos" method="GET">
                            <button type="submit" class="btn nav-link nav-text" >Produtos</button>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form action="../ti/listagem_usuarios" method="GET">
                            <button type="submit" class="btn nav-link nav-text" >Usuarios</button>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form action="../venda/carrega_relatorio" method="GET">
                            <button type="submit" class="btn nav-link nav-text" >Relatorio</button>
                        </form>
                    </li>
                </ul>
                 <i class="fas fa-user-tie" style="margin-left: -189px;"></i> &nbsp;&nbsp; Olá, <c:out value="${nomeUsuario}"/> 
                <form action="../venda/logout" method="POST">
                    <button type="submit" class="btn nav-link nav-text" >Sair</button>
                </form>
            </nav>
        </header>

        <c:if test="${acao == 'editar'}"> 
            <h2 class='h2 text-center subtitulo'>Editar Filial</h2> 
        </c:if>
        <c:if test="${empty acao}">
            <h2 class='h2 text-center subtitulo'>Cadastrar Filial</h2>
        </c:if>

        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <c:if test="${acao == 'editar'}">
                        <form action="editar_filial" method="post">
                        </c:if>
                        <c:if test="${empty acao}">
                            <form action="cadastro_filial" method="post">
                            </c:if>

                            <c:if test="${varMsg == true}">
                                <div class="alert alert-danger" role="alert">
                                    <c:out value="${msg}"/>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label for="codigoFilial" class="sr-only">Código:</label>
                                <c:if test="${acao == 'editar'}">
                                    <input type="number" class="form-control inputForm sr-only" id="codigoFilial" 
                                           value="${codigo}" name="codigoFilial">  
                                </c:if>
                                <c:if test="${empty acao}">
                                    <input type="number" class="form-control inputForm sr-only" id="codigoFilial" 
                                           placeholder="Código da filial" name="codigoFilial">  
                                </c:if>

                                <label for="logradouro">Logradouro:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty logradouroErro}">
                                        <input type="text" class="form-control inputForm" id="logradouro" 
                                               value="${logradouro}" name="logradouro" minlength="0" maxlength="75">
                                    </c:if>
                                    <c:if test="${not empty logradouroErro}">                                
                                        <input type="text" class="form-control inputForm error" id="logradouro" 
                                               placeholder="${logradouroErro}" name="logradouro" minlength="0" maxlength="75">
                                    </c:if>      
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty logradouroErro}">
                                        <input type="text" class="form-control inputForm" id="logradouro" 
                                               placeholder="Logradouro" name="logradouro" minlength="0" maxlength="75">
                                    </c:if>
                                    <c:if test="${not empty logradouroErro}">                                
                                        <input type="text" class="form-control inputForm error" id="logradouro" 
                                               placeholder="${logradouroErro}" name="logradouro" minlength="0" maxlength="75">
                                    </c:if>    
                                </c:if>

                                <label for="numero">Número:</label>                           
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty numeroErro}">                                
                                        <input type="number" class="form-control inputForm" id="numero" 
                                               value="${numero}" name="numero" min="0" minlength="0" maxlength="11">
                                    </c:if>
                                    <c:if test="${not empty numeroErro}">                                
                                        <input type="number" class="form-control inputForm error" id="numero" 
                                               placeholder="${numeroErro}" name="numero" min="0" minlength="0" maxlength="11">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty numeroErro}">                                
                                        <input type="number" class="form-control inputForm" id="numero" 
                                               placeholder="Número" name="numero" min="0" minlength="0" maxlength="11">
                                    </c:if>
                                    <c:if test="${not empty numeroErro}">                                
                                        <input type="number" class="form-control inputForm error" id="numero" 
                                               placeholder="${numeroErro}" name="numero" min="0" minlength="0" maxlength="11">
                                    </c:if>
                                </c:if>

                                <label for="cep">CEP:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty cepErro}">
                                        <input type="number" class="form-control inputForm" id="cep" 
                                               value="${cep}" name="cep" min="0" minlength="0" maxlength="10">
                                    </c:if>
                                    <c:if test="${not empty cepErro}">
                                        <input type="number" class="form-control inputForm error" id="cep" 
                                               placeholder="${cepErro}" name="cep" min="0" minlength="0" maxlength="10">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty cepErro}">
                                        <input type="number" class="form-control inputForm" id="cep" 
                                               placeholder="CEP" name="cep" min="0" minlength="0" maxlength="10">
                                    </c:if>
                                    <c:if test="${not empty cepErro}">
                                        <input type="number" class="form-control inputForm error" id="cep" 
                                               placeholder="${cepErro}" name="cep" min="0" minlength="0" maxlength="10">
                                    </c:if>
                                </c:if>

                                <label for="bairro">Bairro:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty bairroErro}">
                                        <input type="text" class="form-control inputForm" id="bairro" 
                                               value="${bairro}" name="bairro" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty bairroErro}">
                                        <input type="text" class="form-control inputForm error" id="bairro" 
                                               placeholder="${bairroErro}" name="bairro" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty bairroErro}">
                                        <input type="text" class="form-control inputForm" id="bairro" 
                                               placeholder="Bairro" name="bairro" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty bairroErro}">
                                        <input type="text" class="form-control inputForm error" id="bairro" 
                                               placeholder="${bairroErro}" name="bairro" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>

                                <label for="cidade">Cidade:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty cidadeErro}">
                                        <input type="text" class="form-control inputForm" id="cidade" 
                                               value="${cidade}" name="cidade" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty cidadeErro}">
                                        <input type="text" class="form-control inputForm error" id="cidade" 
                                               placeholder="${cidadeErro}" name="cidade" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty cidadeErro}">
                                        <input type="text" class="form-control inputForm" id="cidade" 
                                               placeholder="Cidade" name="cidade" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty cidadeErro}">
                                        <input type="text" class="form-control inputForm error" id="cidade" 
                                               placeholder="${cidadeErro}" name="cidade" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>

                                <label for="estado">Estado:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty estadoErro}">
                                        <input type="text" class="form-control inputForm" id="estado" 
                                               value="${estado}" name="estado" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty estadoErro}">
                                        <input type="text" class="form-control inputForm error" id="estado" 
                                               placeholder="${estadoErro}" name="estado" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty estadoErro}">
                                        <input type="text" class="form-control inputForm" id="estado" 
                                               placeholder="Estado" name="estado" minlength="0" maxlength="50">
                                    </c:if>
                                    <c:if test="${not empty estadoErro}">
                                        <input type="text" class="form-control inputForm error" id="estado" 
                                               placeholder="${estadoErro}" name="estado" minlength="0" maxlength="50">
                                    </c:if>
                                </c:if>

                                <label for="telefone">Telefone:</label>
                                <c:if test="${acao == 'editar'}">
                                    <c:if test="${empty telefoneErro}">
                                        <input type="number" class="form-control inputForm" id="telefone" 
                                               value="${telefone}" name="telefone" min="0" minlength="0" maxlength="15">
                                    </c:if>
                                    <c:if test="${not empty telefoneErro}">
                                        <input type="number" class="form-control inputForm error" id="telefone" 
                                               placeholder="${telefoneErro}" name="telefone" min="0" minlength="0" maxlength="15">
                                    </c:if>
                                </c:if>
                                <c:if test="${empty acao}">
                                    <c:if test="${empty telefoneErro}">
                                        <input type="number" class="form-control inputForm" id="telefone" 
                                               placeholder="Telefone" name="telefone" min="0" minlength="0" maxlength="15">
                                    </c:if>
                                    <c:if test="${not empty telefoneErro}">
                                        <input type="number" class="form-control inputForm error" id="telefone" 
                                               placeholder="${telefoneErro}" name="telefone" min="0" minlength="0" maxlength="15">
                                    </c:if>
                                </c:if>  
                            </div>
                            <button type="submit" class="btn btn-light btn-block">
                                <i class="far fa-save"></i>
                                Salvar
                            </button>
                        </form>
                        <form action="listagem_filiais" method="GET" style="padding-top: 8px; ">
                            <button type="submit" class="btn btn-light btn-block">
                                <i class="fas fa-ban"></i>
                                Cancelar
                            </button>
                        </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
        <script src="../assets/js/jquery-2.1.3.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/main.js"></script>
    </body>
</html>
