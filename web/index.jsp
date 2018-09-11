<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Produto"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>"
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="index.jsp" method="post">
            <label>Descrição:</label>
            <input type="text" name="descricao"/></br>
            <button type="submit">OK</button>
        </form>
        <%
            try{
                out.print("<table>");
                out.print("<tr>");
                out.print("<th>Código</th><th>Descrição</th><th>Preço</th><th>Editar</th><th>Excluir</th>");
                out.print("</tr>");
                out.print("</table>");
                ProdutoDAO prd = new ProdutoDAO();
                if(request.getParameter("descricao") == "" || request.getParameter("descricao") == null){
                    ArrayList<Produto> lista = prd.ListarTodos();
                    for(int num = 0; num < lista.size(); num++){
                        out.print("<tr>");
                        out.print("<td>"+lista.get(num).getCod_produto()+"</td>");
                        out.print("<td>"+lista.get(num).getDescricao_produto()+"</td>");
                        out.print("<td>"+lista.get(num).getPreco_produto()+"</td>");
                out.print("<td><a href='alterar.jsp?codigo="+lista.get(num).getCod_produto()+"&descricao="+lista.get(num).getDescricao_produto()+"&preco="+lista.get(num).getPreco_produto()+"'>CLIQUE</a></td>" );
                out.print("<td><a href='excluir.jsp?codigo="+lista.get(num).getCod_produto()+"&descricao="+lista.get(num).getDescricao_produto()+"'>CLIQUE</a></td>" );
                        out.print("</tr>");
                    }
                }else{
                    ArrayList<Produto> lista = prd.ListarTodosDescricao(request.getParameter("descricao"));
                    for(int num = 0; num < lista.size(); num++){
                        out.print("<tr>");
                        out.print("<td>"+lista.get(num).getCod_produto()+"</td>");
                        out.print("<td>"+lista.get(num).getDescricao_produto()+"</td>");
                        out.print("<td>"+lista.get(num).getPreco_produto()+"</td>");
                out.print("<td><a href='alterar.jsp?codigo="+lista.get(num).getCod_produto()+"&descricao="+lista.get(num).getDescricao_produto()+"&preco="+lista.get(num).getPreco_produto()+"'>CLIQUE</a></td>" );
                out.print("<td><a href='excluir.jsp?codigo="+lista.get(num).getCod_produto()+"&descricao="+lista.get(num).getDescricao_produto()+"'>CLIQUE</a></td>" );
                        out.print("</tr>");
                    }
                }
               
            }catch(Exception erro){
                throw new RuntimeException("Erro 11: "+erro);
            }
        %>
        <a href="inserir.jsp">NOVO</a>
    </body>
</html>
